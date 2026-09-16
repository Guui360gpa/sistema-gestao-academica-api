package br.com.sistemagestaoacademica.dto;

import br.com.sistemagestaoacademica.models.Curso;
import br.com.sistemagestaoacademica.models.Professor;
import br.com.sistemagestaoacademica.models.Status;

import java.time.LocalDate;

public record TurmaResponseDto(
        Long id,
        String nome,
        LocalDate data,
        Professor professor,
        Curso curso,
        Status status
) {
}
