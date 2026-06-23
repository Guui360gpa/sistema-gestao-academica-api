package br.com.sistemagestaoacademica.service.professor;

import br.com.sistemagestaoacademica.models.Professor;
import br.com.sistemagestaoacademica.service.BaseService;
import java.util.List;

@org.springframework.stereotype.Service
public class ListarProfessores extends BaseService {
    public void listar() {
        List<Professor> todosProfessores = professorRepository.findAll();

        if (todosProfessores.isEmpty()) {
            System.out.println("\nNenhum professor cadastrado.");
            return;
        }

        todosProfessores.forEach(p ->
                System.out.printf("%s | %s\n",
                        p.getNome(),
                        p.getEspecialidade()));
    }
}
