package com.unicaes.poo.domain.cliente.dto;

import com.unicaes.poo.domain.cliente.Cliente;

public record DtoClienteResponse(Long id, String nombre) {
    public static DtoClienteResponse fromEntity(Cliente cliente) {
        return new DtoClienteResponse(cliente.getId(), cliente.getNombre());
    }
}
