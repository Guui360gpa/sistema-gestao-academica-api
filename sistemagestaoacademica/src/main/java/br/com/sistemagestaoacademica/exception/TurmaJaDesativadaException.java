package br.com.sistemagestaoacademica.exception;

public class TurmaJaDesativadaException extends RuntimeException {
    public TurmaJaDesativadaException(String message) {
        super(message);
    }
}
