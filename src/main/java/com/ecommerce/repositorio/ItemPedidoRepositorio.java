package com.ecommerce.repositorio;

import com.ecommerce.entidade.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepositorio extends JpaRepository<ItemPedido, Integer> {
}
