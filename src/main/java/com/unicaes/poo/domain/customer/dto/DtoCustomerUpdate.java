package com.unicaes.poo.domain.customer.dto;

import com.unicaes.poo.domain.customer.Customer;

public record DtoCustomerUpdate(String name, String email) {
    public void updateEntity(Customer customer) {
        customer.setName(name);

    }
}
