package br.com.sistemagestaoacademica.controller;


import br.com.sistemagestaoacademica.service.aluno.CadastrarAluno;
import br.com.sistemagestaoacademica.service.aluno.ListarAlunoPorTurma;
import br.com.sistemagestaoacademica.service.aluno.MatricularAluno;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Controller
public class AlunoBaseController extends BaseController {

    @Autowired
    private CadastrarAluno cadastrarAluno;

    @Autowired
    private MatricularAluno matricularAluno;

    @Autowired
    ListarAlunoPorTurma listarAlunoPorTurma;


    public void menu() {
        System.out.println("""
                
                [1] Cadastrar Aluno
                [2] Matricular Aluno
                [3] Listar Alunos Por Turma
                
                """);
        opcao = read.nextInt();
        read.nextLine();


        switch (opcao) {
            case 1:
                cadastrarAluno.cadastrar();
                break;
            case 2:
                matricularAluno.matricular();
                break;
            case 3:
                listarAlunoPorTurma.listar();
                break;

            default:
                break;

        }
    }
}
