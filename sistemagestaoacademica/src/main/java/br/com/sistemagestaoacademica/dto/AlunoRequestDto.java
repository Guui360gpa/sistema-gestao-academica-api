package br.com.sistemagestaoacademica.dto;

import jakarta.validation.constraints.NotBlank;

public record AlunoRequest(
        @NotBlank String nome,
        @NotBlank String email,
        @NotBlank String dataNascimento
) {
}
