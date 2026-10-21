package com.davr7.backend.dtos;

import com.davr7.backend.enums.PlayerCondition;
import jakarta.validation.constraints.NotNull;

public record UpdateConditionPlayerInputDto(
    @NotNull(message = "A condição é obrigatória")
    PlayerCondition condition
) {}
