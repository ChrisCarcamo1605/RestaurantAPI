package com.unicaes.poo.domain.table.dto;

import com.unicaes.poo.domain.table.Table;

public record DtoTableSave(String numero, int capacidad) {
    public Table toEntity() {
        Table mesa = new Table();
        mesa.setNumber(numero);
        mesa.setCapacity(capacidad);
        return mesa;
    }
}
