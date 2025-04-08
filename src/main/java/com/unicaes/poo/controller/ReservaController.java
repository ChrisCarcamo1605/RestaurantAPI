package com.unicaes.poo.controller;

import com.unicaes.poo.domain.reservas.dto.DtoReservaResponse;
import com.unicaes.poo.domain.reservas.dto.DtoSaveReserva;
import com.unicaes.poo.domain.reservas.dto.DtoUpdateReserva;
import com.unicaes.poo.domain.reservas.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<DtoReservaResponse> saveReserva(@RequestBody DtoSaveReserva dtoSaveReserva) {
        var reservaResponse = reservaService.saveReserva(dtoSaveReserva);
        return ResponseEntity.status(201).body(reservaResponse);
    }

    @GetMapping
    public ResponseEntity<List<DtoReservaResponse>> getAllReservas() {
        var reservas = reservaService.getAllReservas();
        return reservas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoReservaResponse> getReservaById(@PathVariable Long id) {
        var reservaResponse = reservaService.getReservaById(id);
        return ResponseEntity.ok(reservaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoReservaResponse> updateReserva(@PathVariable Long id, @RequestBody DtoUpdateReserva dtoUpdateReserva) {
        var updatedReserva = reservaService.updateReserva(id, dtoUpdateReserva);
        return ResponseEntity.ok(updatedReserva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaService.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }
}
