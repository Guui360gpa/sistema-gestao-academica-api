package br.com.sistemagestaoacademica.service.aluno;

import br.com.sistemagestaoacademica.dto.MatriculaRequestDto;
import br.com.sistemagestaoacademica.dto.MatriculaResponseDto;
import br.com.sistemagestaoacademica.exception.AlunoNaoEncontradoException;
import br.com.sistemagestaoacademica.exception.MatriculaDuplicadaException;
import br.com.sistemagestaoacademica.exception.TurmaNaoEncontradaException;
import br.com.sistemagestaoacademica.models.Aluno;
import br.com.sistemagestaoacademica.models.Matricula;
import br.com.sistemagestaoacademica.models.Turma;
import br.com.sistemagestaoacademica.repository.AlunoRepository;
import br.com.sistemagestaoacademica.repository.MatriculaRepository;
import br.com.sistemagestaoacademica.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatricularAluno{

    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;
    private final MatriculaRepository matriculaRepository;

    public MatriculaResponseDto matricular(MatriculaRequestDto dto) {

        if (matriculaRepository.existsByAlunoRaAndTurmaId(dto.alunoRa(),dto.turmaId())){
            throw new MatriculaDuplicadaException("Aluno já matriculado nessa turma");
        }

        Aluno aluno = alunoRepository.findById(dto.alunoRa())
                .orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado"));

        Turma turma = turmaRepository.findById(dto.turmaId())
                .orElseThrow(() -> new TurmaNaoEncontradaException("Turma não encontrada"));

        Matricula matriculaSalva = salvarMatriculaNoBanco(new Matricula(aluno,turma));


        return gerarMatriculaResponse(matriculaSalva);

        }

    private Matricula salvarMatriculaNoBanco(Matricula matricula){
        return matriculaRepository.save(matricula);
    }

    private MatriculaResponseDto gerarMatriculaResponse(Matricula matricula){
        return new MatriculaResponseDto(
                matricula.getId(),
                matricula.getAluno().getRa(),
                matricula.getAluno().getNome(),
                matricula.getTurma().getId(),
                matricula.getTurma().getNome(),
                matricula.getDataMatricula()
        );
    }

    }



