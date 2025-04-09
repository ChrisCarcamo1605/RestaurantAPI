package com.unicaes.poo.domain.supplier.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DtoSupplierSave(@NotBlank(message = "Name cannot be blank")
                              @Size(max = 20, message = "Name must be ≤ 20 characters")
                              String name,

                              @NotBlank(message = "Contact email cannot be blank")
                              @Email(message = "Invalid email format")
                              String contact,

                              @NotBlank(message = "Address cannot be blank")
                              @Size(max = 200, message = "Address must be ≤ 200 characters")
                              String address) {
}
