package br.com.sistemagestaoacademica.tests;

import br.com.sistemagestaoacademica.models.Aluno;

import java.time.LocalDate;

public class TesteAluno {
    public static void main(String[] args) {
        Aluno aluno = new Aluno();
        aluno.setNome("Guilherme Paiva Alves");
        aluno.setRa(4960L);
        aluno.setDataNascimento(LocalDate.parse("2008-02-10"));
        aluno.setEmail("gpa8906@reducar.osasco.com.br");

        System.out.println(aluno);
    }
}
