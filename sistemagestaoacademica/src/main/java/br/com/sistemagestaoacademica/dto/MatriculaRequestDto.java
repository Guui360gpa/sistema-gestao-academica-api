package br.com.sistemagestaoacademica.dto;

import jakarta.validation.constraints.NotNull;

public record MatriculaRequestDto(
        @NotNull Long alunoRa,
        @NotNull Long turmaId
) {
}
