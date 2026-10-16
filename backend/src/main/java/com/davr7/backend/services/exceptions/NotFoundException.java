package com.davr7.backend.services.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends StandardException {
  public NotFoundException(String message) {
    super(message, "ERR_NOT_FOUND", 404);
  }
}
