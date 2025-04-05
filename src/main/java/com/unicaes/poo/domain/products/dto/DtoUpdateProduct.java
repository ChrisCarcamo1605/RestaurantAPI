package com.unicaes.poo.domain.products.dto;

import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;
import jakarta.validation.constraints.Email;

import java.math.BigDecimal;

public record DtoUpdateProduct(
                                long id,
                               String name,
                               BigDecimal priceCost,
                               BigDecimal priceSell,
                               MeasurementUnit measurementUnit,
                               String description,
                               @Email
                               String email
) {
}
