package com.unicaes.poo.controller;

import com.unicaes.poo.interfaces.table.ITableService;
import com.unicaes.poo.domain.table.dto.*;
import com.unicaes.poo.payload.MessageResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private ITableService mesaService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(MessageResponse.builder()
                .message("Mesas obtenidas correctamente").data(mesaService.findAll()).build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        return ResponseEntity.ok().body(MessageResponse.builder().message("Mesa obtenida exitosamente").data(mesaService.findById(id)).build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody DtoTableSave dto, UriComponentsBuilder uriBuilder) {
        var table = mesaService.save(dto);
        URI uri = uriBuilder.path("/tables/{id}").buildAndExpand(table.id()).toUri();
        return ResponseEntity.created(uri)
                .body(MessageResponse.builder().message("Mesa agregada exitosamente").data(table).build());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody DtoTableUpdate dto) {
        return ResponseEntity.accepted().body(MessageResponse.builder()
                .message("Mesas actualizadas exitosamente").data(mesaService.update(dto)).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        mesaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
