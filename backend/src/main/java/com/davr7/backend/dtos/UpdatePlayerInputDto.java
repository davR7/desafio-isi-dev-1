package com.davr7.backend.dtos;

import com.davr7.backend.enums.PlayerPosition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdatePlayerInputDto(
    @NotBlank(message = "O nome é obrigatório")
    String name,

    @NotNull(message = "A idade é obrigatória")
    Integer age,

    @NotNull(message = "A posição é obrigatória")
    PlayerPosition position,

    @NotNull(message = "A agilidade é obrigatória")
    Integer agility
) {}
