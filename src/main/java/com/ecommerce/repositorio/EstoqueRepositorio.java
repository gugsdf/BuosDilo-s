package com.ecommerce.repositorio;

import com.ecommerce.entidade.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepositorio extends JpaRepository<Estoque, Integer> {
}
