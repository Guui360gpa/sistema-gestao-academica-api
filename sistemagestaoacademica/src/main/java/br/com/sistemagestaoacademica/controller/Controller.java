package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.repository.AlunoRepository;
import br.com.sistemagestaoacademica.repository.CursoRepository;
import br.com.sistemagestaoacademica.repository.ProfessorRepository;
import br.com.sistemagestaoacademica.repository.TurmaRepository;

import java.util.Scanner;

public class Controller {

    protected Scanner read = new Scanner(System.in);
    protected int opcao;

    private AlunoRepository alunoRepository;
    private ProfessorRepository professorRepository;
    private TurmaRepository turmaRepository;
    private CursoRepository cursoRepository;

    public Controller(AlunoRepository alunoRepository, ProfessorRepository professorRepository, TurmaRepository turmaRepository,CursoRepository cursoRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.turmaRepository = turmaRepository;
        this.cursoRepository = cursoRepository;
    }

    public Controller() {}

    public void menu(){
        while (true) {
            System.out.println("-----------------------------------");
            System.out.println("    Sistema de Gestão Acadêmica     ");
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
                case 1:
                    AlunoController alunoMain = new AlunoController(alunoRepository);
                    alunoMain.menu();
                    break;
                case 2:
                    ProfessorController professorMain = new ProfessorController(professorRepository);
                    professorMain.menu();
                    break;
                case 3:
                    CursoController cursoController = new CursoController(cursoRepository);
                    cursoController.menu();
                    break;
                case 4:
                    TurmaController turmaMain = new TurmaController(turmaRepository,professorRepository,cursoRepository);
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
