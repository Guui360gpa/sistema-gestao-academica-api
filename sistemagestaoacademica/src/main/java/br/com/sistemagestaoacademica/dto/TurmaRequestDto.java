package br.com.sistemagestaoacademica.dto;

import br.com.sistemagestaoacademica.models.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TurmaRequestDto(
        @NotBlank String nome,
        @NotNull Long  idProfessor,
        @NotNull Long  idCurso,
        @NotBlank Status status
) {
}
