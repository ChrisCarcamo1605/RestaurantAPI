package com.unicaes.poo.domain.customer.dto;

import com.unicaes.poo.domain.customer.Customer;

public record DtoCustomerSave(String nombre) {
    public Customer toEntity() {
        Customer cliente = new Customer();
        cliente.setName(nombre);
        return cliente;
    }
}

