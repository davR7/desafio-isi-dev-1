package com.davr7.backend.controllers.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class HttpError extends StandardError {
  private String message;
}
