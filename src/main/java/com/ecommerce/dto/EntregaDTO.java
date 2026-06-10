package com.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para Entrega.
 */
public class EntregaDTO {

    /**
     * Record para requisição de criação de entrega.
     */
    public record Requisicao(
            @NotNull(message = "ID do pedido é obrigatório")
            Integer pedidoId,

            @NotBlank(message = "CEP é obrigatório")
            String cep,

            String numero,
            String cidade,
            String estado,
            String complemento
    ) {}

    /**
     * Record para resposta de entrega.
     */
    public record Resposta(
            Integer id,
            Integer pedidoId,
            String cep,
            String numero,
            String cidade,
            String estado,
            String complemento
    ) {}
}
