package br.com.sistemagestaoacademica.dto;

import java.time.LocalDate;

public record MatriculaResponseDto(
        Long id,
        Long alunoRa,
        String nomeAluno,
        Long turmaId,
        String nomeTurma,
        LocalDate dataMatricula
) {
}
