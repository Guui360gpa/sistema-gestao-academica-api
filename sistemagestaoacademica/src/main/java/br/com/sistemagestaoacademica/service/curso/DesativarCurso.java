package br.com.sistemagestaoacademica.service.curso;

import br.com.sistemagestaoacademica.dto.CursoResponseDto;
import br.com.sistemagestaoacademica.exception.CursoJaDesativadoException;
import br.com.sistemagestaoacademica.exception.CursoNaoEncontradoException;
import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.repository.CursoRepository;
import br.com.sistemagestaoacademica.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesativarCurso{

    private final CursoRepository cursoRepository;


    public CursoResponseDto desativar(Long id){

        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNaoEncontradoException("Curso não encontrado"));

        CursoResponseDto cursoResponse = gerarCursoResponse(curso);

        if (cursoResponse.status() == Status.DESATIVADA){
            throw new CursoJaDesativadoException("O curso " +cursoResponse.nome() + "já está desativado");
        }

        curso.setStatus(Status.DESATIVADA);
        Curso cursoSalvo = salvarCursoNoBanco(curso);

        return cursoResponse;
    }

    private CursoResponseDto gerarCursoResponse(Curso c){
        return new CursoResponseDto(
                c.getId(),
                c.getNome(),
                c.getDescricao(),
                c.getCargaHoraria(),
                c.getStatus()
        );
    }

    private Curso salvarCursoNoBanco(Curso curso){
        return cursoRepository.save(curso);
    }
}
