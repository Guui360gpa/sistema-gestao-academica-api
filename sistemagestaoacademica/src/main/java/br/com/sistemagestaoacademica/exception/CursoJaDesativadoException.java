package br.com.sistemagestaoacademica.exception;

public class CursoJaDesativadoException extends RuntimeException {
    public CursoJaDesativadoException(String message) {
        super(message);
    }
}
