package com.davr7.backend.dtos;

import com.davr7.backend.enums.PlayerPosition;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlayerInputDto(
    @NotBlank(message = "O nome não pode estar em branco")
    String name,

    @NotNull(message = "A idade é obrigatória")
    Integer age,

    @NotNull(message = "A posição é obrigatória")
    PlayerPosition position,

    @NotNull(message = "A agilidade é obrigatória")
    Integer agility,

    @NotNull(message = "O pace é obrigatório")
    Integer pace,

    @NotNull(message = "O shooting é obrigatório")
    Integer shooting,

    @NotNull(message = "O passing é obrigatório")
    Integer passing,

    @NotNull(message = "O dribbling é obrigatório")
    Integer dribbling,

    @NotNull(message = "O defending é obrigatório")
    Integer defending,

    @NotNull(message = "O physical é obrigatório")
    Integer physical,

    @NotNull(message = "O potential é obrigatório")
    Integer potential
) {}
