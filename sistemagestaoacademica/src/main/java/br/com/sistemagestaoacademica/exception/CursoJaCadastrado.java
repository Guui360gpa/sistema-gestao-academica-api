package br.com.sistemagestaoacademica.exception;

public class CursoJaCadastrado extends RuntimeException {
    public CursoJaCadastrado(String message) {
        super(message);
    }
}
