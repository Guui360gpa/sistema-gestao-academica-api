package br.com.sistemagestaoacademica.exception;

public class ProfessorNaoEncontradoException extends RuntimeException {
    public ProfessorNaoEncontradoException(String message) {
        super(message);
    }
}
