package br.com.sistemagestaoacademica.service.turma;

import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.models.Turma;
import br.com.sistemagestaoacademica.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class DesativarTurma extends BaseService {

    @Autowired
    private ListarTurmasAtivas listarTurmasAtivas;

    public void desativar() {
        Turma turmaEncontrada = null;
        List<Turma> turmasEncontradas;

        System.out.println("Qual turma você deseja DESATIVAR?");
        listarTurmasAtivas.listar();
        while (turmaEncontrada == null){
            var nomeTurma = read.nextLine();
            turmasEncontradas = turmaRepository.buscarTurmaAtivaPorNome(nomeTurma, Status.ATIVADA);
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
                Long idSelecionado = lerLong();
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
        turmaRepository.save(turmaEncontrada);
    }
}
