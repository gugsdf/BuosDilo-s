package com.ecommerce.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Entidade EnderecoConsumidor.
 */
@Entity
@Table(name = "endereco_cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoConsumidor {

    /** ID do endereço. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "endereco_id")
    private Integer id;

    /** Consumidor associado. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Consumidor consumidor;

    /** CEP do endereço. */
    @NotBlank(message = "CEP é obrigatório")
    @Column(name = "cep", length = 10, nullable = false)
    private String cep;

    /** Número do endereço. */
    @Column(name = "numero", length = 10)
    private String numero;

    /** Cidade do endereço. */
    @Column(name = "cidade", length = 50)
    private String cidade;

    /** Estado do endereço. */
    @Column(name = "estado", length = 50)
    private String estado;

    /** Complemento do endereço. */
    @Column(name = "complemento", length = 50)
    private String complemento;
}
