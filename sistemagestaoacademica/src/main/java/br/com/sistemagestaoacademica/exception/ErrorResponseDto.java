package br.com.sistemagestaoacademica.exception;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        LocalDateTime timestamp,
        int status,
        String erro,
        String mensagem
) {
}
