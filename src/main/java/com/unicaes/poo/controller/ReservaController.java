package com.unicaes.poo.controller;

import com.unicaes.poo.domain.reservas.dto.DtoReservaResponse;
import com.unicaes.poo.domain.reservas.dto.DtoSaveReserva;
import com.unicaes.poo.domain.reservas.dto.DtoUpdateReserva;
import com.unicaes.poo.domain.reservas.ReservaService;
import com.unicaes.poo.infra.exceptions.QueryException;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaResponse);
    }

    @GetMapping
    public ResponseEntity<List<DtoReservaResponse>> getAllReservas() {
        List<DtoReservaResponse> reservas = reservaService.getAllReservas();
        if (reservas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoReservaResponse> getReservaById(@PathVariable Long id) {
        try {
            DtoReservaResponse reservaResponse = reservaService.getReservaById(id);
            return ResponseEntity.ok(reservaResponse);
        } catch (QueryException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoReservaResponse> updateReserva(
            @PathVariable Long id, @RequestBody DtoUpdateReserva dtoUpdateReserva) {
        try {
            DtoReservaResponse updatedReserva = reservaService.updateReserva(id, dtoUpdateReserva);
            return ResponseEntity.ok(updatedReserva);
        } catch (QueryException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        try {
            reservaService.deleteReserva(id);
            return ResponseEntity.noContent().build();
        } catch (QueryException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
