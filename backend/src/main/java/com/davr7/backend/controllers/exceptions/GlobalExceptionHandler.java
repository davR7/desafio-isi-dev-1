package com.davr7.backend.controllers.exceptions;

import com.davr7.backend.services.exceptions.StandardException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationError> handleExceptionValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
    Map<String, String> messages = ex.getBindingResult().getFieldErrors()
        .stream().collect(Collectors.toMap(
            FieldError::getField,
            FieldError::getDefaultMessage
        ));

    ValidationError err = ValidationError.builder()
        .timestamp(OffsetDateTime.now())
        .path(req.getRequestURI())
        .status(HttpStatus.BAD_REQUEST.value())
        .error("ERR_BAD_REQUEST")
        .messages(messages)
        .build();

    return ResponseEntity.status(400).body(err);
  }

  @ExceptionHandler(StandardException.class)
  public ResponseEntity<HttpError> handleExceptionResource(StandardException ex, HttpServletRequest req) {
    HttpError err = HttpError.builder()
        .timestamp(OffsetDateTime.now())
        .path(req.getRequestURI())
        .status(ex.getStatus())
        .error(ex.getError())
        .message(ex.getMessage())
        .build();

    return ResponseEntity.status(ex.getStatus()).body(err);
  }
}
