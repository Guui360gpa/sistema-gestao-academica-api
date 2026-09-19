package br.com.sistemagestaoacademica.dto;

import br.com.sistemagestaoacademica.models.Role;
import br.com.sistemagestaoacademica.models.Status;

public record UsuarioResponseDto(
        Long id,
        String email,
        Role role,
        Status status
) {
}
