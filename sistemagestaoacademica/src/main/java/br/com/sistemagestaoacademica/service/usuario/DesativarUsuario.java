package br.com.sistemagestaoacademica.service.usuario;

import br.com.sistemagestaoacademica.dto.UsuarioResponseDto;
import br.com.sistemagestaoacademica.exception.AdminNaoPodeSerModificadoException;
import br.com.sistemagestaoacademica.exception.UsuarioNaoEncontradoException;
import br.com.sistemagestaoacademica.models.Role;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.models.Usuario;
import br.com.sistemagestaoacademica.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DesativarUsuario {

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponseDto desativar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));

        if (usuario.getRole() == Role.ADMIN){
            throw new AdminNaoPodeSerModificadoException("O usuário administrador não pode ser desativado");
        }

        usuario.setStatus(Status.DESATIVADA);
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
