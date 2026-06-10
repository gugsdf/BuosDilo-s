package com.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO para PedidoConsumidor.
 */
public class PedidoConsumidorDTO {

    /**
     * Record para requisição de criação de pedido de consumidor.
     */
    public record Requisicao(
            @NotNull(message = "ID do consumidor é obrigatório")
            Integer consumidorId,

            List<ItemPedidoDTO.Requisicao> itens
    ) {}

    /**
     * Record para resposta de pedido de consumidor.
     */
    public record Resposta(
            Integer id,
            Integer consumidorId,
            String nomeConsumidor,
            LocalDateTime dataPedido,
            String status,
            BigDecimal valorTotal,
            List<ItemPedidoDTO.Resposta> itens
    ) {}
}
