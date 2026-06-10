package com.ecommerce.repositorio;

import com.ecommerce.entidade.EnderecoConsumidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoConsumidorRepositorio extends JpaRepository<EnderecoConsumidor, Integer> {
}
