package com.ecommerce.servico;

import com.ecommerce.dto.PagamentoDTO;
import com.ecommerce.entidade.Pagamento;
import com.ecommerce.entidade.PedidoConsumidor;
import com.ecommerce.entidade.TipoPagamento;
import com.ecommerce.excecao.RegraDeNegocioException;
import com.ecommerce.excecao.RecursoNaoEncontradoException;
import com.ecommerce.repositorio.PagamentoRepositorio;
import com.ecommerce.repositorio.TipoPagamentoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoServico {

    private final PagamentoRepositorio repositorio;
    private final TipoPagamentoRepositorio tipoPagamentoRepositorio;
    private final PedidoConsumidorServico pedidoServico;

    @Transactional(readOnly = true)
    public List<PagamentoDTO.Resposta> listarTodos() {
        return repositorio.findAll().stream()
                .map(this::paraResposta)
                .toList();
    }

    @Transactional(readOnly = true)
    public PagamentoDTO.Resposta buscarPorId(Integer id) {
        return paraResposta(repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pagamento", id)));
    }

    @Transactional
    public PagamentoDTO.Resposta criar(PagamentoDTO.Requisicao requisicao) {
        PedidoConsumidor pedido = pedidoServico.buscarEntidade(requisicao.pedidoId());

        if ("PAGO".equalsIgnoreCase(pedido.getStatus())) {
            throw new RegraDeNegocioException("Este pedido já foi pago");
        }

        TipoPagamento tipo = tipoPagamentoRepositorio.findById(requisicao.tipoPagamentoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tipo de pagamento", requisicao.tipoPagamentoId()));

        Pagamento pagamento = Pagamento.builder()
                .pedido(pedido)
                .tipoPagamento(tipo)
                .valor(requisicao.valor())
                .status("APROVADO")
                .build();

        Pagamento salvo = repositorio.save(pagamento);

        // Atualizar status do pedido
        pedidoServico.atualizarStatus(pedido.getId(), "PAGO");

        return paraResposta(salvo);
    }

    private PagamentoDTO.Resposta paraResposta(Pagamento pagamento) {
        return new PagamentoDTO.Resposta(
                pagamento.getId(),
                pagamento.getPedido().getId(),
                pagamento.getTipoPagamento().getNome(),
                pagamento.getValor(),
                pagamento.getStatus(),
                pagamento.getDataPagamento()
        );
    }
}
