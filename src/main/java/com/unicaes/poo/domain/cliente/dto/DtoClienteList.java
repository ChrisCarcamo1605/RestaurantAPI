package com.unicaes.poo.domain.cliente.dto;

import com.unicaes.poo.domain.cliente.Cliente;

public record DtoClienteList(Long id, String nombre) {
    public static DtoClienteList fromEntity(Cliente cliente) {
        return new DtoClienteList(cliente.getId(), cliente.getNombre());
    }
}
