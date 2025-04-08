package com.unicaes.poo.domain.products.dto;

import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DtoSaveProduct(
        @NotBlank
        String name,
        @NotNull
        Long type,
        @NotNull
        BigDecimal priceCost,
        @NotNull
        BigDecimal priceSell,
        @NotBlank
        String description,
        @NotNull
        MeasurementUnit measurementUnit
) {
}
