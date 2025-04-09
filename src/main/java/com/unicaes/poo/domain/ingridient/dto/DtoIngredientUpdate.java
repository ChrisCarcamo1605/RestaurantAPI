package com.unicaes.poo.domain.ingridient.dto;

import jakarta.validation.constraints.Positive;

public record DtoIngredientUpdate(
        @Positive(message = "El ID del consumible debe ser positivo")
        Long consumableId,

        @Positive(message = "La cantidad debe ser positiva")
        Double quantity,

        @Positive(message = "El ID del producto debe ser positivo")
        Long productId
) {
    public DtoIngredientUpdate {
        // Validación: al menos un campo debe ser no nulo
        if (consumableId == null && quantity == null && productId == null) {
            throw new IllegalArgumentException("Al menos un campo debe ser proporcionado para actualizar");
        }
    }
}