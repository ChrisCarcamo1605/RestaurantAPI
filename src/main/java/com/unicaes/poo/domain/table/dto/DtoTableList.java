package com.unicaes.poo.domain.table.dto;

import com.unicaes.poo.domain.table.Table;

public record DtoTableList(Long id, String number, int capacity) {
    public static DtoTableList fromEntity(Table mesa) {
        return new DtoTableList(mesa.getId(), mesa.getNumber(),mesa.getCapacity());
    }
}
