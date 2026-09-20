package br.com.sistemagestaoacademica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //400
    @ExceptionHandler({
            DataInvalidaException.class,
            EmailInvalidoException.class,
            LoginInvalidoException.class
    })
    public ResponseEntity<ErrorResponseDto> handleBadRequest(RuntimeException ex){
        return construirResposta(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<ValidationErrorResponseDto> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String, String> campos = new HashMap<>();

        for (FieldError erro : ex.getBindingResult().getFieldErrors()) {
            campos.put(erro.getField(),erro.getDefaultMessage());
        }

        ValidationErrorResponseDto body = new ValidationErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                campos
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    //404
    @ExceptionHandler({
            AlunoNaoEncontradoException.class,
            CursoNaoEncontradoException.class,
            ProfessorNaoEncontradoException.class,
            TurmaNaoEncontradaException.class,
            UsuarioNaoEncontradoException.class
    })
    public ResponseEntity<ErrorResponseDto> handleNotFound(RuntimeException ex){
        return construirResposta(HttpStatus.BAD_REQUEST,ex);
    }

    //409
    @ExceptionHandler({
            CursoJaCadastradoException.class,
            CursoJaDesativadoException.class,
            CursoJaAtivadoException.class,
            EmailJaCadastradoException.class,
            MatriculaDuplicadaException.class,
            ProfessorComTurmaAtivaException.class,
            ProfessorJaDesativadoException.class,
            ProfessorJaAtivadoException.class,
            TurmaDuplicadaException.class,
            TurmaJaAtivadaException.class,
            TurmaJaDesativadaException.class,
            AdminNaoPodeSerModificadoException.class
    })
    public ResponseEntity<ErrorResponseDto> handleConflict(RuntimeException ex){
        return construirResposta(HttpStatus.CONFLICT,ex);
    }

    //422
    @ExceptionHandler({
            ListaCursosVazioException.class,
            ListaProfessorVazioException.class,
            ListaTurmaVaziaException.class,
            TurmaVaziaException.class
    })
    public ResponseEntity<ErrorResponseDto> handleUnprocessable(RuntimeException ex) {
        return construirResposta(HttpStatus.UNPROCESSABLE_CONTENT,ex);
    }

    //401
    @ExceptionHandler({
            CredenciaisInvalidasException.class,
            UsuarioInativoException.class
    })
    public ResponseEntity<ErrorResponseDto> handleUnauthorized(RuntimeException ex) {
        return  construirResposta(HttpStatus.UNAUTHORIZED, ex);
    }

    private ResponseEntity<ErrorResponseDto> construirResposta(HttpStatus status, RuntimeException ex) {
        ErrorResponseDto body = new ErrorResponseDto(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                ex.getMessage()
        );
        return ResponseEntity.status(status).body(body);
    }
}
