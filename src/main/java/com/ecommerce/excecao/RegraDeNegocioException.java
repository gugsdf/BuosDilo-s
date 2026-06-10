package com.ecommerce.excecao;

/**
 * Exceção para regras de negócio.
 */
public class RegraDeNegocioException extends RuntimeException {

    /**
     * Construtor com mensagem.
     */
    public RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }
}
