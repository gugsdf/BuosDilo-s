package com.ecommerce.repositorio;

import com.ecommerce.entidade.TelefoneConsumidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneConsumidorRepositorio extends JpaRepository<TelefoneConsumidor, Integer> {
}
