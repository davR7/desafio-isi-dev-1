package com.davr7.backend.controllers.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@SuperBuilder
public class ValidationError extends StandardError {
  private Map<String, String> messages;
}
