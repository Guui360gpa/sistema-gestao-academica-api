package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.repository.ProfessorRepository;

public class ProfessorController extends Controller {

    private ProfessorRepository repository;

    public ProfessorController(ProfessorRepository professorRepository){
        this.repository = professorRepository;
    }

    public ProfessorController() {}

    @Override
    public void menu() {
        System.out.println("""
                
                [1] Cadastrar Professor 
                [2] Associar Professor
                [3] Listar Professores
                [4] Desassociar Professor
                
                """);
        opcao = read.nextInt();
        read.nextLine();

        switch (opcao){
            case 1:
                cadastrarProfessor();
                break;
            case 2:
                acossiarProfessorEmTurma();
                break;
            case 3:
                listarProfessores();
                break;
            case 4:
                desassociarProfessorEmTurma();
                break;

            default:
                break;
        }
    }

    private void cadastrarProfessor() {}

    private void acossiarProfessorEmTurma() {}

    private void listarProfessores() {}

    private void desassociarProfessorEmTurma() {}
}
