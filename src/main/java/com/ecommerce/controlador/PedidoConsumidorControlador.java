package com.ecommerce.controlador;

import com.ecommerce.dto.PedidoConsumidorDTO;
import com.ecommerce.servico.PedidoConsumidorServico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoConsumidorControlador {

    private final PedidoConsumidorServico servico;

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint GET /api/pedidos
    // Lista todos os pedidos
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com a lista de pedidos
    @GetMapping
    public ResponseEntity<List<PedidoConsumidorDTO.Resposta>> listarTodos() {
        return ResponseEntity.ok(servico.listarTodos());
    }

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint GET /api/pedidos/{id}
    // Busca um pedido pelo ID
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com o pedido encontrado
    @GetMapping("/{id}")
    public ResponseEntity<PedidoConsumidorDTO.Resposta> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servico.buscarPorId(id));
    }

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint GET /api/pedidos/consumidor/{consumidorId}
    // Lista pedidos por consumidor
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com a lista de pedidos do consumidor
    @GetMapping("/consumidor/{consumidorId}")
    public ResponseEntity<List<PedidoConsumidorDTO.Resposta>> listarPorConsumidor(@PathVariable Integer consumidorId) {
        return ResponseEntity.ok(servico.listarPorConsumidor(consumidorId));
    }

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint POST /api/pedidos
    // Cria um pedido
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 201 (Created) com o pedido criado
    @PostMapping
    public ResponseEntity<PedidoConsumidorDTO.Resposta> criar(@Valid @RequestBody PedidoConsumidorDTO.Requisicao requisicao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servico.criar(requisicao));
    }

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint PATCH /api/pedidos/{id}/status
    // Atualiza o status de um pedido
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com o pedido atualizado
    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoConsumidorDTO.Resposta> atualizarStatus(
            @PathVariable Integer id,
            @RequestParam String status) {
        return ResponseEntity.ok(servico.atualizarStatus(id, status));
    }
}
