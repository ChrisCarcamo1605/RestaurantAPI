package com.unicaes.poo.domain.cliente.dto;

import com.unicaes.poo.domain.cliente.Cliente;

public record DtoClienteSave(String nombre) {
    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        return cliente;
    }
}

