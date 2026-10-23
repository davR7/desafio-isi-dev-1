package com.davr7.backend.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public record ClubSummaryDto(
    Integer id,
    String name,
    String acronym,
    BigDecimal cash,
    @JsonProperty("squad_value")
    BigDecimal squadValue,
    @JsonProperty("net_worth")
    BigDecimal netWorth
) {}
