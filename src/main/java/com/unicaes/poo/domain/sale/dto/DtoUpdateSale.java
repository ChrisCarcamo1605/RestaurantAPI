package com.unicaes.poo.domain.sale.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DtoUpdateSale(
        Long billId,
        LocalDate saleDate,
        BigDecimal total) {
}
