package com.ecommerce.repositorio;

import com.ecommerce.entidade.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepositorio extends JpaRepository<Entrega, Integer> {
    boolean existsByPedidoId(Integer pedidoId);
}
