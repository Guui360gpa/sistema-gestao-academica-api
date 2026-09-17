package br.com.sistemagestaoacademica.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AlunoRequestDto(
        @NotBlank(message = "Campo obrigatório") String nome,
        @NotBlank(message = "Campo obrigatório") String dataNascimento,
        @NotBlank(message = "Campo obrigatório") @Email(message = "Email inválido") String email

) {
}
