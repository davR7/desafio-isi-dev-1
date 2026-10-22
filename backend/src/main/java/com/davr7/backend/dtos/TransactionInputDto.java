package com.davr7.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record TransactionInputDto(
    @NotNull(message = "club_id é obrigatório")
    @JsonProperty("club_id")
    Integer clubId,
    @NotNull(message = "loyalty_until é obrigatório")
    @JsonProperty("loyalty_until")
    OffsetDateTime loyaltyUntil
) {}
