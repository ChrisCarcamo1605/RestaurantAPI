package com.unicaes.poo.domain.mesa.dto;

import com.unicaes.poo.domain.mesa.Mesa;

public record DtoMesaSave(String numero, int capacidad) {
    public Mesa toEntity() {
        Mesa mesa = new Mesa();
        mesa.setNumero(numero);
        mesa.setCapacidad(capacidad);
        return mesa;
    }
}
