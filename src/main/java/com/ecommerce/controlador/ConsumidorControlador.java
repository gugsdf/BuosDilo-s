package com.ecommerce.controlador;

import com.ecommerce.dto.ConsumidorDTO;
import com.ecommerce.servico.ConsumidorServico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumidores")

// Gera construtor automaticamente (injeção de dependência)
@RequiredArgsConstructor
public class ConsumidorControlador {

    // Serviço responsável pela lógica (injeção automática)
    private final ConsumidorServico servico;


    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint GET /api/consumidores
    // Lista todos os consumidores
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com a lista de consumidores
    @GetMapping
    public ResponseEntity<List<ConsumidorDTO.Resposta>> listarTodos() {
        return ResponseEntity.ok(servico.listarTodos());
    }
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint GET /api/consumidores/{id}
    // Busca um consumidor pelo ID
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com o consumidor encontrado
    @GetMapping("/{id}")
    public ResponseEntity<ConsumidorDTO.Resposta> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servico.buscarPorId(id));
    }
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=


    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint POST /api/consumidores
    // Cria um consumidor
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 201 (Created) com o consumidor criado
    @PostMapping
    public ResponseEntity<ConsumidorDTO.Resposta> criar(@Valid @RequestBody ConsumidorDTO.Requisicao requisicao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servico.criar(requisicao));
    }
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint PUT /api/consumidores/{id}
    // Atualiza um consumidor existente
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com o consumidor atualizado
    @PutMapping("/{id}")
    public ResponseEntity<ConsumidorDTO.Resposta> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ConsumidorDTO.Requisicao requisicao) {
        return ResponseEntity.ok(servico.atualizar(id, requisicao));
    }
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint DELETE /api/consumidores/{id}
    // Deleta um consumidor pelo ID
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 204 (No Content) se a exclusão for bem-suced
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

}
