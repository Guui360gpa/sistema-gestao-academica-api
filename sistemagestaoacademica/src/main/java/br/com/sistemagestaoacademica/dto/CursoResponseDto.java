package br.com.sistemagestaoacademica.dto;


import br.com.sistemagestaoacademica.models.Status;

public record CursoResponseDto(
        Long id,
        String nome,
        String descricao,
        Integer cargaHoraria,
        Status status
) {

}
