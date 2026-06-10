package com.ecommerce.entidade;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "delivery")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private PedidoConsumidor pedido;

    @Column(name = "cep", length = 10)
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
