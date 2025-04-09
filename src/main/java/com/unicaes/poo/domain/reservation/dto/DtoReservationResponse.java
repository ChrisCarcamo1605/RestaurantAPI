package com.unicaes.poo.domain.reservation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DtoReservationResponse(
        Long id,
        Long tableId,
        String client,
        LocalDateTime dateTime,
        BigDecimal reservationAmount,
        Boolean active
) {}
