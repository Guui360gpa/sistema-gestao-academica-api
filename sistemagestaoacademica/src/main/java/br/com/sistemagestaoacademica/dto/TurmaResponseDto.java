package br.com.sistemagestaoacademica.dto;

import br.com.sistemagestaoacademica.models.Status;

import java.time.LocalDate;

public record TurmaResponseDto(
        Long id,
        String nome,
        LocalDate data,
        Long professorId,
        String nomeProfessor,
        Long cursoId,
        String nomeCurso,
        Status status
) {}