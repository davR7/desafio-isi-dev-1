package com.davr7.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;

public record ClubOutputDto(
    Integer id,
    String name,
    String acronym,
    BigDecimal cash,
    @JsonProperty("squad_value")
    BigDecimal squadValue,
    @JsonProperty("net_worth")
    BigDecimal netWorth,
    List<PlayerSummaryDto> players
) {}
