package com.unicaes.poo.domain.reservas.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DtoReservaResponse(
        Long id,
        Long mesaId,
        String cliente,
        LocalDateTime fechaHora,
        BigDecimal montoReserva,
        Boolean activo
) {}
