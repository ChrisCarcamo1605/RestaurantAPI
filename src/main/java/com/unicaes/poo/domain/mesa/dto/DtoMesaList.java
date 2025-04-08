package com.unicaes.poo.domain.mesa.dto;

import com.unicaes.poo.domain.mesa.Mesa;

public record DtoMesaList(Long id, String numero) {
    public static DtoMesaList fromEntity(Mesa mesa) {
        return new DtoMesaList(mesa.getId(), mesa.getNumero());
    }
}
