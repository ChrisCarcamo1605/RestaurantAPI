package com.unicaes.poo.controller;

import com.unicaes.poo.domain.ingredient.dto.*;
import com.unicaes.poo.interfaces.ingredient.Ingredient;
import com.unicaes.poo.payload.MessageResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {


    @Autowired
    private Ingredient ingredientService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createIngredient(
            /*@Valid*/ @RequestBody DtoIngredientSave dto,
                       UriComponentsBuilder ucBuilder) {
        DtoIngredientResponse response = ingredientService.saveIngredient(dto);
        URI uri = ucBuilder.path("/ingredients/{id}")
                .buildAndExpand(response.ingredientId())
                .toUri();
        return ResponseEntity.created(uri).body(
                MessageResponse.<DtoIngredientResponse>builder()
                        .message("Ingrediente creado exitosamente")
                        .data(response)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<?> getAllIngredients(
            @PageableDefault(size = 10) Pageable pageable) {
        Page<DtoIngredientResponse> ingredients = ingredientService.getAllIngredients(pageable);
        return ResponseEntity.ok(
                MessageResponse.<Page<DtoIngredientResponse>>builder()
                        .message("Ingredientes recuperados exitosamente")
                        .data(ingredients)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIngredientById(@PathVariable Long id) {
        DtoIngredientResponse ingredient = ingredientService.getIngredient(id);
        if (ingredient == null) {
            return ResponseEntity.status(404).body(
                    MessageResponse.<DtoIngredientResponse>builder()
                            .message("Ingrediente no encontrado con ID: " + id)
                            .data(null)
                            .build()
            );
        }
        return ResponseEntity.ok(
                MessageResponse.<DtoIngredientResponse>builder()
                        .message("Ingrediente recuperado exitosamente")
                        .data(ingredient)
                        .build()
        );
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateIngredient(
            @PathVariable Long id,
            /*@Valid*/ @RequestBody DtoIngredientUpdate dto) {
        DtoIngredientResponse response = ingredientService.updateIngredient(id, dto);
        if (response == null) {
            return ResponseEntity.status(404).body(
                    MessageResponse.<DtoIngredientResponse>builder()
                            .message("Ingrediente no encontrado para actualizar con ID: " + id)
                            .data(null)
                            .build()
            );
        }
        return ResponseEntity.accepted().body(
                MessageResponse.<DtoIngredientResponse>builder()
                        .message("Ingrediente actualizado exitosamente")
                        .data(response)
                        .build()
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
}
