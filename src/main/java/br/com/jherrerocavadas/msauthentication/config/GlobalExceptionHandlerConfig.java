package br.com.jherrerocavadas.msauthentication.config;

import br.com.jherrerocavadas.msauthentication.exception.dto.StandardExceptionResponseDTO;
import br.com.jherrerocavadas.msauthentication.exception.throwable.ApiNotRegisteredException;
import br.com.jherrerocavadas.msauthentication.exception.throwable.ApiUnauthorizedException;
import br.com.jherrerocavadas.msauthentication.exception.throwable.InvalidRequestFieldsException;
import ch.qos.logback.core.model.processor.ProcessorException;
import io.jsonwebtoken.JwtException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandlerConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<StandardExceptionResponseDTO> handleException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(StandardExceptionResponseDTO.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .error("Erro interno")
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<StandardExceptionResponseDTO> handleRuntimeException(RuntimeException ex){
        logger.error(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(StandardExceptionResponseDTO.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .error("Erro ao processar a requisição")
                        .message("Ocorreu um erro interno ao processar a requisição")
                        .build());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<StandardExceptionResponseDTO> handleBadRequestException(BadRequestException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(StandardExceptionResponseDTO.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .error("Requisição inválida")
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler({ApiUnauthorizedException.class})
    public ResponseEntity<StandardExceptionResponseDTO> handleApiUnauthorizedException(ApiUnauthorizedException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
                .body(StandardExceptionResponseDTO.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .error("Credenciais inválidas")
                        .message(ex.getMessage())
                        .build());
    }
    @ExceptionHandler({JwtException.class})
    public ResponseEntity<StandardExceptionResponseDTO> handleJwtException(JwtException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
                .body(StandardExceptionResponseDTO.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .error("Credenciais inválidas")
                        .message(ex.getMessage())
                        .build());
    }


    @ExceptionHandler({ApiNotRegisteredException.class})
    public ResponseEntity<StandardExceptionResponseDTO> handleApiNotRegisteredException(ApiNotRegisteredException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(StandardExceptionResponseDTO.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .error("Recurso não encontrado")
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler({HttpServerErrorException.InternalServerError.class})
    public ResponseEntity<StandardExceptionResponseDTO> handleNotFoundException(HttpServerErrorException.InternalServerError ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(StandardExceptionResponseDTO.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .error("Erro ao processar a requisição")
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler({ProcessorException.class})
    public ResponseEntity<StandardExceptionResponseDTO> handleProcessorException(ProcessorException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(StandardExceptionResponseDTO.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .error("Erro de processamento")
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler({InvalidRequestFieldsException.class})
    public ResponseEntity<StandardExceptionResponseDTO> handleInvalidRequestFieldsException(InvalidRequestFieldsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(StandardExceptionResponseDTO.builder()
                        .timestamp(LocalDateTime.now())
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .error("Campos inválidos")
                        .message(ex.getMessage())
                        .build());
    }
}
