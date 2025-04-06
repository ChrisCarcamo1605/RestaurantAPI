package com.unicaes.poo.domain.consumables.dto;

import com.unicaes.poo.domain.consumableTypes.ConsumableTypes;
import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;
import jakarta.validation.constraints.*;

public record DtoConsumableUpdate(
        @Size(max = 100, message = "Name must be ≤ 100 characters")
        String name,

        @Positive(message = "Price must be positive")
        Double price,

        @PositiveOrZero(message = "Stock cannot be negative")
        Double stock,

        Long supplierId,

        ConsumableTypes consumableTypes,

        MeasurementUnit measurementUnit
) {}