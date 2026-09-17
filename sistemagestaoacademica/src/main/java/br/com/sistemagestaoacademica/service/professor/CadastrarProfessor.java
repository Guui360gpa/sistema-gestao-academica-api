package br.com.sistemagestaoacademica.service.professor;

import br.com.sistemagestaoacademica.dto.ProfessorRequestDto;
import br.com.sistemagestaoacademica.dto.ProfessorResponseDto;
import br.com.sistemagestaoacademica.exception.EmailJaCadastradoException;
import br.com.sistemagestaoacademica.models.Professor;
import br.com.sistemagestaoacademica.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarProfessor{

    private final ProfessorRepository professorRepository;

    public ProfessorResponseDto cadastrar(ProfessorRequestDto dto) {

        if (professorRepository.existsByEmail(dto.email())){
            throw new EmailJaCadastradoException("Email já existe");
        }

        Professor professorSalvo = salvarProfessorNoBanco(new Professor(dto.nome(),dto.email(),dto.telefone(),dto.especialidade()));

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
