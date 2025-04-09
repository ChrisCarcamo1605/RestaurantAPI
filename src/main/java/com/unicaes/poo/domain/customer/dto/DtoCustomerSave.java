package com.unicaes.poo.domain.customer.dto;

import com.unicaes.poo.domain.customer.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoCustomerSave(@NotBlank String name) {
    public Customer toEntity() {
        Customer customer = new Customer();
        customer.setName(name);
        return customer;
    }
}

