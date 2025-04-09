package com.unicaes.poo.controller;

import com.unicaes.poo.domain.ingridient.dto.*;
import com.unicaes.poo.domain.ingridient.IIngridient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IIngridient ingredientService;

    @PostMapping
    public ResponseEntity<DtoIngredientResponse> createIngredient(@RequestBody DtoIngredientSave dto) {
        DtoIngredientResponse response = ingredientService.saveIngredient(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DtoIngredientResponse>> getAllIngredients(Pageable pageable) {
        Page<DtoIngredientResponse> ingredients = ingredientService.getAllIngredients(pageable);
        return ResponseEntity.ok(ingredients);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DtoIngredientResponse> updateIngredient(
            @PathVariable Long id,
            @RequestBody DtoIngredientUpdate dto) {
        DtoIngredientResponse response = ingredientService.updateIngredient(id, dto);
        return ResponseEntity.accepted().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
}
