package com.unicaes.poo.controller;

import com.unicaes.poo.domain.consumables.IConsumable;
import com.unicaes.poo.domain.consumables.dto.*;
import com.unicaes.poo.infra.exceptions.ConsumableException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/consumable")
public class ConsumableController {

    private final IConsumable consumableService;

    public ConsumableController(IConsumable consumableService) {
        this.consumableService = consumableService;
    }

    @PostMapping
    public ResponseEntity<?> createConsumable(
            @Valid @RequestBody DtoConsumableSave dto,
            UriComponentsBuilder uriBuilder) {
        try {
            DtoConsumableResponse response = consumableService.createConsumable(dto);
            URI uri = uriBuilder.path("/consumable/{id}").buildAndExpand(response.consumableId()).toUri();
            return ResponseEntity.created(uri).body(response);
        } catch (ConsumableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateConsumable(
            @PathVariable Long id,
            @Valid @RequestBody DtoConsumableUpdate dto) {
        try {
            DtoConsumableResponse response = consumableService.updateConsumable(id, dto);
            return ResponseEntity.ok(response);
        } catch (ConsumableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deactivateConsumable(@PathVariable Long id) {
        try {
            consumableService.deactivateConsumable(id);
            return ResponseEntity.noContent().build();
        } catch (ConsumableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<DtoConsumableList>> getAllConsumables(
            @PageableDefault(size = 100) Pageable pageable) {
        return ResponseEntity.ok(consumableService.getAllConsumable(pageable));
    }


}