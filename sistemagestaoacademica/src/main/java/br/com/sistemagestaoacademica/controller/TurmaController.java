package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.repository.AlunoRepository;
import br.com.sistemagestaoacademica.repository.ProfessorRepository;
import br.com.sistemagestaoacademica.repository.TurmaRepository;

public class TurmaController extends Controller {

    private TurmaRepository turmaRepository;

    public TurmaController(AlunoRepository alunoRepository, ProfessorRepository professorRepository, TurmaRepository turmaRepository) {
        super(alunoRepository, professorRepository, turmaRepository);
        this.turmaRepository = turmaRepository;
    }

    @Override
    public void menu() {
        System.out.println("""
                [1] Nova Turma
                [2] Listar Turmas Ativas
                [3] Desativar Turma
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

            default:
                break;
        }
    }
}
