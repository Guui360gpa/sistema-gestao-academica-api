package br.com.sistemagestaoacademica.service.aluno;

import br.com.sistemagestaoacademica.models.Aluno;
import br.com.sistemagestaoacademica.models.Matricula;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.models.Turma;
import br.com.sistemagestaoacademica.service.BaseService;
import br.com.sistemagestaoacademica.service.turma.ListarTurmasAtivas;
import br.com.sistemagestaoacademica.service.turma.NovaTurma;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class MatricularAluno extends BaseService {

    @Autowired
    private CadastrarAluno cadastrarAluno;
    @Autowired
    private ListarTurmasAtivas listarTurmasAtivas;
    @Autowired
    private NovaTurma novaTurma;

    public void matricular() {
        Aluno alunoEncontrado = null;
        List<Aluno> alunosEncontrados;

        System.out.println("\nQual aluno você deseja matricular ?");
        while (alunoEncontrado == null) {
            var nomeAluno = read.nextLine();
            alunosEncontrados = alunoRepository.buscarAlunoPorNome(nomeAluno);
            if (alunosEncontrados.isEmpty()) {
                System.out.println("\nAluno não encontrado ! Deseja cadastrá-lo?(s/n)");
                var escolha = read.nextLine();
                if (escolha.equalsIgnoreCase("s")) {
                    cadastrarAluno.cadastrar();
                } else {
                    return;
                }
            } else {
                alunosEncontrados.forEach(a ->
                        System.out.printf("%s - %s - %s\n", a.getRa(), a.getNome(), a.getEmail()));
                System.out.println("\nDigite o RA do aluno desejado: ");
                Long idSelecionado = lerLong();
                alunoEncontrado = alunosEncontrados.stream()
                        .filter(a -> a.getRa().equals(idSelecionado))
                        .findFirst()
                        .orElse(null);

                if (alunoEncontrado == null) {
                    System.out.println("\nRA inválido! Tente novamente:");
                }
            }
        }

        List<Turma> turmasAtivas = turmaRepository.findByStatus(Status.ATIVADA);
        if (turmasAtivas.isEmpty()) {
            System.out.println("\nNenhuma turma ativa disponível para matrícula.");
            return;
        }

        Turma turmaEncontrada = null;
        List<Turma> turmasEncontradas;

        System.out.printf("\nA qual turma deseja matricular %s ?\n", alunoEncontrado.getNome());
        listarTurmasAtivas.listar();

        System.out.println("\n: ");
        while (turmaEncontrada == null) {
            var nomeTurma = read.nextLine();
            turmasEncontradas = turmaRepository.buscarTurmaAtivaPorNome(nomeTurma, Status.ATIVADA);
            if (turmasEncontradas.isEmpty()) {
                System.out.println("\nTurma ativa não encontrada! Deseja cadastrar uma nova turma?(s/n)");
                var escolha = read.nextLine();
                if (escolha.equalsIgnoreCase("s")) {
                    novaTurma.nova();
                } else {
                    return;
                }
            } else {
                turmasEncontradas.forEach(a ->
                        System.out.printf("%s - %s - %s - %s\n", a.getId(), a.getNome(), a.getProfessor().getNome(), a.getCurso().getNome()));
                System.out.println("\nDigite o ID da turma desejada: ");
                Long idSelecionado = lerLong();
                turmaEncontrada = turmasEncontradas.stream()
                        .filter(a -> a.getId().equals(idSelecionado))
                        .findFirst()
                        .orElse(null);

                if (turmaEncontrada == null) {
                    System.out.println("\nID inválido! Tente novamente:");
                } else if (matriculaRepository.existsByAlunoRaAndTurmaId(alunoEncontrado.getRa(), turmaEncontrada.getId())) {
                    System.out.println("Erro: " + alunoEncontrado.getNome() + " já está matriculado na turma " + turmaEncontrada.getNome() + "!");
                    return;
                }
            }
        }

        matriculaRepository.save(new Matricula(alunoEncontrado, turmaEncontrada));
        System.out.println("Aluno matriculado com sucesso!");
    }
}
