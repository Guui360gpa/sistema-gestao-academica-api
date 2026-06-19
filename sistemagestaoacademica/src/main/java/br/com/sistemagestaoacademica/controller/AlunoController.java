package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.models.Aluno;
import br.com.sistemagestaoacademica.models.Matricula;
import br.com.sistemagestaoacademica.models.Turma;
import br.com.sistemagestaoacademica.repository.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Pattern;

public class AlunoController extends Controller {

    private AlunoRepository repository;
    private TurmaRepository turmaRepository;
    private MatriculaRepository matriculaRepository;
    private TurmaController turmaController;

    public AlunoController(AlunoRepository alunoRepository,
                           TurmaRepository turmaRepository,
                           MatriculaRepository matriculaRepository,
                           ProfessorRepository professorRepository,
                           CursoRepository cursoRepository) {
        this.repository = alunoRepository;
        this.turmaRepository = turmaRepository;
        this.turmaController = new TurmaController(turmaRepository, professorRepository, cursoRepository);
        this.matriculaRepository = matriculaRepository;
    }

    public AlunoController() {
    }

    @Override
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
                cadastrarAluno();
                break;
            case 2:
                matricularAluno();
                break;
            case 3:
                listarAlunosPorTurma();
                break;

            default:
                break;

        }
    }

    private void cadastrarAluno() {
        System.out.println("Digite o nome completo do Aluno:");
        var nomeAluno = read.nextLine();

        System.out.printf("Qual é a data de nascimento do %s:", nomeAluno);
        var dataNascimento = read.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimentoFormat = LocalDate.parse(dataNascimento, formatter);

        System.out.printf("Qual é o email do %s:", nomeAluno);
        var email = read.nextLine();

        String emailRegex = "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);

        while (!pattern.matcher(email).matches()) {
            System.out.print("Email inválido! Digite um email válido (ex: usuario@dominio.com): ");
            email = read.nextLine();
        }

        Aluno aluno = new Aluno(nomeAluno, dataNascimentoFormat, email);

        repository.save(aluno);

        System.out.println("Aluno cadastrado com sucesso!");
    }

    private void matricularAluno() {
        Aluno alunoEncontrado = null;
        List<Aluno> alunosEncontrados;

        System.out.println("\nQual aluno você deseja matricular ?");
        while (alunoEncontrado == null) {
            var nomeAluno = read.nextLine();
            alunosEncontrados = repository.buscarAlunoPorNome(nomeAluno);
            if (alunosEncontrados.isEmpty()) {
                System.out.println("\nAluno não encontrado ! Deseja cadastra-lo?(s/n)");
                var escolha = read.nextLine();
                if (escolha.equalsIgnoreCase("s")) {
                    cadastrarAluno();
                } else {
                    return;
                }
            } else {
                alunosEncontrados.forEach(a ->
                        System.out.printf("%s - %s - %s\n", a.getRa(), a.getNome(), a.getEmail()));
                System.out.println("\nDigite o RA do aluno desejado: ");
                Long idSelecionado = Long.parseLong(read.nextLine().trim());
                alunoEncontrado = alunosEncontrados.stream()
                        .filter(a -> a.getRa().equals(idSelecionado))
                        .findFirst()
                        .orElse(null);

                if (alunoEncontrado == null) {
                    System.out.println("\nRA inválido! Tente novamente:");
                }
            }
        }

        Turma turmaEncontrada = null;
        List<Turma> turmasEncontradas;

        System.out.printf("\nA qual turma deseja matricular %s ?\n", alunoEncontrado.getNome());
        this.turmaController.listarTurmasAtivas();

        System.out.println("\n: ");
        while (turmaEncontrada == null) {
            var nomeTurma = read.nextLine();
            turmasEncontradas = turmaRepository.buscarTurmaPorNome(nomeTurma);
            if (turmasEncontradas.isEmpty()) {
                System.out.println("\nTurma não encontrada ! Deseja cadastrar uma nova turma?(s/n)");
                var escolha = read.nextLine();
                if (escolha.equalsIgnoreCase("s")) {
                    this.turmaController.novaTurma();
                } else {
                    return;
                }
            } else {
                turmasEncontradas.forEach(a ->
                        System.out.printf("%s - %s - %s - %s\n", a.getId(), a.getNome(), a.getProfessor().getNome(), a.getCurso().getNome()));
                System.out.println("\nDigite o ID da turma desejada: ");
                Long idSelecionado = Long.parseLong(read.nextLine().trim());
                turmaEncontrada = turmasEncontradas.stream()
                        .filter(a -> a.getId().equals(idSelecionado))
                        .findFirst()
                        .orElse(null);

                if (turmaEncontrada == null) {
                    System.out.println("\nID inválido! Tente novamente:"); // ← sem return, loop continua
                } else if (matriculaRepository.existsByAlunoIdAndTurmaId(alunoEncontrado.getRa(), turmaEncontrada.getId())) {
                    System.out.println("Erro: " + alunoEncontrado.getNome() + " já está matriculado na turma " + turmaEncontrada.getNome() + "!");
                    return;
                }
            }
        }

        matriculaRepository.save(new Matricula(alunoEncontrado, turmaEncontrada));
        System.out.println("Aluno matriculado com sucesso!");
    }

    private void listarAlunosPorTurma() {
        turmaController.listarTurmasAtivas();
        System.out.println("\nDigite o ID da turma desejada: ");
        Long idSelecionado = Long.parseLong(read.nextLine().trim());


        List<Aluno> alunosTurma = matriculaRepository.buscarAlunosPorTurma(idSelecionado);

        alunosTurma.forEach(a ->
                System.out.printf("RA: %s |Nome: %s |Email: %s\n",
                        a.getRa(),
                        a.getNome(),
                        a.getEmail()));
    }


}
