package br.com.sistemagestaoacademica.principal;

import java.util.Scanner;

public class Main {

    protected Scanner read = new Scanner(System.in);
    protected int opcao;

    public void menu(){
        while (true) {
            System.out.println("-----------------------------------");
            System.out.println("    Sistema de Gestão Acadêmica     ");
            System.out.println("-----------------------------------");
            System.out.println("""
                    
                    [1] Aluno
                    [2] Professor
                    [3] Disciplina
                    [4] Turma
                    
                    [0] Sair
                    
                    """);

            opcao = read.nextInt();
            read.nextLine();

            switch (opcao) {
                case 1:
                    AlunoMain alunoMain = new AlunoMain();
                    alunoMain.menu();
                    break;
                case 2:
                    ProfessorMain professorMain = new ProfessorMain();
                    professorMain.menu();
                    break;
                case 3:
                    break;
                case 4:
                    TurmaMain turmaMain = new TurmaMain();
                    turmaMain.menu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }

            if(opcao == 0){
                System.out.println("Saindo...");
                break;
            }
        }
    }
}
