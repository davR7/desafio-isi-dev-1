package com.davr7.backend.dtos;

import jakarta.validation.constraints.NotBlank;

public record ClubInputDto(
    @NotBlank(message = "O nome não pode estar em branco")
    String name,

    @NotBlank(message = "A sigla não pode estar em branco")
    String acronym
) {}
