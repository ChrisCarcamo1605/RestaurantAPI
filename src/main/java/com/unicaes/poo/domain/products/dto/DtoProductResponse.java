package com.unicaes.poo.domain.products.dto;


import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;

import java.math.BigDecimal;

public record DtoProductResponse(long id, String name,
                                 BigDecimal priceCost,
                                 BigDecimal priceSell,
                                 String description,
                                 MeasurementUnit measurementUnit) {
}
