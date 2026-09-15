package br.com.sistemagestaoacademica.exception;

public class CursoNaoEncontradoException extends RuntimeException {
    public CursoNaoEncontradoException(String message) {
        super(message);
    }
}
