package com.unicaes.poo.domain.consumables.dto;

import com.unicaes.poo.domain.consumableTypes.ConsumableTypes;
import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;

public record DtoConsumableResponse(
        Long consumableId,
        String name,
        Double price,
        Double stock,
        Long supplierId,
        ConsumableTypes consumableTypes,
        MeasurementUnit measurementUnit,
        Boolean active
) {}