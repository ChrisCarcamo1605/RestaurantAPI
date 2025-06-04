package com.unicaes.poo.domain.reservation.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DtoSaveReservation(
        @NotNull(message = "El ID de la mesa es obligatorio")
        Long tableId,

        @NotBlank(message = "El nombre del cliente no puede estar en blanco")
        @Size(max = 100, message = "El nombre del cliente debe tener como máximo 100 caracteres")
        String client,

        @NotNull(message = "La fecha y hora de la reserva son obligatorias")
        @FutureOrPresent(message = "La fecha de la reserva debe ser en el presente o futuro")
        LocalDateTime dateTime,

        @NotNull(message = "El monto de la reserva es obligatorio")
        @Positive(message = "El monto de la reserva debe ser un número positivo")
        BigDecimal reservationAmount
) {}
