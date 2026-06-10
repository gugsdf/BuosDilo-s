package com.ecommerce.controlador;

import com.ecommerce.dto.EntregaDTO;
import com.ecommerce.servico.EntregaServico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/entregas")
@RequiredArgsConstructor
public class EntregaControlador {

    private final EntregaServico servico;

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint GET /api/entregas
    // Lista todas as entregas
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com a lista de entregas
    @GetMapping
    public ResponseEntity<List<EntregaDTO.Resposta>> listarTodas() {
        return ResponseEntity.ok(servico.listarTodas());
    }

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint GET /api/entregas/{id}
    // Busca uma entrega pelo ID
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com a entrega encontrada
    @GetMapping("/{id}")
    public ResponseEntity<EntregaDTO.Resposta> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servico.buscarPorId(id));
    }

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint POST /api/entregas
    // Cria uma entrega
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 201 (Created) com a entrega criada
    @PostMapping
    public ResponseEntity<EntregaDTO.Resposta> criar(@Valid @RequestBody EntregaDTO.Requisicao requisicao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servico.criar(requisicao));
    }
}
