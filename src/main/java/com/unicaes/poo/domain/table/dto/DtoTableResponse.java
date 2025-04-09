package com.unicaes.poo.domain.table.dto;

import com.unicaes.poo.domain.table.Table;

public record DtoTableResponse(Long id, String number, int capacity) {
    public static DtoTableResponse fromEntity(Table mesa) {
        return new DtoTableResponse(mesa.getId(), mesa.getNumber(), mesa.getCapacity());
    }
}
