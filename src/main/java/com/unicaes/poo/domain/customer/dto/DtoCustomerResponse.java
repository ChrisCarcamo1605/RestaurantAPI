package com.unicaes.poo.domain.customer.dto;

import com.unicaes.poo.domain.customer.Customer;

public record DtoCustomerResponse(Long id, String name) {
    public static DtoCustomerResponse fromEntity(Customer cliente) {
        return new DtoCustomerResponse(cliente.getId(), cliente.getName());
    }
}
