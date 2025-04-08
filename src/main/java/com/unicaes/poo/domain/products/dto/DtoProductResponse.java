package com.unicaes.poo.domain.products.dto;


import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;
import com.unicaes.poo.domain.products.Product;

import java.math.BigDecimal;

public record DtoProductResponse(long id, String name, String type,
                                 BigDecimal priceCost,
                                 BigDecimal priceSell,
                                 String description,
                                 MeasurementUnit measurementUnit) {

    public static DtoProductResponse fromEntity(Product product) {

        return new DtoProductResponse(
                product.getId(),
                product.getName(),
                product.getProductType().getName(),
                product.getPriceCost(),
                product.getPriceSell(),
                product.getDescription(),
                product.getMeasurementUnit());

    }
}
