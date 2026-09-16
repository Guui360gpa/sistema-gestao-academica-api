package br.com.sistemagestaoacademica.service.professor;

import br.com.sistemagestaoacademica.dto.ProfessorResponseDto;
import br.com.sistemagestaoacademica.exception.ListaProfessorVazioException;
import br.com.sistemagestaoacademica.models.Professor;
import br.com.sistemagestaoacademica.repository.ProfessorRepository;
import br.com.sistemagestaoacademica.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarProfessores{

    private final ProfessorRepository professorRepository;

    public List<ProfessorResponseDto> listar() {

        List<Professor> todosProfessores = professorRepository.findAll();

        if (todosProfessores.isEmpty()) {
            throw new ListaProfessorVazioException("Nenhum professor cadastrado");
        }

       return gerarListaProfessorResponse(todosProfessores);
    }

    private List<ProfessorResponseDto> gerarListaProfessorResponse(List<Professor> professores){
        return professores.stream()
                .map(p -> new ProfessorResponseDto(
                        p.getId(),
                        p.getNome(),
                        p.getEspecialidade()
                )).toList();
    }
}
