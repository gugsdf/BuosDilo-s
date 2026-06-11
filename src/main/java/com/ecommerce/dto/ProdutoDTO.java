package com.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * DTO para Produto.
 */
public class ProdutoDTO {

    /**
     * Record para requisição de criação/atualização de produto.
     */
    public record Requisicao(
            @NotBlank(message = "Nome é obrigatório")
            String nome,

            @NotNull(message = "Preço é obrigatório")
            @Positive(message = "Preço deve ser positivo")
            BigDecimal preco,

            String sku,

            Boolean ativo,

            String image
    ) {}

    /**
     * Record para resposta de produto.
     */
    public record Resposta(
            Integer id,
            String nome,
            BigDecimal preco,
            String sku,
            Boolean ativo,

            String image
    ) {}
}
