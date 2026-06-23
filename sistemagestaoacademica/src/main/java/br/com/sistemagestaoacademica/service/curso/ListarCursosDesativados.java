package br.com.sistemagestaoacademica.service.curso;

import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.service.BaseService;
import java.util.List;

@org.springframework.stereotype.Service
public class ListarCursosDesativados extends BaseService {
    public void listar(){
        List<Curso> cursosDesativados = cursoRepository.findByStatus(Status.DESATIVADA);

        if(cursosDesativados.isEmpty()){
            System.out.println("Nenhum curso desativado foi encontrado!");
        }

        cursosDesativados.forEach(c ->
                System.out.printf("\nCurso de %s\n%s\n%s h\n",
                        c.getNome(),
                        c.getDescricao(),
                        c.getCargaHoraria()));
    }
}
