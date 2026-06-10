package com.ecommerce.repositorio;

import com.ecommerce.entidade.PedidoConsumidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoConsumidorRepositorio extends JpaRepository<PedidoConsumidor, Integer> {

    List<PedidoConsumidor> findByConsumidorId(Integer consumidorId);

    List<PedidoConsumidor> findByStatus(String status);
}
