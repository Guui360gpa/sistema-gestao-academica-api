package br.com.sistemagestaoacademica.service.turma;

import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Professor;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.models.Turma;
import br.com.sistemagestaoacademica.service.BaseService;
import java.util.List;

@org.springframework.stereotype.Service
public class NovaTurma extends BaseService {
    public void nova() {
        System.out.println("\nDigite o nome da turma: ");
        var nomeTurma = read.nextLine();

        Professor professorEncontrado = null;
        Curso cursoEncontrado = null;
        List<Professor> professoresEncontrados;
        List<Curso> cursosEncontrados;

        System.out.println("\nDigite o professor responsável:");
        while (professorEncontrado == null) {
            var nomeProfessor = read.nextLine().trim();

            if (nomeProfessor.isEmpty()) continue;

            professoresEncontrados = professorRepository.buscarProfessorPorNome(nomeProfessor);
            if (professoresEncontrados.isEmpty()) {
                System.out.println("\nProfessor não encontrado! Tente Novamente.");
            } else {
                professoresEncontrados.forEach(p ->
                        System.out.printf("%s - profª %s - %s\n", p.getId(), p.getNome(), p.getEspecialidade()));
                System.out.println("\nDigite o ID do professor desejado: ");
                Long idSelecionado = lerLong();
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
        while (cursoEncontrado == null) {
            var nomeCurso = read.nextLine().trim();

            if (nomeCurso.isEmpty()) continue;

            cursosEncontrados = cursoRepository.findByNomeContainingIgnoreCaseAndStatus(nomeCurso, Status.ATIVADA);
            if (cursosEncontrados.isEmpty()) {
                System.out.println("\nCurso não encontrado!");
                return;
            } else {
                cursosEncontrados.forEach(c ->
                        System.out.printf("%s - %s\n", c.getId(), c.getNome()));
                System.out.println("\nDigite o ID do curso desejado: ");
                Long idSelecionado = lerLong();
                cursoEncontrado = cursosEncontrados.stream()
                        .filter(c -> c.getId().equals(idSelecionado))
                        .findFirst()
                        .orElse(null);

                if (cursoEncontrado == null) {
                    System.out.println("\nID inválido! Voltando ao menu principal...");
                    return;
                }
            }
        }

        Turma turma = new Turma(nomeTurma, professorEncontrado, cursoEncontrado);
        turmaRepository.save(turma);
        System.out.println("\nNova turma criada com sucesso!");
    }
}