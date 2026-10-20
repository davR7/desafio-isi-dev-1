package com.davr7.backend.services.exceptions;

import lombok.Getter;

@Getter
public class ConflictException extends StandardException {
  public ConflictException(String message) {
    super(message, "ERR_CONFLICT", 409);
  }
}
