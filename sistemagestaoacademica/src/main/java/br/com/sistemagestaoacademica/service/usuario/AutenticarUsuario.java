package br.com.sistemagestaoacademica.service.usuario;

import br.com.sistemagestaoacademica.dto.LoginRequestDto;
import br.com.sistemagestaoacademica.dto.LoginResponseDto;
import br.com.sistemagestaoacademica.exception.CredenciaisInvalidasException;
import br.com.sistemagestaoacademica.exception.UsuarioInativoException;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.models.Usuario;
import br.com.sistemagestaoacademica.repository.UsuarioRepository;
import br.com.sistemagestaoacademica.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticarUsuario {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponseDto login(LoginRequestDto dto) {
        Usuario usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new CredenciaisInvalidasException("Email ou senha inválidos"));

        if (!passwordEncoder.matches(dto.senha(),usuario.getSenha())) {
            throw new CredenciaisInvalidasException("Email ou senha inválidos");
        }

        if (usuario.getStatus() != Status.ATIVADA) {
            throw new UsuarioInativoException("Usuário inativo. Contate o administrador.");
        }

        String token = jwtService.gerarToken(usuario.getEmail(),usuario.getRole().name());

        return new LoginResponseDto(token,usuario.getEmail(),usuario.getRole().name());
    }
}
