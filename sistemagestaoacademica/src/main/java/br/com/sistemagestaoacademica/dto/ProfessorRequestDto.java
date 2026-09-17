package br.com.sistemagestaoacademica.dto;

import br.com.sistemagestaoacademica.models.Especialidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorRequestDto(
        @NotBlank(message = "Campo obrigatório") String nome,
        @NotNull(message = "Campo obrigatório") Especialidade especialidade
        ){
}
