package br.com.sistemagestaoacademica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoRequestDto(
        @NotBlank(message = "Campo obrigatório") String nome,
        @NotBlank(message = "Campo obrigatório") String descricao,
        @NotNull(message = "Campo obrigatório") Integer cargaHoraria
) {
}
