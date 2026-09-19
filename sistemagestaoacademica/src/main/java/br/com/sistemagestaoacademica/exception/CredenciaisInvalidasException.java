package br.com.sistemagestaoacademica.exception;

public class CredenciaisInvalidasException extends RuntimeException {
    public CredenciaisInvalidasException(String message) {
        super(message);
    }
}
