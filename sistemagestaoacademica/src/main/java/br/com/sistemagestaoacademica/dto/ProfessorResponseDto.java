package br.com.sistemagestaoacademica.dto;

import br.com.sistemagestaoacademica.models.Especialidade;
import br.com.sistemagestaoacademica.models.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProfessorResponseDto(
        Long id,
        String nome,
        Especialidade especialidade,
        Status status
){
}
