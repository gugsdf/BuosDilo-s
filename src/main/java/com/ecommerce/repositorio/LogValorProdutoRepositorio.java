package com.ecommerce.repositorio;

import com.ecommerce.entidade.LogValorProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogValorProdutoRepositorio extends JpaRepository<LogValorProduto, Integer> {
}
