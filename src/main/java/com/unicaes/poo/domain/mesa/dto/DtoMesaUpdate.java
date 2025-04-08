package com.unicaes.poo.domain.mesa.dto;

import com.unicaes.poo.domain.mesa.Mesa;

public record DtoMesaUpdate(String numero, int capacidad) {
    public void updateEntity(Mesa mesa) {
        mesa.setNumero(numero);
        mesa.setCapacidad(capacidad);
    }
}
