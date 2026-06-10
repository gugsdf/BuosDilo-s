package com.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para Consumidor.
 */
public class ConsumidorDTO {

    /**
     * Record para requisição de criação/atualização de consumidor.
     */
    public record Requisicao(
            @NotBlank(message = "Nome é obrigatório")
            String nome,

            @Email(message = "E-mail inválido")
            @NotBlank(message = "E-mail é obrigatório")
            String email,

            @NotBlank(message = "Senha é obrigatória")
            @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
            String senha
    ) {}

    /**
     * Record para resposta de consumidor.
     */
    public record Resposta(
            Integer id,
            String nome,
            String email
    ) {}
}
