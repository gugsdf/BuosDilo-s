package com.ecommerce.repositorio;

import com.ecommerce.entidade.EnderecoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoEstoqueRepositorio extends JpaRepository<EnderecoEstoque, Integer> {
}
