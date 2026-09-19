package br.com.sistemagestaoacademica.dto;

public record LoginResponseDto(
        String token,
        String email,
        String role
) {
}
