package com.unicaes.poo.controller;

import com.unicaes.poo.domain.venta.dto.DtoVentaResponse;
import com.unicaes.poo.domain.venta.dto.DtoSaveVenta;
import com.unicaes.poo.domain.venta.dto.DtoUpdateVenta;
import com.unicaes.poo.domain.venta.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public ResponseEntity<DtoVentaResponse> saveVenta(@RequestBody DtoSaveVenta dtoSaveVenta) {
        DtoVentaResponse ventaResponse = ventaService.saveVenta(dtoSaveVenta);
        return ResponseEntity.ok(ventaResponse);
    }

    @GetMapping
    public ResponseEntity<List<DtoVentaResponse>> getAllVentas() {
        List<DtoVentaResponse> ventas = ventaService.getAllVentas();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoVentaResponse> getVentaById(@PathVariable Long id) {
        DtoVentaResponse ventaResponse = ventaService.getVentaById(id);
        return ResponseEntity.ok(ventaResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DtoVentaResponse> updateVenta(@PathVariable Long id, @RequestBody DtoUpdateVenta dtoUpdateVenta) {
        DtoVentaResponse updatedVenta = ventaService.updateVenta(id, dtoUpdateVenta);
        return ResponseEntity.ok(updatedVenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
}
