package br.com.sistemagestaoacademica.dto;

import br.com.sistemagestaoacademica.models.Especialidade;
import jakarta.validation.constraints.NotBlank;

public record ProfessorRequestDto(
        @NotBlank String nome,
        @NotBlank Especialidade especialidade
        ){
}
