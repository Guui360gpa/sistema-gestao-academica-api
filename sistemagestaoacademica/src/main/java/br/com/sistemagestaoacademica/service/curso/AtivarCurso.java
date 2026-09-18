package br.com.sistemagestaoacademica.service.curso;

import br.com.sistemagestaoacademica.dto.CursoResponseDto;
import br.com.sistemagestaoacademica.exception.CursoJaAtivadoException;
import br.com.sistemagestaoacademica.exception.CursoNaoEncontradoException;
import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtivarCurso {

    private final CursoRepository cursoRepository;

    public CursoResponseDto ativar(Long id){

        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNaoEncontradoException("Curso não encontrado"));

        if (curso.getStatus() == Status.ATIVADA){
            throw new CursoJaAtivadoException("O curso " + curso.getNome() + " já está ativado");
        }

        curso.setStatus(Status.ATIVADA);
        Curso cursoSalvo = salvarCursoNoBanco(curso);

        return gerarCursoResponse(cursoSalvo);
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
