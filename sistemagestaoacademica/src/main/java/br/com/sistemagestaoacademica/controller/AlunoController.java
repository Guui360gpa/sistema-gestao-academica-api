package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.repository.AlunoRepository;

public class AlunoController extends Controller{

    private AlunoRepository repository;

    public AlunoController(AlunoRepository alunoRepository){
        this.repository = alunoRepository;
    }

    public AlunoController() {}

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
                cadastrarAluno();
                break;
            case 2:
                matricularAluno();
                break;
            case 3:
                listarAlunosPorTurma();
                break;
            case 4:
                desmatricularAluno();
                break;

            default:
                break;

        }
    }

    private void cadastrarAluno() {}

    private void matricularAluno() {}

    private void listarAlunosPorTurma() {}

    private void desmatricularAluno() {}
}
