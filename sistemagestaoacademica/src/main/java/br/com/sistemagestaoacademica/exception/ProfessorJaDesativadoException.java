package br.com.sistemagestaoacademica.exception;

public class ProfessorJaDesativadoException extends RuntimeException {
    public ProfessorJaDesativadoException(String message) {
        super(message);
    }
}
