package br.com.sistemagestaoacademica.service.usuario;

import br.com.sistemagestaoacademica.dto.UsuarioResponseDto;
import br.com.sistemagestaoacademica.dto.UsuarioResquestDto;
import br.com.sistemagestaoacademica.exception.EmailJaCadastradoException;
import br.com.sistemagestaoacademica.models.Usuario;
import br.com.sistemagestaoacademica.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Service
@RequiredArgsConstructor
public class CadastrarUsuario {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioResponseDto cadastrar(UsuarioResquestDto dto) {

        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new EmailJaCadastradoException("Email já cadastrado: " + dto.email());
        }

        Usuario usuario = new Usuario(dto.email(),passwordEncoder.encode(dto.senha()));
        Usuario usuarioSalvo = salvarUsuarioNoBanco(usuario);

        return gerarUsuarioResponse(usuarioSalvo);
    }

    private Usuario salvarUsuarioNoBanco(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    private UsuarioResponseDto gerarUsuarioResponse(Usuario u){
        return new UsuarioResponseDto(
                u.getId(),
                u.getEmail(),
                u.getRole(),
                u.getStatus()
        );
    }

}
