package com.davr7.backend.dtos;

import com.davr7.backend.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransactionOutputDto(
    Integer id,
    @JsonProperty("player_id")
    Integer playerId,
    @JsonProperty("player_name")
    String playerName,
    TransactionType type,
    BigDecimal value,
    BigDecimal fee,
    BigDecimal profit,
    @JsonProperty("cash_change")
    BigDecimal cashChange,
    @JsonProperty("created_at")
    OffsetDateTime createdAt,
    ContractOutputDto contract,
    @JsonProperty("club_id")
    Integer clubId,
    @JsonProperty("club_cash")
    BigDecimal clubCash
) {}
