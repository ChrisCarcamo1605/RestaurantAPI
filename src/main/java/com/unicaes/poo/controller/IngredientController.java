package com.unicaes.poo.controller;

import com.unicaes.poo.domain.ingredient.dto.*;
import com.unicaes.poo.domain.ingredient.interfaces.IIngredient;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {


    @Autowired
    private IIngredient ingredientService;

    @PostMapping
    @Transactional
    public ResponseEntity<DtoIngredientResponse> createIngredient(@RequestBody @Valid DtoIngredientSave dto, UriComponentsBuilder ucBuilder) {


        DtoIngredientResponse response = ingredientService.saveIngredient(dto);

        URI uri = ucBuilder.path("/ingredients/{id}").buildAndExpand(response.ingredientId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<DtoIngredientResponse>> getAllIngredients(Pageable pageable) {
        Page<DtoIngredientResponse> ingredients = ingredientService.getAllIngredients(pageable);
        return ResponseEntity.ok(ingredients);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<DtoIngredientResponse> updateIngredient(
            @PathVariable Long id,
            @Valid
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
