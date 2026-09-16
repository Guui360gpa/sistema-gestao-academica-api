package br.com.sistemagestaoacademica.service.professor;

import br.com.sistemagestaoacademica.dto.ProfessorResponseDto;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarProfessoresDesativados {

    private final ProfessorRepository professorRepository;

    public List<ProfessorResponseDto> listar() {

        return professorRepository.findByStatus(Status.DESATIVADA).stream()
                .map(p -> new ProfessorResponseDto(
                        p.getId(),
                        p.getNome(),
                        p.getEspecialidade(),
                        p.getStatus()
                ))
                .toList();
    }
}
