package com.ecommerce.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "endereco_estoque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "endereco_estoque_id")
    private Integer id;

    @NotBlank(message = "CEP é obrigatório")
    @Column(name = "cep", length = 10, nullable = false)
    private String cep;

    @Column(name = "numero", length = 10)
    private String numero;

    @Column(name = "cidade", length = 50)
    private String cidade;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "complemento", length = 50)
    private String complemento;
}
