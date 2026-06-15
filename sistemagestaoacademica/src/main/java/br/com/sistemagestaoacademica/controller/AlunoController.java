package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.models.Aluno;
import br.com.sistemagestaoacademica.repository.AlunoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

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

    private void cadastrarAluno() {
        System.out.println("Digite o nome completo do Aluno:");
        var nomeAluno = read.nextLine();

        System.out.printf("Qual é a data de nascimento do %s:",nomeAluno);
        var dataNascimento = read.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimentoFormat = LocalDate.parse(dataNascimento, formatter);

        System.out.printf("Qual é o email do %s:",nomeAluno);
        var email = read.nextLine();

        String emailRegex = "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);

        while (!pattern.matcher(email).matches()) {
            System.out.print("Email inválido! Digite um email válido (ex: usuario@dominio.com): ");
            email = read.nextLine();
        }

        Aluno aluno = new Aluno(nomeAluno,dataNascimentoFormat,email);

        repository.save(aluno);

        System.out.println("Aluno cadastrado com sucesso!");
    }

    private void matricularAluno() {}

    private void listarAlunosPorTurma() {}

    private void desmatricularAluno() {}
}
