package com.unicaes.poo.domain.supplier.dto;

import jakarta.validation.constraints.*;

/**
 * DTO for supplier listing with validation constraints
 */
public record DtoSupplierList(

        Long id,


        String name,

        String contact,


        String address,


        Boolean active
) {}