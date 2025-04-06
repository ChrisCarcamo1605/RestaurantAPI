package com.unicaes.poo.controller;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.unicaes.poo.domain.ingridient.IIngridient;
import com.unicaes.poo.domain.ingridient.dto.DtoIngredientResponse;
import com.unicaes.poo.domain.ingridient.dto.DtoIngredientSave;
import com.unicaes.poo.domain.ingridient.IngredientService;


@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {


    private final IIngridient ingredientService; // Usar el nombre exacto

    @PostMapping
    public ResponseEntity<DtoIngredientResponse> createIngredient(
            @RequestBody DtoIngredientSave dto) {
        DtoIngredientResponse response = ingredientService.saveIngredient(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DtoIngredientResponse>> getAllIngredients(
            Pageable pageable) {
        Page<DtoIngredientResponse> ingredients = ingredientService.getAllIngredients(pageable);
        return ResponseEntity.ok(ingredients);
    }
}