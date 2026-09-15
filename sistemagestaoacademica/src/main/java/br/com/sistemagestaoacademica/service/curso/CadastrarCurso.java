package br.com.sistemagestaoacademica.service.curso;

import br.com.sistemagestaoacademica.dto.CursoRequestDto;
import br.com.sistemagestaoacademica.dto.CursoResponseDto;
import br.com.sistemagestaoacademica.exception.CursoJaCadastrado;
import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarCurso{

    private final CursoRepository cursoRepository;

    public CursoResponseDto cadastrar(CursoRequestDto dto){

        if (cursoRepository.existsByNome(dto.nome())){
            throw new CursoJaCadastrado("Curso ja cadastrado");
        }

        Curso cursoSalvo = salvarCursoNoBanco(new Curso(dto.nome(),dto.descricao(),dto.cargaHoraria()));

        return gerarCursoResponse(cursoSalvo);
    }

    private Curso salvarCursoNoBanco(Curso curso){
        return cursoRepository.save(curso);
    }

    private CursoResponseDto gerarCursoResponse(Curso c){
        return new CursoResponseDto(
                c.getId(),
                c.getNome(),
                c.getDescricao(),
                c.getCargaHoraria(),
                c.getStatus()
        );
    }
}
