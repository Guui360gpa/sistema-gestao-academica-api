package br.com.sistemagestaoacademica.service.turma;


import br.com.sistemagestaoacademica.dto.TurmaResponseDto;
import br.com.sistemagestaoacademica.exception.ListaTurmaVaziaException;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.models.Turma;
import br.com.sistemagestaoacademica.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarTurmasAtivas{

    private final TurmaRepository turmaRepository;

    public List<TurmaResponseDto> listar(){
        List<Turma> turmasAtivas = turmaRepository.findByStatus(Status.ATIVADA);

        if(turmasAtivas.isEmpty()){
            throw new ListaTurmaVaziaException("Nenhuma turma ativa");
        }

        return gerarListaTurmaResponse(turmasAtivas);
    }

    private List<TurmaResponseDto> gerarListaTurmaResponse(List<Turma> turmas){
        return turmas.stream()
                .map(t -> new TurmaResponseDto(
                        t.getId(),
                        t.getNome(),
                        t.getData(),
                        t.getProfessor(),
                        t.getCurso(),
                        t.getStatusTurma()
                )).toList();
    }
}
