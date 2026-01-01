package com.moizesjr.passeio_api.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice // Transforma essa classe num interceptador global
public class ResourceExceptionHandler {

  @ExceptionHandler(RecursoNaoEncontradoException.class)
  public ResponseEntity<StandardError> recursoNaoEncontrado(RecursoNaoEncontradoException e,
      HttpServletRequest request) {

    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(HttpStatus.NOT_FOUND.value()); // Erro 404
    err.setError("Recurso não encontrado");
    err.setMessage(e.getMessage()); // A mensagem que consta no buscarPorId no Service
    err.setPath(request.getRequestURI());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

    ValidationError err = new ValidationError();

    err.setTimestamp(Instant.now());
    err.setStatus(HttpStatus.BAD_REQUEST.value()); // Erro 400
    err.setError("Erro de Validação");
    err.setMessage("Dados inválidos. Verifique os campos abaixo.");
    err.setPath(request.getRequestURI());

    // Aqui pegamos a lista de erros que o Spring encontrou e passamos para o nosso
    // objeto
    for (FieldError f : e.getBindingResult().getFieldErrors()) {
      err.addError(f.getField(), f.getDefaultMessage());
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
  }
}