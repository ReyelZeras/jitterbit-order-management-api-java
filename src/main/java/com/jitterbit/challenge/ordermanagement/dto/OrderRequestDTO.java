package com.jitterbit.challenge.ordermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Representa o JSON de entrada enviado pelo cliente.
 */
public record OrderRequestDTO(
        @JsonProperty("numeroPedido") String numeroPedido,
        @JsonProperty("valorTotal") BigDecimal valorTotal,
        @JsonProperty("dataCriacao") OffsetDateTime dataCriacao,
        @JsonProperty("items") List<ItemRequestDTO> items
) {}