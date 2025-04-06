package com.unicaes.poo.domain.ingridient.dto;
import jakarta.validation.constraints.*;

public record DtoIngredientSave(
        @NotNull(message = "El ID del consumible es requerido")
        @Positive(message = "El ID del consumible debe ser positivo")
        Long consumableId,

        @NotNull(message = "La cantidad es requerida")
        @Positive(message = "La cantidad debe ser positiva")
        Double quantity,

        @NotNull(message = "El ID del producto es requerido")
        @Positive(message = "El ID del producto debe ser positivo")
        Long productId
) {}