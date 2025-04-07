package com.unicaes.poo.controller;

import com.unicaes.poo.domain.reservas.dto.DtoReservaResponse;
import com.unicaes.poo.domain.reservas.dto.DtoSaveReserva;
import com.unicaes.poo.domain.reservas.dto.DtoUpdateReserva;
import com.unicaes.poo.domain.reservas.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<DtoReservaResponse> saveReserva(@RequestBody DtoSaveReserva dtoSaveReserva) {
        DtoReservaResponse reservaResponse = reservaService.saveReserva(dtoSaveReserva);
        return ResponseEntity.ok(reservaResponse);
    }

    @GetMapping
    public ResponseEntity<List<DtoReservaResponse>> getAllReservas() {
        List<DtoReservaResponse> reservas = reservaService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoReservaResponse> getReservaById(@PathVariable Long id) {
        DtoReservaResponse reservaResponse = reservaService.getReservaById(id);
        return ResponseEntity.ok(reservaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoReservaResponse> updateReserva(
            @PathVariable Long id, @RequestBody DtoUpdateReserva dtoUpdateReserva) {
        DtoReservaResponse updatedReserva = reservaService.updateReserva(id, dtoUpdateReserva);
        return ResponseEntity.ok(updatedReserva);
    }

    // Eliminar una reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}
