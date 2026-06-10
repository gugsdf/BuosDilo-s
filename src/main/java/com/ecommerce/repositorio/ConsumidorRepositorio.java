package com.ecommerce.repositorio;

import com.ecommerce.entidade.Consumidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para Consumidor.
 */
@Repository
public interface ConsumidorRepositorio extends JpaRepository<Consumidor, Integer> {

    /**
     * Busca consumidor por e-mail.
     */
    Optional<Consumidor> findByEmail(String email);

    /**
     * Verifica se existe consumidor com o e-mail.
     */
    boolean existsByEmail(String email);
}
