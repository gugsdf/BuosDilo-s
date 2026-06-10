package com.ecommerce.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Entidade EnderecoConsumidor.
 */
@Entity
@Table(name = "consumer_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoConsumidor {

    /** ID do endereço. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer id;

    /** Consumidor associado. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id", nullable = false)
    private Consumidor consumidor;

    /** CEP do endereço. */
    @NotBlank(message = "CEP é obrigatório")
    @Column(name = "cep", length = 10, nullable = false)
    private String cep;

    /** Número do endereço. */
    @Column(name = "number", length = 10)
    private String numero;

    /** Cidade do endereço. */
    @Column(name = "city", length = 50)
    private String cidade;

    /** Estado do endereço. */
    @Column(name = "state", length = 50)
    private String estado;

    /** Complemento do endereço. */
    @Column(name = "complement", length = 50)
    private String complemento;
}
