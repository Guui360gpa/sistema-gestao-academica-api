package br.com.sistemagestaoacademica.service.curso;

import br.com.sistemagestaoacademica.dto.CursoResponseDto;
import br.com.sistemagestaoacademica.exception.CursoComTurmaAtivaException;
import br.com.sistemagestaoacademica.exception.CursoJaDesativadoException;
import br.com.sistemagestaoacademica.exception.CursoNaoEncontradoException;
import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.repository.CursoRepository;
import br.com.sistemagestaoacademica.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DesativarCurso{

    private final CursoRepository cursoRepository;
    private final TurmaRepository turmaRepository;


    public CursoResponseDto desativar(Long id){

        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new CursoNaoEncontradoException("Curso não encontrado"));

        if (curso.getStatus() == Status.DESATIVADA){
            throw new CursoJaDesativadoException("O curso " + curso.getNome() + " já está desativado");
        }

        if (possuiTurmaAtiva(id)){
            throw new CursoComTurmaAtivaException("Curso não pode ser desativado: possui turma(s) ativa(s) vinculada(s)");
        }

        curso.setStatus(Status.DESATIVADA);
        Curso cursoSalvo = salvarCursoNoBanco(curso);

        return gerarCursoResponse(cursoSalvo);
    }

    private boolean possuiTurmaAtiva(Long id){
        return turmaRepository.existsByCurso_IdAndStatus(id,Status.ATIVADA);
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
