package com.unicaes.poo.domain.ingredient.dto;

import com.unicaes.poo.domain.ingredient.Ingredient;
import com.unicaes.poo.domain.products.dto.DtoProductResponse;

public record DtoIngredientResponse (
    Long ingredientId,
    Long consumableId,
    String consumableName,
    Double quantity,
    String measurementUnit,
    Long productId,
    String productName
){

    public DtoIngredientResponse(Ingredient ingredient){ this (ingredient.getId(),
                ingredient.getConsumable().getId(),
                ingredient.getConsumable().getName(),
                ingredient.getQuantity(),
                ingredient.getConsumable().getMeasurementUnit().name()
                ,ingredient.getProduct().getId(),

                ingredient.getProduct().getName());
    }
}
