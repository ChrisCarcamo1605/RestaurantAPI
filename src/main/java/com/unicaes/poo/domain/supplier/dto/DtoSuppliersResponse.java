package com.unicaes.poo.domain.supplier.dto;

/**
 * DTO for supplier API responses
 */
public record DtoSuppliersResponse(
        Long supplierId,
        String name,
        String contact,
        String address,
        Boolean active
) {}