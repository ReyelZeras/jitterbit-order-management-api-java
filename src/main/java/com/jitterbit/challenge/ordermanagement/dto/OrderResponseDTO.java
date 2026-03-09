package com.jitterbit.challenge.ordermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public record OrderResponseDTO(
        String orderId,
        BigDecimal value,
        OffsetDateTime creationDate,
        List<ItemResponseDTO> items
) {}
