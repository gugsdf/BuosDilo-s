package com.ecommerce.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "consumer_phone")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefoneConsumidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id", nullable = false)
    private Consumidor consumidor;

    @NotBlank(message = "Número de telefone é obrigatório")
    @Column(name = "phone_number", length = 20, nullable = false)
    private String numeroTelefone;
}
