package com.ecommerce.repositorio;

import com.ecommerce.entidade.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepositorio extends JpaRepository<Pagamento, Integer> {
}
