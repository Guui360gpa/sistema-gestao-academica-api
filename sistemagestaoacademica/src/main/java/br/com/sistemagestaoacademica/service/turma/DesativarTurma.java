package br.com.sistemagestaoacademica.service.turma;

import br.com.sistemagestaoacademica.dto.TurmaResponseDto;
import br.com.sistemagestaoacademica.exception.TurmaJaDesativadaException;
import br.com.sistemagestaoacademica.exception.TurmaNaoEncontradaException;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.models.Turma;
import br.com.sistemagestaoacademica.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DesativarTurma{

    private final TurmaRepository turmaRepository;

    public TurmaResponseDto desativar(Long idTurma) {
        Turma turma = turmaRepository.findById(idTurma)
                .orElseThrow(() -> new TurmaNaoEncontradaException("Turma não encontrada"));

        if (turma.getStatusTurma() == Status.DESATIVADA){
            throw new TurmaJaDesativadaException("Turma " + turma.getNome() + " já desativada");
        }

        turma.setStatusTurma(Status.DESATIVADA);
        Turma turmaSalva  = salvarTurmaNoBanco(turma);

        return gerarTurmaResponse(turmaSalva);
    }

    private Turma salvarTurmaNoBanco(Turma turma){
        return turmaRepository.save(turma);
    }

    private TurmaResponseDto gerarTurmaResponse(Turma turma) {
        return new TurmaResponseDto(
                turma.getId(),
                turma.getNome(),
                turma.getData(),
                turma.getProfessor().getId(),
                turma.getProfessor().getNome(),
                turma.getCurso().getId(),
                turma.getCurso().getNome(),
                turma.getStatus()
        );
    }
}
