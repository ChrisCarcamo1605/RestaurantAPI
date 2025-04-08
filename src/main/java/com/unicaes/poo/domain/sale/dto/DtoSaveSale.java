package com.unicaes.poo.domain.sale.dto;

import jakarta.validation.constraints.NotNull;

public record DtoSaveSale(
        @NotNull
        Long billId) {
}
