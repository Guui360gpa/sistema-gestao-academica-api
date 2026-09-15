package br.com.sistemagestaoacademica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoRequestDto(
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotNull Integer cargaHoraria
) {
}
