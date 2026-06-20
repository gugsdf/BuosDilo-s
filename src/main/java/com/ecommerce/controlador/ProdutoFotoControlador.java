package com.ecommerce.controlador;

import com.ecommerce.dto.ProdutoFotoDTO;
import com.ecommerce.servico.ProdutoFotoServico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos/{produtoId}/fotos")
@RequiredArgsConstructor
public class ProdutoFotoControlador {

    private final ProdutoFotoServico servico;

    @GetMapping
    public ResponseEntity<List<ProdutoFotoDTO.Resposta>> listar(@PathVariable Integer produtoId) {
        return ResponseEntity.ok(servico.listarPorProduto(produtoId));
    }

    @PostMapping
    public ResponseEntity<ProdutoFotoDTO.Resposta> criar(
            @PathVariable Integer produtoId,
            @Valid @RequestBody ProdutoFotoDTO.Requisicao requisicao) {
        ProdutoFotoDTO.Resposta r = servico.criar(produtoId, requisicao);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @PutMapping("/{fotoId}")
    public ResponseEntity<ProdutoFotoDTO.Resposta> atualizar(
            @PathVariable Integer produtoId,
            @PathVariable Integer fotoId,
            @Valid @RequestBody ProdutoFotoDTO.Requisicao requisicao) {
        return ResponseEntity.ok(servico.atualizar(produtoId, fotoId, requisicao));
    }

    @PatchMapping("/{fotoId}")
    public ResponseEntity<ProdutoFotoDTO.Resposta> patchUrl(
            @PathVariable Integer produtoId,
            @PathVariable Integer fotoId,
            @RequestBody ProdutoFotoDTO.Requisicao partial) {
        // Reuse atualizar since Requisicao requires fotoUrl; caller can send only fotoUrl
        return ResponseEntity.ok(servico.atualizar(produtoId, fotoId, partial));
    }

    @DeleteMapping("/{fotoId}")
    public ResponseEntity<Void> deletar(
            @PathVariable Integer produtoId,
            @PathVariable Integer fotoId) {
        servico.deletar(produtoId, fotoId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{fotoId}/principal")
    public ResponseEntity<ProdutoFotoDTO.Resposta> definirPrincipal(
            @PathVariable Integer produtoId,
            @PathVariable Integer fotoId) {
        return ResponseEntity.ok(servico.definirPrincipal(produtoId, fotoId));
    }
}

