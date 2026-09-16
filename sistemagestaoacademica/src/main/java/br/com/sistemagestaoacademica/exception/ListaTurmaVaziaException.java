package br.com.sistemagestaoacademica.exception;

public class ListaTurmaVaziaException extends RuntimeException {
    public ListaTurmaVaziaException(String message) {
        super(message);
    }
}
