package com.unicaes.poo.domain.sale.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record DtoUpdateSale(
        @NotNull(message = "El ID de la factura es obligatorio")
        Long billId,

        @NotNull(message = "La fecha de la venta es obligatoria")
        @PastOrPresent(message = "La fecha de la venta no puede ser futura")
        LocalDate saleDate,

        @NotNull(message = "El total de la venta es obligatorio")
        @Positive(message = "El total de la venta debe ser un número positivo")
        BigDecimal total
) {}
