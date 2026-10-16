package com.davr7.backend.services.exceptions;

import lombok.Getter;

@Getter
public class StandardException extends RuntimeException {
  private final String error;
  private final Integer status;

  public StandardException(String message, String error, Integer status) {
    super(message);
    this.error = error;
    this.status = status;
  }
}
