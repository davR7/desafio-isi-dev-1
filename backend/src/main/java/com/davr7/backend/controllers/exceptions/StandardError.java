package com.davr7.backend.controllers.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Getter
@SuperBuilder
public class StandardError {
  private OffsetDateTime timestamp;
  private String path;
  private Integer status;
  private String error;
}
