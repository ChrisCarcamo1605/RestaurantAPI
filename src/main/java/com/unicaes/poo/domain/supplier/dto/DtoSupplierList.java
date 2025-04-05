package com.unicaes.poo.domain.supplier.dto;

import jakarta.validation.constraints.*;

/**
 * DTO for supplier listing with validation constraints
 */
public record DtoSupplierList(
        @NotBlank(message = "Name cannot be blank")
        @Size(max = 20, message = "Name must be ≤ 20 characters")
        String name,

        @NotBlank(message = "Contact email cannot be blank")
        @Email(message = "Invalid email format")
        String contact,

        @NotBlank(message = "Address cannot be blank")
        @Size(max = 200, message = "Address must be ≤ 200 characters")
        String address,

        @NotNull(message = "Active status is required")
        Boolean active
) {}