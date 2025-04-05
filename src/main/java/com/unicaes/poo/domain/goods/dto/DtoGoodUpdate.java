package com.unicaes.poo.domain.goods.dto;

import com.unicaes.poo.domain.goodsTypes.GoodsTypes;
import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;
import jakarta.validation.constraints.*;

public record DtoGoodUpdate(
        @Size(max = 100, message = "Name must be ≤ 100 characters")
        String name,

        @Positive(message = "Price must be positive")
        Double price,

        @PositiveOrZero(message = "Stock cannot be negative")
        Double stock,

        Long supplierId,

        GoodsTypes goodType,

        MeasurementUnit measurementUnit
) {}