package com.ecommerce.controlador;

import com.ecommerce.entidade.TipoPagamento;
import com.ecommerce.excecao.RecursoNaoEncontradoException;
import com.ecommerce.repositorio.TipoPagamentoRepositorio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-pagamento")
@RequiredArgsConstructor
public class TipoPagamentoControlador {

    private final TipoPagamentoRepositorio repositorio;

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint GET /api/tipos-pagamento
    // Lista todos os tipos de pagamento
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com a lista de tipos de pagamento
    @GetMapping
    public ResponseEntity<List<TipoPagamento>> listarTodos() {
        return ResponseEntity.ok(repositorio.findAll());
    }

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint GET /api/tipos-pagamento/{id}
    // Busca um tipo de pagamento pelo ID
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com o tipo de pagamento encontrado
    @GetMapping("/{id}")
    public ResponseEntity<TipoPagamento> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(repositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tipo de pagamento", id)));
    }

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint POST /api/tipos-pagamento
    // Cria um tipo de pagamento
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 201 (Created) com o tipo de pagamento criado
    @PostMapping
    public ResponseEntity<TipoPagamento> criar(@RequestBody @Valid RequisicaoTipo requisicao) {
        TipoPagamento tipo = TipoPagamento.builder()
                .nome(requisicao.nome())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(tipo));
    }

    public record RequisicaoTipo(@NotBlank String nome) {}
}
