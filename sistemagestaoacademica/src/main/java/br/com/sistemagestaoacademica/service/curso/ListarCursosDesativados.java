package br.com.sistemagestaoacademica.service.curso;

import br.com.sistemagestaoacademica.dto.CursoResponseDto;
import br.com.sistemagestaoacademica.exception.ListaCursosAtivosVazioException;
import br.com.sistemagestaoacademica.exception.ListaCursosVazioException;
import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.repository.CursoRepository;
import br.com.sistemagestaoacademica.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarCursosDesativados{

    private final CursoRepository cursoRepository;

    public List<CursoResponseDto> listar(){
        List<Curso> cursosDesativados = cursoRepository.findByStatus(Status.DESATIVADA);

        if(cursosDesativados.isEmpty()){
            throw new ListaCursosVazioException("Nenhum curso desativo");
        }

        return gerarListaCursoResponse(cursosDesativados);
    }

    private List<CursoResponseDto> gerarListaCursoResponse(List<Curso> curso){
        return curso.stream()
                .map(c -> new CursoResponseDto(
                        c.getId(),
                        c.getNome(),
                        c.getDescricao(),
                        c.getCargaHoraria(),
                        c.getStatus()
                )).toList();
    }
}
