package com.unicaes.poo.controller;

import com.unicaes.poo.domain.ingridient.dto.*;
import com.unicaes.poo.domain.ingridient.IIngridient;
import com.unicaes.poo.infra.exceptions.NotFoundException;
import com.unicaes.poo.infra.exceptions.ValidationException;
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
    public ResponseEntity<?> createIngredient(@RequestBody DtoIngredientSave dto) {
        try {
            DtoIngredientResponse response = ingredientService.saveIngredient(dto);
            return ResponseEntity.ok(response);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al crear el ingrediente");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllIngredients(Pageable pageable) {
        try {
            Page<DtoIngredientResponse> ingredients = ingredientService.getAllIngredients(pageable);
            return ResponseEntity.ok(ingredients);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al obtener los ingredientes");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateIngredient(
            @PathVariable Long id,
            @RequestBody DtoIngredientUpdate dto) {
        try {
            DtoIngredientResponse response = ingredientService.updateIngredient(id, dto);
            return ResponseEntity.ok(response);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al actualizar el ingrediente");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredient(@PathVariable Long id) {
        try {
            ingredientService.deleteIngredient(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar el ingrediente");
        }
    }
}