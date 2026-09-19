package br.com.sistemagestaoacademica.exception;

public class UsuarioInativoException extends RuntimeException {
    public UsuarioInativoException(String message) {
        super(message);
    }
}
