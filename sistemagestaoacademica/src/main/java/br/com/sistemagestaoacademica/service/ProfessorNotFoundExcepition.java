package br.com.sistemagestaoacademica.service;

import br.com.sistemagestaoacademica.models.Professor;

public class ProfessorNotFoundExcepition extends RuntimeException {
    public ProfessorNotFoundExcepition(String nome) {
        super("Professor com nome" + nome + " não encontrado.");
    }
}
