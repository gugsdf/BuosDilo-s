package com.ecommerce.excecao;

/**
 * Exceção para recurso não encontrado.
 */
public class RecursoNaoEncontradoException extends RuntimeException {

    /**
     * Construtor com mensagem.
     */
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    /**
     * Construtor com recurso e id.
     */
    public RecursoNaoEncontradoException(String recurso, Integer id) {
        super(recurso + " não encontrado(a) com id: " + id);
    }
}
