package br.com.sistemagestaoacademica.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ValidationErrorResponseDto (
        LocalDateTime timestamp,
        int status,
        String erro,
        Map<String, String> campos
) {
}
