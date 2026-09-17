package br.com.sistemagestaoacademica.dto;

import jakarta.validation.constraints.NotNull;

public record MatriculaRequestDto(
        @NotNull(message = "Campo obrigatório") Long alunoRa,
        @NotNull(message = "Campo obrigatório") Long turmaId
) {
}
