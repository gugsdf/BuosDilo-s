package com.ecommerce.servico;

import com.ecommerce.dto.ProdutoDTO;
import com.ecommerce.entidade.LogValorProduto;
import com.ecommerce.entidade.Produto;
import com.ecommerce.excecao.RegraDeNegocioException;
import com.ecommerce.excecao.RecursoNaoEncontradoException;
import com.ecommerce.repositorio.LogValorProdutoRepositorio;
import com.ecommerce.repositorio.ProdutoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Serviço para Produto.
 */
@Service
@RequiredArgsConstructor
public class ProdutoServico {

    private final ProdutoRepositorio repositorio;
    private final LogValorProdutoRepositorio logRepositorio;

    /**
     * Lista todos os produtos.
     */
    @Transactional(readOnly = true)
    public List<ProdutoDTO.Resposta> listarTodos() {
        return repositorio.findAll().stream()
                .map(this::paraResposta)
                .toList();
    }

    /**
     * Lista produtos ativos.
     */
    @Transactional(readOnly = true)
    public List<ProdutoDTO.Resposta> listarAtivos() {
        return repositorio.findByAtivoTrue().stream()
                .map(this::paraResposta)
                .toList();
    }

    /**
     * Busca produto por ID.
     */
    @Transactional(readOnly = true)
    public ProdutoDTO.Resposta buscarPorId(Integer id) {
        return paraResposta(buscarEntidade(id));
    }

    /**
     * Cria um novo produto.
     */
    @Transactional
    public ProdutoDTO.Resposta criar(ProdutoDTO.Requisicao requisicao) {
        if (requisicao.sku() != null && repositorio.existsBySku(requisicao.sku())) {
            throw new RegraDeNegocioException("Já existe um produto com o SKU: " + requisicao.sku());
        }

        Produto produto = Produto.builder()
                .nome(requisicao.nome())
                .preco(requisicao.preco())
                .sku(requisicao.sku())
                .ativo(requisicao.ativo() != null ? requisicao.ativo() : true)
                .build();

        return paraResposta(repositorio.save(produto));
    }

    /**
     * Atualiza um produto existente.
     */
    @Transactional
    public ProdutoDTO.Resposta atualizar(Integer id, ProdutoDTO.Requisicao requisicao) {
        Produto produto = buscarEntidade(id);
        BigDecimal precoAnterior = produto.getPreco();

        produto.setNome(requisicao.nome());
        produto.setPreco(requisicao.preco());
        if (requisicao.sku() != null) produto.setSku(requisicao.sku());
        if (requisicao.ativo() != null) produto.setAtivo(requisicao.ativo());

        Produto salvo = repositorio.save(produto);

        // Registrar log se o preço mudou
        if (precoAnterior.compareTo(requisicao.preco()) != 0) {
            LogValorProduto log = LogValorProduto.builder()
                    .produto(salvo)
                    .acao("UPDATE")
                    .valorAntigo(precoAnterior)
                    .valorNovo(requisicao.preco())
                    .build();
            logRepositorio.save(log);
        }

        return paraResposta(salvo);
    }

    /**
     * Inativa um produto.
     */
    @Transactional
    public void inativar(Integer id) {
        Produto produto = buscarEntidade(id);
        produto.setAtivo(false);
        repositorio.save(produto);
    }

    public Produto buscarEntidade(Integer id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto", id));
    }

    private ProdutoDTO.Resposta paraResposta(Produto produto) {
        return new ProdutoDTO.Resposta(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getSku(),
                produto.getAtivo()
        );
    }
}
