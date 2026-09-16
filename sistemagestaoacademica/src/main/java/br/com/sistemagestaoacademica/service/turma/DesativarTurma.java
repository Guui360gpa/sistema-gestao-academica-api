package br.com.sistemagestaoacademica.service.turma;

import br.com.sistemagestaoacademica.exception.TurmaNaoEncontradaException;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.models.Turma;
import br.com.sistemagestaoacademica.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DesativarTurma{

    private final TurmaRepository turmaRepository;

    public void desativar(Long idTurma) {
        Turma turma = turmaRepository.findById(idTurma)
                .orElseThrow(() -> new TurmaNaoEncontradaException("Turma não encontrada"));

        turma.setStatusTurma(Status.DESATIVADA);

        salvarTurmaNoBanco(turma);
    }

    private void salvarTurmaNoBanco(Turma turma){
        turmaRepository.save(turma);
    }
}
