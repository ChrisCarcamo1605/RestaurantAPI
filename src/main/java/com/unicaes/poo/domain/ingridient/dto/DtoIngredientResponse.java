package com.unicaes.poo.domain.ingridient.dto;

public record DtoIngredientResponse (
    Long ingredientId,
    Long consumableId,
    String consumableName,
    Double quantity,
    String measurementUnit,
    Long productId,
    String productName
){}
