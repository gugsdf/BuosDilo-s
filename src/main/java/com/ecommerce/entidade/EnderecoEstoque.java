package com.ecommerce.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "stock_address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_address_id")
    private Integer id;

    @NotBlank(message = "CEP é obrigatório")
    @Column(name = "cep", length = 10, nullable = false)
    private String cep;

    @Column(name = "number", length = 10)
    private String numero;

    @Column(name = "city", length = 50)
    private String cidade;

    @Column(name = "state", length = 50)
    private String estado;

    @Column(name = "complement", length = 50)
    private String complemento;
}
