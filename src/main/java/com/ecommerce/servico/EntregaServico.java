package com.ecommerce.servico;

import com.ecommerce.dto.EntregaDTO;
import com.ecommerce.entidade.Entrega;
import com.ecommerce.entidade.PedidoConsumidor;
import com.ecommerce.excecao.RegraDeNegocioException;
import com.ecommerce.excecao.RecursoNaoEncontradoException;
import com.ecommerce.repositorio.EntregaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntregaServico {

    private final EntregaRepositorio repositorio;
    private final PedidoConsumidorServico pedidoServico;

    @Transactional(readOnly = true)
    public List<EntregaDTO.Resposta> listarTodas() {
        return repositorio.findAll().stream()
                .map(this::paraResposta)
                .toList();
    }

    @Transactional(readOnly = true)
    public EntregaDTO.Resposta buscarPorId(Integer id) {
        return paraResposta(repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Entrega", id)));
    }

    @Transactional
    public EntregaDTO.Resposta criar(EntregaDTO.Requisicao requisicao) {
        PedidoConsumidor pedido = pedidoServico.buscarEntidade(requisicao.pedidoId());

        if (repositorio.existsByPedidoId(pedido.getId())) {
            throw new RegraDeNegocioException("Já existe uma entrega para este pedido");
        }

        Entrega entrega = Entrega.builder()
                .pedido(pedido)
                .cep(requisicao.cep())
                .numero(requisicao.numero())
                .cidade(requisicao.cidade())
                .estado(requisicao.estado())
                .complemento(requisicao.complemento())
                .build();

        return paraResposta(repositorio.save(entrega));
    }

    private EntregaDTO.Resposta paraResposta(Entrega entrega) {
        return new EntregaDTO.Resposta(
                entrega.getId(),
                entrega.getPedido().getId(),
                entrega.getCep(),
                entrega.getNumero(),
                entrega.getCidade(),
                entrega.getEstado(),
                entrega.getComplemento()
        );
    }
}
