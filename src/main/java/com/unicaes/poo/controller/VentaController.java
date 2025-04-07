package com.unicaes.poo.controller;

import com.unicaes.poo.domain.venta.dto.DtoVentaResponse;
import com.unicaes.poo.domain.venta.dto.DtoVentasList;
import com.unicaes.poo.domain.venta.dto.DtoSaveVenta;
import com.unicaes.poo.domain.venta.VentaService;
import com.unicaes.poo.domain.venta.dto.DtoUpdateVenta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    // Crear una nueva venta (POST)
    @PostMapping
    public ResponseEntity<DtoVentaResponse> addVenta(@Valid @RequestBody DtoSaveVenta dto, UriComponentsBuilder uriBuilder) {

        var venta = ventaService.save(dto);

        var response = new DtoVentaResponse(venta.getIdVenta(), venta.getIdTicket());
        URI uri = uriBuilder.path("venta/{id}").buildAndExpand(venta.getIdVenta()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    // Obtener la lista de ventas activas (GET)
    @GetMapping
    public ResponseEntity<Page<DtoVentasList>> getVentasList(@PageableDefault(size = 3) Pageable pageable) {
        var ventas = ventaService.getVentasList(pageable);

        return ResponseEntity.ok(ventas);
    }

    // Actualizar una venta existente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<DtoVentaResponse> updateVenta(@PathVariable Long id, @Valid @RequestBody DtoUpdateVenta dto) {

        var venta = ventaService.updateVenta(id, dto);

        return ResponseEntity.ok().body(new DtoVentaResponse(venta.getIdVenta(), venta.getIdTicket()));
    }

    // Eliminar (marcar como inactiva) una venta (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build(); // No hay contenido en la respuesta
    }
}
