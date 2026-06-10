package com.ecommerce.repositorio;

import com.ecommerce.entidade.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagamentoRepositorio extends JpaRepository<TipoPagamento, Integer> {
}
