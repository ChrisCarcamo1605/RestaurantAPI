package com.unicaes.poo.domain.goods.dto;

import com.unicaes.poo.domain.goodsTypes.GoodsTypes;
import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;
import jakarta.validation.constraints.*;

public record DtoGoodSave(
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

        @NotNull(message = "Good type is required")
        GoodsTypes goodType,

        @NotNull(message = "Measurement unit is required")
        MeasurementUnit measurementUnit
) {}