package br.com.sistemagestaoacademica.service.turma;

import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.models.Turma;
import br.com.sistemagestaoacademica.service.BaseService;
import java.util.List;

@org.springframework.stereotype.Service
public class ListarTurmasAtivas extends BaseService {
    public void listar() {
        List<Turma> turmasAtivas = turmaRepository.findByStatus(Status.ATIVADA);

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
}
