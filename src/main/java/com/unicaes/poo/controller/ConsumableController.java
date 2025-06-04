package com.unicaes.poo.controller;

import com.unicaes.poo.interfaces.consumable.Consumable;
import com.unicaes.poo.domain.consumables.dto.*;
import com.unicaes.poo.payload.MessageResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/consumable")
public class ConsumableController {

    private final Consumable consumableService;

    public ConsumableController(Consumable consumableService) {
        this.consumableService = consumableService;
    }
    @PostMapping
    @Transactional
    public ResponseEntity<?> createConsumable(
           @RequestBody DtoConsumableSave dto,
                       UriComponentsBuilder uriBuilder) {

        DtoConsumableResponse response = consumableService.createConsumable(dto);
        URI uri = uriBuilder.path("/consumable/{id}")
                .buildAndExpand(response.consumableId())
                .toUri();
        return ResponseEntity.created(uri).body(
                MessageResponse.<DtoConsumableResponse>builder()
                        .message("Consumible creado exitosamente")
                        .data(response)
                        .build()
        );
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateConsumable(
            @PathVariable Long id,
         @RequestBody DtoConsumableUpdate dto) {

        DtoConsumableResponse response = consumableService.updateConsumable(id, dto);
        if (response == null) {
            return ResponseEntity.status(404).body(
                    MessageResponse.<DtoConsumableResponse>builder()
                            .message("Consumible no encontrado para actualizar con ID: " + id)
                            .data(null)
                            .build()
            );
        }
        return ResponseEntity.accepted().body(
                MessageResponse.<DtoConsumableResponse>builder()
                        .message("Consumible actualizado exitosamente")
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deactivateConsumable(@PathVariable Long id) {
      consumableService.deactivateConsumable(id);
      return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllConsumables(
            @PageableDefault(size = 100) Pageable pageable) {
        Page<DtoConsumableList> page = consumableService.getAllConsumable(pageable);
        return ResponseEntity.ok(
                MessageResponse.<Page<DtoConsumableList>>builder()
                        .message("Consumibles recuperados exitosamente")
                        .data(page)
                        .build()
        );

    }
}
