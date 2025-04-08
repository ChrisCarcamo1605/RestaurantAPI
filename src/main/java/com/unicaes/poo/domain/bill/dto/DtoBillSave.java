package com.unicaes.poo.domain.bill.dto;

import com.unicaes.poo.domain.cliente.Cliente;
import com.unicaes.poo.domain.user.User;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DtoBillSave(
                          @NotNull
                          LocalDateTime emissionDate,

                          Long customer,
                          @NotNull
                          Long waiter,

                          LocalDateTime doneDate,
                          @NotNull
                          BigDecimal totalAmount,

                          Boolean active) {
}
