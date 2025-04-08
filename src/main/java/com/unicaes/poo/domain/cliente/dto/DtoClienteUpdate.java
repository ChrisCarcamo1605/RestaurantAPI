package com.unicaes.poo.domain.cliente.dto;

import com.unicaes.poo.domain.cliente.Cliente;

public record DtoClienteUpdate(String nombre, String correo) {
    public void updateEntity(Cliente cliente) {
        cliente.setNombre(nombre);

    }
}
