package br.com.sistemagestaoacademica.service.aluno;

import br.com.sistemagestaoacademica.dto.AlunoResponseDto;
import br.com.sistemagestaoacademica.exception.TurmaVaziaException;
import br.com.sistemagestaoacademica.models.Aluno;
import br.com.sistemagestaoacademica.repository.MatriculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarAlunoPorTurma{

    private final MatriculaRepository matriculaRepository;

    public List<AlunoResponseDto> listar(Long idSelecionado) {

        List<Aluno> alunosTurma = matriculaRepository.buscarAlunosPorTurma(idSelecionado);

        if (alunosTurma.isEmpty()) {
            throw new TurmaVaziaException("Esta turma está vazia");
        }

        return alunosTurma.stream()
                .map(a -> new AlunoResponseDto(
                        a.getRa(),
                        a.getNome(),
                        a.getEmail(),
                        a.getIdade()
                )).toList();
    }
}
