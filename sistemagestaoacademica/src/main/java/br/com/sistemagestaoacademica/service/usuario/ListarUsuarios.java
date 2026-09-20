package br.com.sistemagestaoacademica.service.usuario;

import br.com.sistemagestaoacademica.dto.UsuarioResponseDto;
import br.com.sistemagestaoacademica.models.Status;
import br.com.sistemagestaoacademica.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarUsuarios {

    private final UsuarioRepository usuarioRepository;

    public List<UsuarioResponseDto> listar(Status status){
        return usuarioRepository.findByStatus(status).stream()
                .map(u -> new UsuarioResponseDto(
                        u.getId(),
                        u.getEmail(),
                        u.getRole(),
                        u.getStatus()
                )).toList();
    }

}
