package com.unicaes.poo.domain.customer.dto;

import com.unicaes.poo.domain.customer.Customer;

public record DtoCustomerList(Long id, String nombre) {
    public static DtoCustomerList fromEntity(Customer cliente) {
        return new DtoCustomerList(cliente.getId(), cliente.getName());
    }
}
