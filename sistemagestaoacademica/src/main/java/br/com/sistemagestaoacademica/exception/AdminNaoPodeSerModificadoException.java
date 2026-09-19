package br.com.sistemagestaoacademica.exception;

public class AdminNaoPodeSerModificadoException extends RuntimeException {
    public AdminNaoPodeSerModificadoException(String message) {
        super(message);
    }
}
