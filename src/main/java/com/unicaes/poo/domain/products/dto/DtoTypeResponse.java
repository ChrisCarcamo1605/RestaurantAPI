package com.unicaes.poo.domain.products.dto;

import com.unicaes.poo.domain.products.ProductType;
import jakarta.validation.constraints.NotBlank;


public record DtoTypeResponse(Long id,
                           String name) {

    public static DtoTypeResponse fromEntiy(ProductType type){
        return new DtoTypeResponse(type.getTypeId(),type.getName());
    }
}
