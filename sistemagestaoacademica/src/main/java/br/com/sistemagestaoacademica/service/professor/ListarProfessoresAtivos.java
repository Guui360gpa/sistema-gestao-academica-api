package br.com.sistemagestaoacademica.service.professor;

import br.com.sistemagestaoacademica.dto.ProfessorResponseDto;
import br.com.sistemagestaoacademica.exception.ListaProfessorVazioException;
import br.com.sistemagestaoacademica.models.Professor;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarProfessoresAtivos{

    private final ProfessorRepository professorRepository;

    public List<ProfessorResponseDto> listar() {

        List<Professor> professoresAtivos = professorRepository.findByStatus(Status.ATIVADA);

        if (professoresAtivos.isEmpty()) {
            throw new ListaProfessorVazioException("Nenhum professor cadastrado");
        }

       return gerarListaProfessorResponse(professoresAtivos);
    }

    private List<ProfessorResponseDto> gerarListaProfessorResponse(List<Professor> professores){
        return professores.stream()
                .map(p -> new ProfessorResponseDto(
                        p.getId(),
                        p.getNome(),
                        p.getEmail(),
                        p.getTelefone(),
                        p.getEspecialidade(),
                        p.getStatus()
                )).toList();
    }
}
