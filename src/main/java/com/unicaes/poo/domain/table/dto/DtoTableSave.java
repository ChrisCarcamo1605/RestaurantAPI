package com.unicaes.poo.domain.table.dto;

import com.unicaes.poo.domain.table.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoTableSave(
        @NotBlank
        String number,
        @NotNull
        Integer capacity) {




    public Table toEntity() {
        Table mesa = new Table();
        mesa.setNumber(number);
        mesa.setCapacity(capacity);
        return mesa;
    }
}
