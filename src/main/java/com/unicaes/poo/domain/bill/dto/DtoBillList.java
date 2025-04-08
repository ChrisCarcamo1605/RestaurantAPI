package com.unicaes.poo.domain.bill.dto;

import com.unicaes.poo.domain.bill.Bill;
import com.unicaes.poo.domain.cliente.Cliente;
import com.unicaes.poo.domain.user.User;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DtoBillList(
        Long id,
        LocalDateTime emissionDate,
        String customer,
        String waiter,
        LocalDateTime doneDate,
        BigDecimal totalAmount,
        Boolean active) {

    public static DtoBillList toEntity(Bill bill) {

        return new DtoBillList(bill.getId(),
                bill.getEmissionDate(),
                bill.getCustomer().getNombre(),
                bill.getWaiter().getUsername(),
                bill.getDoneDate(),
                bill.getTotalAmount(),
                bill.getActive());
    }

}
