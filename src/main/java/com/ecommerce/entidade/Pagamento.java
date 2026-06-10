package com.ecommerce.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private PedidoConsumidor pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private TipoPagamento tipoPagamento;

    @NotNull(message = "Valor é obrigatório")
    @Column(name = "value", precision = 10, scale = 2, nullable = false)
    private BigDecimal valor;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "payment_date")
    private LocalDateTime dataPagamento;

    @PrePersist
    public void prePersist() {
        this.dataPagamento = LocalDateTime.now();
        if (this.status == null) this.status = "PENDENTE";
    }
}
