package com.unicaes.poo.domain.table.dto;

import com.unicaes.poo.domain.table.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DtoTableSave(
        @NotBlank
        String number,
        @NotNull
        @Size(max = 3,min = 2, message = "Name must be ≤ 3 characters")
        Integer capacity) {




    public Table toEntity() {
        Table mesa = new Table();
        mesa.setNumber(number);
        mesa.setCapacity(capacity);
        return mesa;
    }
}
