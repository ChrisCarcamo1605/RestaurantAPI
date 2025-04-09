package com.unicaes.poo.domain.supplier.dto;

import jakarta.validation.constraints.*;

public record DtoUpdateSupplier(
        @Size(max = 20, message = "Name must be ≤ 20 characters")
        String name,

        @Size(max = 50, message = "Contact must be ≤ 50 characters")
        @Email(message = "Invalid email format")
        String contact,

        @Size(max = 200, message = "Address must be ≤ 200 characters")
        String address
) {
        public DtoUpdateSupplier {
                // Validación adicional - al menos un campo debe ser no nulo
                if (name == null && contact == null && address == null) {
                        throw new IllegalArgumentException("At least one field must be provided for update");
                }
        }
}