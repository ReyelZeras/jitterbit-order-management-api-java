package com.jitterbit.challenge.ordermanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * DTO de resposta para os itens, separado para garantir visibilidade no Mapper.
 */
public record ItemResponseDTO(
        String productId,
        Integer quantity,
        BigDecimal price
) {}