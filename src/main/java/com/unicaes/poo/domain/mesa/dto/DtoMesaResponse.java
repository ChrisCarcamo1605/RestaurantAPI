package com.unicaes.poo.domain.mesa.dto;

import com.unicaes.poo.domain.mesa.Mesa;

public record DtoMesaResponse(Long id, String numero, int capacidad) {
    public static DtoMesaResponse fromEntity(Mesa mesa) {
        return new DtoMesaResponse(mesa.getId(), mesa.getNumero(), mesa.getCapacidad());
    }
}
