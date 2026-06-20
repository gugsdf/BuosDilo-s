package com.ecommerce.controlador;

import com.ecommerce.dto.ProdutoDTO;
import com.ecommerce.servico.ProdutoServico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoControlador {

    private final ProdutoServico servico;

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint GET /api/produtos
    // Lista todos os produtos ou apenas os ativos se apenasAtivos=true
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com a lista de produtos
    @GetMapping
    public ResponseEntity<List<ProdutoDTO.Resposta>> listarTodos(
            @RequestParam(required = false, defaultValue = "false") boolean apenasAtivos) {
        if (apenasAtivos) {
            return ResponseEntity.ok(servico.listarAtivos());
        }
        return ResponseEntity.ok(servico.listarTodos());
    }

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint GET /api/produtos/{id}
    // Busca um produto pelo ID
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com o produto encontrado
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO.Resposta> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(servico.buscarPorId(id));
    }

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint POST /api/produtos
    // Cria um produto
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 201 (Created) com o produto criado
    @PostMapping
    public ResponseEntity<ProdutoDTO.Resposta> criar(@Valid @RequestBody ProdutoDTO.Requisicao requisicao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servico.criar(requisicao));
    }

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint PUT /api/produtos/{id}
    // Atualiza um produto existente
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 200 (OK) com o produto atualizado
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO.Resposta> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ProdutoDTO.Requisicao requisicao) {
        return ResponseEntity.ok(servico.atualizar(id, requisicao));
    }

    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Endpoint PATCH /api/produtos/{id}/inativar
    // Inativa um produto
    // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    // Retorna status 204 (No Content) se a inativação for bem-sucedida
    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Integer id) {
        servico.inativar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/imagem")
    public ResponseEntity<com.ecommerce.dto.ProdutoDTO.Resposta> atualizarImagem(
            @PathVariable Integer id,
            @RequestBody Map<String, String> body) {
        String image = body.get("image");
        return ResponseEntity.ok(servico.atualizarImagem(id, image));
    }
}
