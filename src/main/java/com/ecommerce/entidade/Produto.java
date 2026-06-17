package com.ecommerce.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private Integer id;

    @NotBlank(message = "Nome do produto é obrigatório")
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @NotNull(message = "Preço é obrigatório")
    @Positive(message = "Preço deve ser positivo")
    @Column(name = "preco", precision = 10, scale = 2, nullable = false)
    private BigDecimal preco;

    @Column(name = "sku", length = 50)
    private String sku;

    @Column(name = "ativo", nullable = false)
    @Builder.Default
    private Boolean ativo = true;

    // O banco já possui a coluna `imagem` como type `text` (PostgreSQL).
    // `@Lob` faria o Hibernate esperar um CLOB (oid) — causa erro de validação.
    // Mapear explicitamente como `text` faz o Hibernate esperar VARCHAR/TEXT.
    @Column(name = "imagem", columnDefinition = "text")
    private String image;



}
