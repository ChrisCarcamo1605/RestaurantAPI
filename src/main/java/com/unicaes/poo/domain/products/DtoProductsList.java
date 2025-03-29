package com.unicaes.poo.domain.products;


import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;

import java.math.BigDecimal;

public record DtoProductsList(String name, BigDecimal priceCost, BigDecimal priceSell, String description
        , MeasurementUnit measurementUnit) {

    public DtoProductsList(Product product) {
        this(product.name = product.getName(),
                product.priceCost,
                product.priceSell,
                product.description,
                product.measurementUnit);
    }
}
