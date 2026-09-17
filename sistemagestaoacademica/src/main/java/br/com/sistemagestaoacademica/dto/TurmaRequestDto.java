package br.com.sistemagestaoacademica.dto;

import br.com.sistemagestaoacademica.models.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TurmaRequestDto(
        @NotBlank(message = "Campo obrigatório") String nome,
        @NotNull(message = "Campo obrigatório") Long  idProfessor,
        @NotNull(message = "Campo obrigatório") Long  idCurso,
        @NotBlank(message = "Campo obrigatório") Status status
) {
}
