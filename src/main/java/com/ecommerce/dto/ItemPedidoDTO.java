package com.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * DTO para ItemPedido.
 */
public class ItemPedidoDTO {

    /**
     * Record para requisição de criação de item de pedido.
     */
    public record Requisicao(
            @NotNull(message = "ID do produto é obrigatório")
            Integer produtoId,

            @NotNull(message = "Quantidade é obrigatória")
            @Positive(message = "Quantidade deve ser positiva")
            Integer quantidade
    ) {}

    /**
     * Record para resposta de item de pedido.
     */
    public record Resposta(
            Integer id,
            Integer produtoId,
            String nomeProduto,
            Integer quantidade,
            BigDecimal precoUnitario,
            BigDecimal subtotal
    ) {}
}
