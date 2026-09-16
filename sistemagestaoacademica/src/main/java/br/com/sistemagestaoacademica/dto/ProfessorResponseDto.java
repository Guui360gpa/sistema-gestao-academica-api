package br.com.sistemagestaoacademica.dto;

import br.com.sistemagestaoacademica.models.Especialidade;
import br.com.sistemagestaoacademica.models.Status;

public record ProfessorResponseDto(
        Long id,
        String nome,
        Especialidade especialidade,
        Status status
){
}
