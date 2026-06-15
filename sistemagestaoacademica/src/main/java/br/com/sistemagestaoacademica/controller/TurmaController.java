package br.com.sistemagestaoacademica.controller;

import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Professor;
import br.com.sistemagestaoacademica.models.Turma;
import br.com.sistemagestaoacademica.repository.CursoRepository;
import br.com.sistemagestaoacademica.repository.ProfessorRepository;
import br.com.sistemagestaoacademica.repository.TurmaRepository;
import br.com.sistemagestaoacademica.service.ProfessorNotFoundExcepition;

import java.util.ArrayList;
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
                desativarTurmas();
                break;

            default:
                break;
        }
    }

    private void novaTurma() {
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
            cursosEncontrados = cursoRepository.findByNomeContainingIgnoreCase(nomeCurso);
            if (cursosEncontrados.isEmpty()) {
                System.out.println("\nCurso não encontrado! Tente Novamente.");
            }else {
                cursosEncontrados.forEach(c ->
                        System.out.printf("%s - %s\n",c.getId(),c.getNome()));
                System.out.println("\nDigite o ID do curso desejado: ");
                Long idSelecionado = Long.parseLong(read.nextLine().trim());
                cursoEncontrado = cursosEncontrados.stream()
                        .filter(p -> p.getId().equals(idSelecionado))
                        .findFirst()
                        .orElse(null);

                if (cursoEncontrado == null) {
                    System.out.println("\nID inválido! Tente novamente:");
                }
            }
        }

        Turma turma = new Turma(nomeTurma,professorEncontrado,cursoEncontrado);
        repository.save(turma);
        System.out.println("\n Nova turma ativa!");

        }

    private void listarTurmasAtivas() {}

    private void listarTurmasDesativadas() {}

    private void desativarTurmas() {}
}
