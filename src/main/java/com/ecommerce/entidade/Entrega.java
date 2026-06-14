package com.ecommerce.entidade;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entrega")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrega_id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private PedidoConsumidor pedido;

    @Column(name = "cep", length = 10)
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
