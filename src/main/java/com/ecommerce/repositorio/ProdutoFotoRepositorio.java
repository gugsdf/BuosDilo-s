package com.ecommerce.repositorio;

import com.ecommerce.entidade.ProdutoFoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoFotoRepositorio extends JpaRepository<ProdutoFoto, Integer> {
    List<ProdutoFoto> findByProdutoIdOrderByOrdem(Integer produtoId);
}

