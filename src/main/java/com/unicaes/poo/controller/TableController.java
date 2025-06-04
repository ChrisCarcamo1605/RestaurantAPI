package com.unicaes.poo.controller;

import com.unicaes.poo.interfaces.table.ITableService;
import com.unicaes.poo.domain.table.dto.*;
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
    public ResponseEntity<List<DtoTableList>> findAll() {
        return ResponseEntity.ok().body(mesaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoTableResponse> findById(@PathVariable Long id, UriComponentsBuilder uriBuilder) {

        return ResponseEntity.ok().body(mesaService.findById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DtoTableResponse> save(@RequestBody DtoTableSave dto, UriComponentsBuilder uriBuilder) {
        var table = mesaService.save(dto);
        URI uri = uriBuilder.path("/tables/{id}").buildAndExpand(table.id()).toUri();

        return ResponseEntity.created(uri).body(table);
    }

    @PutMapping()
    @Transactional
    public ResponseEntity<DtoTableResponse> update(@RequestBody DtoTableUpdate dto) {
        return ResponseEntity.accepted().body(mesaService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        return ResponseEntity.noContent().build();
    }
}
