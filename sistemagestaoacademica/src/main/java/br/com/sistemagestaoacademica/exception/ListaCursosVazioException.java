package br.com.sistemagestaoacademica.exception;

public class ListaCursosVazioException extends RuntimeException {
    public ListaCursosVazioException(String message) {
        super(message);
    }
}
