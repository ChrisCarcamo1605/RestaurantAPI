package com.unicaes.poo.domain.bill.dto;

import jakarta.validation.constraints.NotNull;

public record DtoBillDetailsSave(
        @NotNull
        Long billId,
        @NotNull
        Long productId,
        @NotNull
        Integer quantity
) {
}