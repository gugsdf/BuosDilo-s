package com.ecommerce.servico;

import com.ecommerce.dto.ItemPedidoDTO;
import com.ecommerce.dto.PedidoConsumidorDTO;
import com.ecommerce.entidade.*;
import com.ecommerce.excecao.RegraDeNegocioException;
import com.ecommerce.excecao.RecursoNaoEncontradoException;
import com.ecommerce.repositorio.ConsumidorRepositorio;
import com.ecommerce.repositorio.ItemPedidoRepositorio;
import com.ecommerce.repositorio.PedidoConsumidorRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoConsumidorServico {

    private final PedidoConsumidorRepositorio repositorio;
    private final ConsumidorRepositorio consumidorRepositorio;
    private final ItemPedidoRepositorio itemPedidoRepositorio;
    private final ProdutoServico produtoServico;

    @Transactional(readOnly = true)
    public List<PedidoConsumidorDTO.Resposta> listarTodos() {
        return repositorio.findAll().stream()
                .map(this::paraResposta)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<PedidoConsumidorDTO.Resposta> listarPorConsumidor(Integer consumidorId) {
        return repositorio.findByConsumidorId(consumidorId).stream()
                .map(this::paraResposta)
                .toList();
    }

    @Transactional(readOnly = true)
    public PedidoConsumidorDTO.Resposta buscarPorId(Integer id) {
        return paraResposta(buscarEntidade(id));
    }

    @Transactional
    public PedidoConsumidorDTO.Resposta criar(PedidoConsumidorDTO.Requisicao requisicao) {
        Consumidor consumidor = consumidorRepositorio.findById(requisicao.consumidorId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Consumidor", requisicao.consumidorId()));

        if (requisicao.itens() == null || requisicao.itens().isEmpty()) {
            throw new RegraDeNegocioException("O pedido deve ter pelo menos um item");
        }

        PedidoConsumidor pedido = PedidoConsumidor.builder()
                .consumidor(consumidor)
                .status("PENDENTE")
                .build();

        PedidoConsumidor pedidoSalvo = repositorio.save(pedido);

        List<ItemPedido> itens = new ArrayList<>();
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemPedidoDTO.Requisicao itemReq : requisicao.itens()) {
            Produto produto = produtoServico.buscarEntidade(itemReq.produtoId());

            if (!produto.getAtivo()) {
                throw new RegraDeNegocioException("Produto inativo: " + produto.getNome());
            }

            ItemPedido item = ItemPedido.builder()
                    .pedido(pedidoSalvo)
                    .produto(produto)
                    .quantidade(itemReq.quantidade())
                    .precoUnitario(produto.getPreco())
                    .build();

            item.calcularSubtotal();
            itens.add(itemPedidoRepositorio.save(item));
            valorTotal = valorTotal.add(item.getSubtotal());
        }

        pedidoSalvo.setValorTotal(valorTotal);
        pedidoSalvo.setItens(itens);
        return paraResposta(repositorio.save(pedidoSalvo));
    }

    @Transactional
    public PedidoConsumidorDTO.Resposta atualizarStatus(Integer id, String novoStatus) {
        PedidoConsumidor pedido = buscarEntidade(id);
        pedido.setStatus(novoStatus);
        return paraResposta(repositorio.save(pedido));
    }

    public PedidoConsumidor buscarEntidade(Integer id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido", id));
    }

    private PedidoConsumidorDTO.Resposta paraResposta(PedidoConsumidor pedido) {
        List<ItemPedidoDTO.Resposta> itensResposta = pedido.getItens() != null
                ? pedido.getItens().stream().map(item -> new ItemPedidoDTO.Resposta(
                        item.getId(),
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getPrecoUnitario(),
                        item.getSubtotal()
                  )).toList()
                : List.of();

        return new PedidoConsumidorDTO.Resposta(
                pedido.getId(),
                pedido.getConsumidor().getId(),
                pedido.getConsumidor().getNome(),
                pedido.getDataPedido(),
                pedido.getStatus(),
                pedido.getValorTotal(),
                itensResposta
        );
    }
}
