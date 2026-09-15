package br.com.sistemagestaoacademica.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AlunoRequestDto(
        @NotBlank String nome,
        @NotBlank String dataNascimento,
        @NotBlank @Email String email

) {
}
