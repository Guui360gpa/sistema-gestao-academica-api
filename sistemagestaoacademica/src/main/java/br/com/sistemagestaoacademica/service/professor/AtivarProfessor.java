package br.com.sistemagestaoacademica.service.professor;

import br.com.sistemagestaoacademica.dto.ProfessorResponseDto;
import br.com.sistemagestaoacademica.exception.ProfessorJaAtivadoException;
import br.com.sistemagestaoacademica.exception.ProfessorNaoEncontradoException;
import br.com.sistemagestaoacademica.models.Professor;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtivarProfessor {

    private final ProfessorRepository professorRepository;

    public ProfessorResponseDto ativar(Long id){

        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ProfessorNaoEncontradoException("Professor não encontrado"));

        if (professor.getStatus() == Status.ATIVADA){
            throw new ProfessorJaAtivadoException("Professor(a) " + professor.getNome() + " já ativado(a)");
        }

        professor.setStatus(Status.ATIVADA);
        Professor professorSalvo = salvarProfessorNoBanco(professor);

        return gerarProfessorResponse(professorSalvo);
    }

    private Professor salvarProfessorNoBanco(Professor professor){
        return professorRepository.save(professor);
    }

    private ProfessorResponseDto gerarProfessorResponse(Professor p){
        return new ProfessorResponseDto(
                p.getId(),
                p.getNome(),
                p.getEmail(),
                p.getTelefone(),
                p.getEspecialidade(),
                p.getStatus()
        );
    }
}
