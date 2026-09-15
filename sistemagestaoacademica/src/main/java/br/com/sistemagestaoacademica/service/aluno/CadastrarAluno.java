package br.com.sistemagestaoacademica.service.aluno;

import br.com.sistemagestaoacademica.dto.AlunoRequestDto;
import br.com.sistemagestaoacademica.dto.AlunoResponseDto;
import br.com.sistemagestaoacademica.exception.DataInvalidaException;
import br.com.sistemagestaoacademica.exception.EmailInvalidoException;
import br.com.sistemagestaoacademica.exception.EmailJaCadastradoException;
import br.com.sistemagestaoacademica.models.Aluno;
import br.com.sistemagestaoacademica.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class CadastrarAluno{

    private final AlunoRepository alunoRepository;

    public AlunoResponseDto cadastrar(AlunoRequestDto dto) {
        if (!validadorDeEmail(dto.email())){
            throw new EmailInvalidoException("Email inválido: " + dto.email());
        }
        if (emailExistente(dto.email())){
            throw new EmailJaCadastradoException("Email já existe");
        }

        LocalDate dataFormatada = formatarData(dto.dataNascimento());

        Aluno aluno = new Aluno(dto.nome(), dataFormatada, dto.email());

        salvarAlunoNoBanco(aluno);

        return new AlunoResponseDto(
                aluno.getRa(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getIdade()
        );
    }

    private boolean emailExistente(String email){
        return alunoRepository.existsByEmail(email);
    }

    private LocalDate formatarData(String data){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(data, formatter);
        } catch (DateTimeParseException e){
            throw new DataInvalidaException("Data inválida, use o formato dd/MM/yyyy");
        }

    }

    private boolean validadorDeEmail(String email){
        String emailRegex = "^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);

        return pattern.matcher(email).matches();
    }

    private Aluno salvarAlunoNoBanco(Aluno aluno){
        return alunoRepository.save(aluno);
    }
}
