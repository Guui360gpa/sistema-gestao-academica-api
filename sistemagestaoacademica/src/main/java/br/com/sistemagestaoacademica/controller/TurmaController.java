package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Professor;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.models.Turma;
import br.com.sistemagestaoacademica.repository.CursoRepository;
import br.com.sistemagestaoacademica.repository.ProfessorRepository;
import br.com.sistemagestaoacademica.repository.TurmaRepository;

import java.util.List;

public class TurmaController extends Controller {

    private TurmaRepository repository;
    private ProfessorRepository professorRepository;
    private CursoRepository cursoRepository;

    public TurmaController(TurmaRepository turmaRepository,ProfessorRepository professorRepository,CursoRepository cursoRepository){
        this.repository = turmaRepository;
        this.professorRepository = professorRepository;
        this.cursoRepository = cursoRepository;
    }

    public TurmaController() {}

    @Override
    public void menu() {
        System.out.println("""
                [1] Nova Turma
                [2] Listar Turmas Ativas
                [3] Listar Turmas Desativadas
                [4] Desativar Turma
                """);
        opcao = read.nextInt();
        read.nextLine();

        switch (opcao){
            case 1:
                novaTurma();
                break;
            case 2:
                listarTurmasAtivas();
                break;
            case 3:
                listarTurmasDesativadas();
                break;
            case 4:
                desativarTurma();
                break;

            default:
                break;
        }
    }

    public void novaTurma() {
        System.out.println("\nDigite o nome da turma: ");
        var nomeTurma = read.nextLine();

        Professor professorEncontrado = null;
        Curso cursoEncontrado = null;
        List<Professor> professoresEncontrados;
        List<Curso> cursosEncontrados;

        System.out.println("\nDigite o professor responsável:");
        while (professorEncontrado == null){
            var nomeProfessor = read.nextLine();
            professoresEncontrados = professorRepository.buscarProfessorPorNome(nomeProfessor);
            if (professoresEncontrados.isEmpty()) {
                System.out.println("\nProfessor não encopntrado! Tente Novamente.");
            }else {
                professoresEncontrados.forEach(p ->
                        System.out.printf("%s - profª %s - %s\n",p.getId(),p.getNome(),p.getEspecialidade()));
                System.out.println("\nDigite o ID do professor desejado: ");
                Long idSelecionado = Long.parseLong(read.nextLine().trim());
                professorEncontrado = professoresEncontrados.stream()
                        .filter(p -> p.getId().equals(idSelecionado))
                        .findFirst()
                        .orElse(null);

                if (professorEncontrado == null) {
                    System.out.println("\nID inválido! Tente novamente:");
                }
            }
        }


        System.out.println("\nDigite o curso que será realizado pela turma:");
        while (cursoEncontrado == null){
            var nomeCurso = read.nextLine();
            cursosEncontrados = cursoRepository.findByNomeContainingIgnoreCaseAndStatus(nomeCurso,Status.ATIVADA);
            if (cursosEncontrados.isEmpty()) {
                System.out.println("\nCurso não encontrado! Tente Novamente.");
            }else {
                cursosEncontrados.forEach(c ->
                        System.out.printf("%s - %s\n",c.getId(),c.getNome()));
                System.out.println("\nDigite o ID do curso desejado: ");
                Long idSelecionado = Long.parseLong(read.nextLine().trim());
                cursoEncontrado = cursosEncontrados.stream()
                        .filter(c -> c.getId().equals(idSelecionado))
                        .findFirst()
                        .orElse(null);

                if (cursoEncontrado == null) {
                    System.out.println("Curso não encontrado.");
                } else if (cursoEncontrado.getStatus() != Status.ATIVADA) {
                    System.out.println("Erro: o curso \"" + cursoEncontrado.getNome() + "\" não está ativo.");
                    cursoEncontrado = null;
                }

                if (cursoEncontrado == null) {
                    System.out.println("\nID inválido! Tente novamente:");
                }
            }
        }

        Turma turma = new Turma(nomeTurma,professorEncontrado,cursoEncontrado);
        repository.save(turma);
        System.out.println("\n Nova turma ativa!");

        }

    public void listarTurmasAtivas() {
        List<Turma> turmasAtivas = repository.findByStatus(Status.ATIVADA);

        if (turmasAtivas.isEmpty()) {
            System.out.println("\nNenhuma turma ativa encontrada.");
            return;
        }

        System.out.println("\n=== Turmas Ativas ===");
        turmasAtivas.forEach(t ->
                System.out.printf("ID: %s | Turma: %s | Professor: %s | Curso: %s\n",
                        t.getId(),
                        t.getNome(),
                        t.getProfessor().getNome(),
                        t.getCurso().getNome())
        );
    }

    private void listarTurmasDesativadas() {
        List<Turma> turmasDesativas = repository.findByStatus(Status.DESATIVADA);

        if (turmasDesativas.isEmpty()) {
            System.out.println("\nNenhuma turma desativada encontrada.");
            return;
        }

        System.out.println("\n=== Turmas Desativadas ===");
        turmasDesativas.forEach(t ->
                System.out.printf("ID: %s | Turma: %s | Professor: %s | Curso: %s\n",
                        t.getId(),
                        t.getNome(),
                        t.getProfessor().getNome(),
                        t.getCurso().getNome())
        );
    }

    private void desativarTurma() {
        Turma turmaEncontrada = null;
        List<Turma> turmasEncontradas;

        System.out.println("Qual turma você deseja DESATIVAR?");
        listarTurmasAtivas();
        while (turmaEncontrada == null){
            var nomeTurma = read.nextLine();
            turmasEncontradas = repository.buscarTurmaAtivaPorNome(nomeTurma,Status.ATIVADA);
            if (turmasEncontradas.isEmpty()) {
                System.out.println("\nTurma ativa não encontrada! Tente Novamente.");
            }else {
                turmasEncontradas.forEach(t ->
                                System.out.printf("ID: %s | Turma: %s | Professor: %s | Curso: %s\n",
                                        t.getId(),
                                        t.getNome(),
                                        t.getProfessor().getNome(),
                                        t.getCurso().getNome()));
                System.out.println("\nDigite o ID da turma que deseja desativar: ");
                Long idSelecionado = Long.parseLong(read.nextLine().trim());
                turmaEncontrada = turmasEncontradas.stream()
                        .filter(p -> p.getId().equals(idSelecionado))
                        .findFirst()
                        .orElse(null);

                if (turmaEncontrada == null) {
                    System.out.println("\nID inválido! Tente novamente:");
                }
            }
        }

        turmaEncontrada.setStatusTurma(Status.DESATIVADA);
        repository.save(turmaEncontrada);
    }
}
