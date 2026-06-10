package com.ecommerce.servico;

import com.ecommerce.dto.ConsumidorDTO;
import com.ecommerce.entidade.Consumidor;
import com.ecommerce.excecao.RegraDeNegocioException;
import com.ecommerce.excecao.RecursoNaoEncontradoException;
import com.ecommerce.repositorio.ConsumidorRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Serviço para Consumidor.
 */
@Service
@RequiredArgsConstructor
public class ConsumidorServico {

    private final ConsumidorRepositorio repositorio;

    /**
     * Lista todos os consumidores.
     */
    @Transactional(readOnly = true)
    public List<ConsumidorDTO.Resposta> listarTodos() {
        return repositorio.findAll().stream()
                .map(this::paraResposta)
                .toList();
    }

    /**
     * Busca consumidor por ID.
     */
    @Transactional(readOnly = true)
    public ConsumidorDTO.Resposta buscarPorId(Integer id) {
        Consumidor consumidor = repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Consumidor", id));
        return paraResposta(consumidor);
    }

    /**
     * Cria um novo consumidor.
     */
    @Transactional
    public ConsumidorDTO.Resposta criar(ConsumidorDTO.Requisicao requisicao) {
        if (repositorio.existsByEmail(requisicao.email())) {
            throw new RegraDeNegocioException("Já existe um consumidor cadastrado com o e-mail: " + requisicao.email());
        }

        Consumidor consumidor = Consumidor.builder()
                .nome(requisicao.nome())
                .email(requisicao.email())
                .senha(requisicao.senha()) // Em produção, usar BCrypt
                .build();

        return paraResposta(repositorio.save(consumidor));
    }

    /**
     * Atualiza um consumidor existente.
     */
    @Transactional
    public ConsumidorDTO.Resposta atualizar(Integer id, ConsumidorDTO.Requisicao requisicao) {
        Consumidor consumidor = repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Consumidor", id));

        if (!consumidor.getEmail().equals(requisicao.email()) && repositorio.existsByEmail(requisicao.email())) {
            throw new RegraDeNegocioException("Já existe um consumidor com o e-mail: " + requisicao.email());
        }

        consumidor.setNome(requisicao.nome());
        consumidor.setEmail(requisicao.email());
        consumidor.setSenha(requisicao.senha());

        return paraResposta(repositorio.save(consumidor));
    }

    /**
     * Deleta um consumidor.
     */
    @Transactional
    public void deletar(Integer id) {
        if (!repositorio.existsById(id)) {
            throw new RecursoNaoEncontradoException("Consumidor", id);
        }
        repositorio.deleteById(id);
    }

    /**
     * Converte entidade Consumidor para DTO Resposta.
     */
    private ConsumidorDTO.Resposta paraResposta(Consumidor consumidor) {
        return new ConsumidorDTO.Resposta(
                consumidor.getId(),
                consumidor.getNome(),
                consumidor.getEmail()
        );
    }
}
