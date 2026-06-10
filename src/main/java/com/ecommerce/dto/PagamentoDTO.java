package com.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO para Pagamento.
 */
public class PagamentoDTO {

    /**
     * Record para requisição de criação de pagamento.
     */
    public record Requisicao(
            @NotNull(message = "ID do pedido é obrigatório")
            Integer pedidoId,

            @NotNull(message = "Tipo de pagamento é obrigatório")
            Integer tipoPagamentoId,

            @NotNull(message = "Valor é obrigatório")
            @Positive(message = "Valor deve ser positivo")
            BigDecimal valor
    ) {}

    /**
     * Record para resposta de pagamento.
     */
    public record Resposta(
            Integer id,
            Integer pedidoId,
            String tipoPagamento,
            BigDecimal valor,
            String status,
            LocalDateTime dataPagamento
    ) {}
}
