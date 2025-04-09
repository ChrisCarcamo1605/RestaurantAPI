package com.unicaes.poo.domain.sale.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DtoSaveSale(
        @NotNull
        Long billId,

        @NotNull
        BigDecimal total,

        @NotNull
        LocalDate saleDate) {
}
