package com.ecommerce.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_value_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogValorProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Produto produto;

    @NotBlank(message = "Ação é obrigatória")
    @Column(name = "action", length = 10, nullable = false)
    private String acao;

    @Column(name = "old_value", precision = 10, scale = 2)
    private BigDecimal valorAntigo;

    @Column(name = "new_value", precision = 10, scale = 2)
    private BigDecimal valorNovo;

    @Column(name = "event_date")
    private LocalDateTime dataEvento;

    @PrePersist
    public void prePersist() {
        this.dataEvento = LocalDateTime.now();
    }
}
