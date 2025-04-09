package com.unicaes.poo.domain.bill.dto;

import com.unicaes.poo.domain.bill.Bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DtoBillResponse(
        Long id,
        LocalDateTime emissionDate,
        String customer,
        String waiter,
        LocalDateTime doneDate,
        BigDecimal totalAmount,
        Boolean active) {
    public static DtoBillResponse toEntity(Bill bill) {

        return new DtoBillResponse(bill.getId(),
                bill.getEmissionDate(),
                bill.getCustomer().getName(),
                bill.getWaiter().getUsername(),
                bill.getDoneDate(),
                bill.getTotalAmount(),
                bill.getActive());
    }
}
