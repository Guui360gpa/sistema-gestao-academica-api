package br.com.sistemagestaoacademica.dto;

public record AlunoResponseDto(
        Long ra,
        String nome,
        String email,
        int idade
) {
}
