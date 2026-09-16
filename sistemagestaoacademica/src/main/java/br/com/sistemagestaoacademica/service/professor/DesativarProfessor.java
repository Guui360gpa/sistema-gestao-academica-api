package br.com.sistemagestaoacademica.service.professor;

import br.com.sistemagestaoacademica.dto.ProfessorResponseDto;
import br.com.sistemagestaoacademica.exception.ProfessorComTurmaAtivaException;
import br.com.sistemagestaoacademica.exception.ProfessorJaDesativadoException;
import br.com.sistemagestaoacademica.exception.ProfessorNaoEncontradoException;
import br.com.sistemagestaoacademica.models.Professor;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.repository.ProfessorRepository;
import br.com.sistemagestaoacademica.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DesativarProfessor {

    private final ProfessorRepository professorRepository;
    private final TurmaRepository turmaRepository;

    public ProfessorResponseDto desativar(Long professorId) {

        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ProfessorNaoEncontradoException("Professor não encontrado"));

        if (professor.getStatus() == Status.DESATIVADA){
            throw new ProfessorJaDesativadoException("Professor já está desativado");
        }

        if (possuiTurmaAtiva(professorId)) {
            throw new ProfessorComTurmaAtivaException(
                    "Professor não pode ser desativado: possui turma(s) ativa(s) vinculada(s)");
        }

        professor.setStatus(Status.DESATIVADA);
        Professor professorSalvo = salvarProfessorNoBanco(professor);

        return gerarProfessorResponse(professorSalvo);

    }

    private boolean possuiTurmaAtiva(Long id){
        return turmaRepository.existsByProfessor_IdAndStatusTurma(id,Status.ATIVADA);
    }

    private Professor salvarProfessorNoBanco(Professor professor){
        return professorRepository.save(professor);
    }

    private ProfessorResponseDto gerarProfessorResponse(Professor p){
        return new ProfessorResponseDto(
                p.getId(),
                p.getNome(),
                p.getEspecialidade(),
                p.getStatus());
    }
}
