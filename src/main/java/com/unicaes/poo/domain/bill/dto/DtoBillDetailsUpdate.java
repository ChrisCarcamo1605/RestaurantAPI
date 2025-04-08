package com.unicaes.poo.domain.bill.dto;

import java.math.BigDecimal;

public record DtoBillDetailsUpdate(
        Long id,
        Integer quantity,
        BigDecimal unitPrice
) {

    public DtoBillDetailsUpdate withId(Long newId) {
        return new DtoBillDetailsUpdate(newId, this.quantity, this.unitPrice);
    }
}