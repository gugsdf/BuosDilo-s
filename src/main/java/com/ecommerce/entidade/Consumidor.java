package com.ecommerce.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

/**
 * Entidade Consumidor.
 */
@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consumidor {

    /** ID do consumidor. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    private Integer id;

    /** Nome do consumidor. */
    @NotBlank(message = "Nome é obrigatório")
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    /** E-mail do consumidor. */
    @Email(message = "E-mail inválido")
    @NotBlank(message = "E-mail é obrigatório")
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    /** Senha do consumidor. */
    @NotBlank(message = "Senha é obrigatória")
    @Column(name = "senha", length = 255, nullable = false)
    private String senha;

    /** Lista de telefones do consumidor. */
    @OneToMany(mappedBy = "consumidor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TelefoneConsumidor> telefones;

    /** Lista de endereços do consumidor. */
    @OneToMany(mappedBy = "consumidor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EnderecoConsumidor> enderecos;

    /** Lista de pedidos do consumidor. */
    @OneToMany(mappedBy = "consumidor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PedidoConsumidor> pedidos;
}
