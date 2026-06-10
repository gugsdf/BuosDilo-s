package com.ecommerce.repositorio;

import com.ecommerce.entidade.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Integer> {

    List<Produto> findByAtivoTrue();

    Optional<Produto> findBySku(String sku);

    boolean existsBySku(String sku);
}
