package com.ecommerce.controlador;

import com.ecommerce.dto.PagamentoDTO;
import com.ecommerce.servico.PagamentoServico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagamentos")
@RequiredArgsConstructor
public class PagamentoControlador {

    private final PagamentoServico servico;

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint GET /api/pagamentos
    // Lista todos os pagamentos
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com a lista de pagamentos
    @GetMapping
    public ResponseEntity<List<PagamentoDTO.Resposta>> listarTodos() {
        return ResponseEntity.ok(servico.listarTodos());
    }

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint GET /api/pagamentos/{id}
    // Busca um pagamento pelo ID
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com o pagamento encontrado
    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO.Resposta> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servico.buscarPorId(id));
    }

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint POST /api/pagamentos
    // Cria um pagamento
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 201 (Created) com o pagamento criado
    @PostMapping
    public ResponseEntity<PagamentoDTO.Resposta> criar(@Valid @RequestBody PagamentoDTO.Requisicao requisicao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servico.criar(requisicao));
    }
}
