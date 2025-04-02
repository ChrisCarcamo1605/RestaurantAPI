package com.unicaes.poo.domain.products.dto;


import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;
import com.unicaes.poo.domain.products.Product;

import java.math.BigDecimal;

public record DtoProductsList(String name, BigDecimal priceCost, BigDecimal priceSell, String description
        , MeasurementUnit measurementUnit) {

    public DtoProductsList(Product product) {
        this(product.getName(),
                product.getPriceCost(),
                product.getPriceSell(),
                product.getDescription(),
                product.getMeasurementUnit());
    }
}
