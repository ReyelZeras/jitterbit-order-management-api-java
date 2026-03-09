package com.jitterbit.challenge.ordermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * Representa os itens dentro do JSON de entrada.
 */
public record ItemRequestDTO(
        @JsonProperty("idItem") String idItem,
        @JsonProperty("quantidadeItem") Integer quantidadeItem,
        @JsonProperty("valorItem") BigDecimal valorItem
) {}