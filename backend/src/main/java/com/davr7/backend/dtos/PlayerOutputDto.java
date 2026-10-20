package com.davr7.backend.dtos;

import com.davr7.backend.enums.PlayerCondition;
import com.davr7.backend.enums.PlayerPosition;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public record PlayerOutputDto(
    Integer id,
    String name,
    Integer age,
    PlayerPosition position,
    PlayerCondition condition,
    Integer agility,
    Integer pace,
    Integer shooting,
    Integer passing,
    Integer dribbling,
    Integer defending,
    Integer physical,
    Integer potential,
    Integer overall,
    @JsonProperty("base_value")
    BigDecimal baseValue,
    @JsonProperty("market_value")
    BigDecimal marketValue,
    @JsonProperty("club_id")
    Integer clubId
) {}
