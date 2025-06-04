package com.unicaes.poo.interfaces.ingredient;
import com.unicaes.poo.domain.ingredient.dto.DtoIngredientUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.unicaes.poo.domain.ingredient.dto.DtoIngredientResponse;
import com.unicaes.poo.domain.ingredient.dto.DtoIngredientSave;

public interface Ingredient {
    DtoIngredientResponse saveIngredient(DtoIngredientSave dto);
    Page<DtoIngredientResponse> getAllIngredients(Pageable pageable);
    DtoIngredientResponse updateIngredient(Long id, DtoIngredientUpdate dto);
    DtoIngredientResponse getIngredient(Long id);
    void deleteIngredient(Long id);
}
