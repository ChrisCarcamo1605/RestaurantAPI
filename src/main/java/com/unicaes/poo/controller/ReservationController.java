package com.unicaes.poo.controller;

import com.unicaes.poo.domain.reservation.dto.DtoReservationResponse;
import com.unicaes.poo.domain.reservation.dto.DtoSaveReservation;
import com.unicaes.poo.domain.reservation.dto.DtoUpdateReservation;
import com.unicaes.poo.domain.reservation.ReservationServiceImpl;
import com.unicaes.poo.payload.MessageResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationServiceImpl reservationService;

    @PostMapping
    @Transactional
    public ResponseEntity<MessageResponse> saveReservation(@RequestBody DtoSaveReservation dtoSaveReservation) {
        var reservationResponse = reservationService.saveReservation(dtoSaveReservation);
        return ResponseEntity.status(201).body(
                MessageResponse.builder()
                        .message("Reservación creada exitosamente")
                        .data(reservationResponse)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<MessageResponse> getAllReservations() {
        var reservations = reservationService.getAllReservations();
        if (reservations.isEmpty()) {
            return ResponseEntity.ok(
                    MessageResponse.builder()
                            .message("No hay reservaciones disponibles")
                            .data(List.of())
                            .build()
            );
        }
        return ResponseEntity.ok(
                MessageResponse.builder()
                        .message("Listado de reservaciones obtenido correctamente")
                        .data(reservations)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getReservationById(@PathVariable Long id) {
        var reservationResponse = reservationService.getReservationById(id);
        return ResponseEntity.ok(
                MessageResponse.builder()
                        .message("Reservación obtenida correctamente")
                        .data(reservationResponse)
                        .build()
        );
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MessageResponse> updateReservation(@PathVariable Long id, @RequestBody DtoUpdateReservation dtoUpdateReservation) {
        var updatedReservation = reservationService.updateReservation(id, dtoUpdateReservation);
        return ResponseEntity.accepted().body(
                MessageResponse.builder()
                        .message("Reservación actualizada exitosamente")
                        .data(updatedReservation)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok(
                MessageResponse.builder()
                        .message("Reservación eliminada correctamente")
                        .data(null)
                        .build()
        );
    }
}
