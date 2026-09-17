package br.com.sistemagestaoacademica.dto;

import br.com.sistemagestaoacademica.models.Especialidade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorRequestDto(
        @NotBlank(message = "Campo obrigatório") String nome,
        @NotBlank(message = "Campo obrigatório") @Email(message = "Email inválido") String email,
        @NotBlank(message = "Campo obrigatório") String telefone,
        @NotNull(message = "Campo obrigatório") Especialidade especialidade
        ){
}
