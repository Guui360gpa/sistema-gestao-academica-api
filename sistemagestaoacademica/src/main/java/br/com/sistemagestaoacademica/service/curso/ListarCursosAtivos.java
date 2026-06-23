package br.com.sistemagestaoacademica.service.curso;

import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.service.BaseService;
import java.util.List;

@org.springframework.stereotype.Service
public class ListarCursosAtivos extends BaseService {
    public void listar(){
        List<Curso> cursosAtivos = cursoRepository.findByStatus(Status.ATIVADA);

        if(cursosAtivos.isEmpty()){
            System.out.println("Nenhum curso ativo foi encontrado!");
        }

        cursosAtivos.forEach(c ->
                System.out.printf("\nCurso de %s\n%s\n%s h\n",
                        c.getNome(),
                        c.getDescricao(),
                        c.getCargaHoraria()));
    }
}
