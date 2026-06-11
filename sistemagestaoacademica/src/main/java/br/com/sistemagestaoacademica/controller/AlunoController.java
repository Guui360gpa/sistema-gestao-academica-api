package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.repository.AlunoRepository;
import br.com.sistemagestaoacademica.repository.ProfessorRepository;
import br.com.sistemagestaoacademica.repository.TurmaRepository;

public class AlunoController extends Controller {

    private AlunoRepository alunoRepository;

    public AlunoController(AlunoRepository alunoRepository, ProfessorRepository professorRepository, TurmaRepository turmaRepository) {
        super(alunoRepository, professorRepository, turmaRepository);
        this.alunoRepository = alunoRepository;
    }

    @Override
    public void menu(){
        System.out.println("""

                [1] Cadastrar Aluno
                [2] Matricular Aluno
                [3] Listar Alunos Por Turma
                [4] Desmatricular Aluno
                
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
