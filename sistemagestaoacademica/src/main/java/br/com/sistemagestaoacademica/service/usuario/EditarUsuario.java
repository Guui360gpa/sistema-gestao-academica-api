package br.com.sistemagestaoacademica.service.usuario;

import br.com.sistemagestaoacademica.dto.UsuarioResponseDto;
import br.com.sistemagestaoacademica.dto.UsuarioResquestDto;
import br.com.sistemagestaoacademica.exception.AdminNaoPodeSerModificadoException;
import br.com.sistemagestaoacademica.exception.EmailJaCadastradoException;
import br.com.sistemagestaoacademica.exception.UsuarioNaoEncontradoException;
import br.com.sistemagestaoacademica.models.Role;
import br.com.sistemagestaoacademica.models.Usuario;
import br.com.sistemagestaoacademica.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditarUsuario {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioResponseDto editar(Long id, UsuarioResquestDto dto){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));

        if (usuario.getRole() == Role.ADMIN) {
            throw new AdminNaoPodeSerModificadoException("O usuário administrador não pode ser modificado");
        }

        if (usuario.getEmail().equals(dto.email()) && usuarioRepository.existsByEmail(dto.email())) {
            throw new EmailJaCadastradoException("Email já cadastrado: " + dto.email());
        }

        usuario.setEmail(dto.email());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));

        Usuario usarioSalvo = salvarUsuarioNoBanco(usuario);
        return gerarUsuarioResponse(usarioSalvo);
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
