package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.repository.AlunoRepository;
import br.com.sistemagestaoacademica.repository.ProfessorRepository;
import br.com.sistemagestaoacademica.repository.TurmaRepository;

public class ProfessorController extends Controller {

    private ProfessorRepository professorRepository;

    public ProfessorController(AlunoRepository alunoRepository, ProfessorRepository professorRepository, TurmaRepository turmaRepository) {
        super(alunoRepository, professorRepository, turmaRepository);
        this.professorRepository = professorRepository;
    }

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
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;

            default:
                break;
        }
    }
}
