package com.ecommerce.servico;

import com.ecommerce.dto.ProdutoFotoDTO;
import com.ecommerce.entidade.Produto;
import com.ecommerce.entidade.ProdutoFoto;
import com.ecommerce.excecao.RecursoNaoEncontradoException;
import com.ecommerce.repositorio.ProdutoFotoRepositorio;
import com.ecommerce.repositorio.ProdutoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoFotoServico {

    private final ProdutoFotoRepositorio fotoRepositorio;
    private final ProdutoRepositorio produtoRepositorio;

    @Transactional(readOnly = true)
    public List<ProdutoFotoDTO.Resposta> listarPorProduto(Integer produtoId) {
        return fotoRepositorio.findByProdutoIdOrderByOrdem(produtoId).stream()
                .map(this::paraResposta)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProdutoFotoDTO.Resposta criar(Integer produtoId, ProdutoFotoDTO.Requisicao req) {
        Produto produto = produtoRepositorio.findById(produtoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto", produtoId));

        ProdutoFoto foto = ProdutoFoto.builder()
                .produto(produto)
                .fotoUrl(req.fotoUrl())
                .lado(req.lado())
                .ordem(req.ordem())
                .build();

        ProdutoFoto salvo = fotoRepositorio.save(foto);
        return paraResposta(salvo);
    }

    @Transactional
    public ProdutoFotoDTO.Resposta atualizar(Integer produtoId, Integer fotoId, ProdutoFotoDTO.Requisicao req) {
        // valida existencia do produto
        produtoRepositorio.findById(produtoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto", produtoId));

        ProdutoFoto foto = fotoRepositorio.findById(fotoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Foto do produto", fotoId));

        foto.setFotoUrl(req.fotoUrl());
        foto.setLado(req.lado());
        foto.setOrdem(req.ordem());

        ProdutoFoto salvo = fotoRepositorio.save(foto);
        return paraResposta(salvo);
    }

    @Transactional
    public void deletar(Integer produtoId, Integer fotoId) {
        // valida existencia do produto
        produtoRepositorio.findById(produtoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto", produtoId));

        ProdutoFoto foto = fotoRepositorio.findById(fotoId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Foto do produto", fotoId));

        fotoRepositorio.delete(foto);
    }


    private ProdutoFotoDTO.Resposta paraResposta(ProdutoFoto foto) {
        return new ProdutoFotoDTO.Resposta(
                foto.getId(),
                foto.getProduto() != null ? foto.getProduto().getId() : null,
                foto.getFotoUrl(),
                foto.getLado(),
                foto.getOrdem()
        );
    }
}

