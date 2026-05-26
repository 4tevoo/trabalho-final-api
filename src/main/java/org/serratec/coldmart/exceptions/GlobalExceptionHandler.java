package org.serratec.coldmart.exceptions;

import org.serratec.coldmart.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult().getFieldErrors().stream().map(erro -> erro.getField() + ": " + erro.getDefaultMessage()).toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(erros, LocalDateTime.now()));
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<ErrorMessage> handleNaoEncontradoException(NaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(List.of(ex.getMessage()), LocalDateTime.now()));
    }

    @ExceptionHandler(EntidadeDuplicadaException.class)
    public ResponseEntity<ErrorMessage> handleEntidadeDuplicadaException(EntidadeDuplicadaException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(List.of(ex.getMessage()), LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleErroInterno(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(List.of("Erro interno no servidor"), LocalDateTime.now()));
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErrorMessage> handleRegraNegocioException(RegraNegocioException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(List.of(ex.getMessage()), LocalDateTime.now()));
    }
}
