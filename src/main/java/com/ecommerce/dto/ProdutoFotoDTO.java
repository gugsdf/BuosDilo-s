package com.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

public class ProdutoFotoDTO {

    public record Requisicao(
            @NotBlank(message = "fotoUrl é obrigatório")
            String fotoUrl,
            String lado,
            Integer ordem
    ) {}

    public record Resposta(
            Integer id,
            Integer produtoId,
            String fotoUrl,
            String lado,
            Integer ordem
    ) {}

}

