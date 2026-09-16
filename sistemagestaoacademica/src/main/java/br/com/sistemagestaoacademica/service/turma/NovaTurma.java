package br.com.sistemagestaoacademica.service.turma;

import br.com.sistemagestaoacademica.dto.TurmaRequestDto;
import br.com.sistemagestaoacademica.dto.TurmaResponseDto;
import br.com.sistemagestaoacademica.exception.CursoNaoEncontradoException;
import br.com.sistemagestaoacademica.exception.ProfessorNaoEncontradoException;
import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Professor;
import br.com.sistemagestaoacademica.models.Turma;
import br.com.sistemagestaoacademica.repository.CursoRepository;
import br.com.sistemagestaoacademica.repository.ProfessorRepository;
import br.com.sistemagestaoacademica.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovaTurma{

    private final ProfessorRepository professorRepository;
    private final CursoRepository cursoRepository;
    private final TurmaRepository turmaRepository;

    public TurmaResponseDto nova(TurmaRequestDto dto) {

        Curso curso = cursoRepository.findById(dto.idCurso())
                .orElseThrow(() -> new CursoNaoEncontradoException("Curso não encontrado"));

        Professor professor = professorRepository.findById(dto.idProfessor())
                        .orElseThrow(() -> new ProfessorNaoEncontradoException("Professor não encontrado"));

        Turma turmaSalva = salvarTurmaNoBanco(new Turma(dto.nome(),professor,curso));

        return gerarTurmaResponse(turmaSalva);
    }

    private Turma salvarTurmaNoBanco(Turma turma){
        return turmaRepository.save(turma);
    }

    private TurmaResponseDto gerarTurmaResponse(Turma t){
        return new TurmaResponseDto(
                t.getId(),
                t.getNome(),
                t.getData(),
                t.getProfessor(),
                t.getCurso(),
                t.getStatusTurma());
    }
}