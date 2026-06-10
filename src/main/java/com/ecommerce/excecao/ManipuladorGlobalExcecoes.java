package com.ecommerce.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Manipulador global de exceções.
 */
@RestControllerAdvice
public class ManipuladorGlobalExcecoes {

    /**
     * Trata RecursoNaoEncontradoException.
     */
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<RespostaErro> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
        RespostaErro erro = new RespostaErro(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    /**
     * Trata RegraDeNegocioException.
     */
    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<RespostaErro> handleRegraDeNegocio(RegraDeNegocioException ex) {
        RespostaErro erro = new RespostaErro(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    /**
     * Trata MethodArgumentNotValidException.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String campo = ((FieldError) error).getField();
            String mensagem = error.getDefaultMessage();
            erros.put(campo, mensagem);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    /**
     * Trata exceções gerais.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaErro> handleErroGeral(Exception ex) {
        RespostaErro erro = new RespostaErro(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor: " + ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    /**
     * Record para resposta de erro.
     */
    public record RespostaErro(int status, String mensagem, LocalDateTime timestamp) {}
}
