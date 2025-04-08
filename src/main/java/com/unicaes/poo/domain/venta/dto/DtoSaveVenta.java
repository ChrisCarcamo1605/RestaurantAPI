package com.unicaes.poo.domain.venta.dto;

import jakarta.validation.constraints.NotNull;

public record DtoSaveVenta(
        @NotNull
        Long bill_id) {
}
