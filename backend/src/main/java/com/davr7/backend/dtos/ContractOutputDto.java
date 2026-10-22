package com.davr7.backend.dtos;

import com.davr7.backend.enums.ContractStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ContractOutputDto(
    @JsonProperty("purchase_value")
    BigDecimal purchaseValue,
    @JsonProperty("loyalty_until")
    OffsetDateTime loyaltyUntil,
    ContractStatus status
) {}
