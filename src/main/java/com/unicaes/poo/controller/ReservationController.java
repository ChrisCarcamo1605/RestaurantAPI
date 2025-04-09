package com.unicaes.poo.controller;

import com.unicaes.poo.domain.reservation.dto.DtoReservationResponse;
import com.unicaes.poo.domain.reservation.dto.DtoSaveReservation;
import com.unicaes.poo.domain.reservation.dto.DtoUpdateReservation;
import com.unicaes.poo.domain.reservation.ReservationService;
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
    public ResponseEntity<DtoReservationResponse> saveReservation(@RequestBody DtoSaveReservation dtoSaveReservation) {
        var reservationResponse = reservationService.saveReservation(dtoSaveReservation);
        return ResponseEntity.status(201).body(reservationResponse);
    }

    @GetMapping
    public ResponseEntity<List<DtoReservationResponse>> getAllReservations() {
        var reservations = reservationService.getAllReservations();
        return reservations.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(reservations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoReservationResponse> getReservationById(@PathVariable Long id) {
        var reservationResponse = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservationResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoReservationResponse> updateReservation(@PathVariable Long id, @RequestBody DtoUpdateReservation dtoUpdateReservation) {
        var updatedReservation = reservationService.updateReservation(id, dtoUpdateReservation);
        return ResponseEntity.accepted().body(updatedReservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
