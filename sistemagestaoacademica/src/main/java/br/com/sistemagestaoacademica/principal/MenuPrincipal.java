package br.com.sistemagestaoacademica.principal;

import br.com.sistemagestaoacademica.controller.AlunoBaseController;
import br.com.sistemagestaoacademica.controller.CursoBaseController;
import br.com.sistemagestaoacademica.controller.ProfessorBaseController;
import br.com.sistemagestaoacademica.controller.TurmaBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuPrincipal {
    @Autowired private AlunoBaseController alunoController;
    @Autowired private ProfessorBaseController professorController;
    @Autowired
    private CursoBaseController cursoController;
    @Autowired private TurmaBaseController turmaController;

    private final Scanner read = new Scanner(System.in);

    public void menu() {
        int opcao;
        while (true) {
            System.out.println("-----------------------------------");
            System.out.println("    Sistema de Gestão Acadêmica    ");
            System.out.println("-----------------------------------");
            System.out.println("""
                    [1] Aluno
                    [2] Professor
                    [3] Curso
                    [4] Turma
                    [0] Sair
                    """);

            opcao = read.nextInt();
            read.nextLine();

            switch (opcao) {
                case 1 -> alunoController.menu();
                case 2 -> professorController.menu();
                case 3 -> cursoController.menu();
                case 4 -> turmaController.menu();
                case 0 -> { System.out.println("Saindo..."); return; }
                default -> System.out.println("Opção Inválida!");
            }
        }
    }
}
