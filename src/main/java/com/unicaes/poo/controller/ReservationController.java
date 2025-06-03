package com.unicaes.poo.controller;

import com.unicaes.poo.domain.reservation.dto.DtoReservationResponse;
import com.unicaes.poo.domain.reservation.dto.DtoSaveReservation;
import com.unicaes.poo.domain.reservation.dto.DtoUpdateReservation;
import com.unicaes.poo.domain.reservation.ReservationService;
import com.unicaes.poo.payload.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<MessageResponse<DtoReservationResponse>> saveReservation(@RequestBody DtoSaveReservation dtoSaveReservation) {
        var reservationResponse = reservationService.saveReservation(dtoSaveReservation);
        return ResponseEntity.status(201).body(
                MessageResponse.build("Reserva creada exitosamente", reservationResponse)
        );
    }

    @GetMapping
    public ResponseEntity<MessageResponse<List<DtoReservationResponse>>> getAllReservations() {
        var reservations = reservationService.getAllReservations();
        if (reservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(
                MessageResponse.build("Reservas obtenidas correctamente", reservations)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse<DtoReservationResponse>> getReservationById(@PathVariable Long id) {
        var reservationResponse = reservationService.getReservationById(id);
        return ResponseEntity.ok(
                MessageResponse.build("Reserva obtenida exitosamente", reservationResponse)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse<DtoReservationResponse>> updateReservation(@PathVariable Long id, @RequestBody DtoUpdateReservation dtoUpdateReservation) {
        var updatedReservation = reservationService.updateReservation(id, dtoUpdateReservation);
        return ResponseEntity.accepted().body(
                MessageResponse.build("Reserva actualizada exitosamente", updatedReservation)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse<String>> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok(
                MessageResponse.build("Reserva eliminada exitosamente", null)
        );
    }
}
