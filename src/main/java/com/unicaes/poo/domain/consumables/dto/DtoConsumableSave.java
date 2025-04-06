package com.unicaes.poo.domain.consumables.dto;

import com.unicaes.poo.domain.consumableTypes.ConsumableTypes;
import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public record DtoConsumableSave(
        @NotBlank(message = "Name cannot be blank")
        @Size(max = 100, message = "Name must be ≤ 100 characters")

        String name,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be positive")
        Double price,

        @NotNull(message = "Stock is required")
        @PositiveOrZero(message = "Stock cannot be negative")
        Double stock,

        @NotNull(message = "Supplier ID is required")
        Long supplierId,

        @NotNull(message = "consumable type is required")
        @Column(name = "consumableTypes")
        ConsumableTypes consumableTypes,

        @NotNull(message = "Measurement unit is required")
        MeasurementUnit measurementUnit
) {}