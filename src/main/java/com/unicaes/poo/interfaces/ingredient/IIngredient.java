package com.unicaes.poo.interfaces.ingredient;
import com.unicaes.poo.domain.ingredient.dto.DtoIngredientUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.unicaes.poo.domain.ingredient.dto.DtoIngredientResponse;
import com.unicaes.poo.domain.ingredient.dto.DtoIngredientSave;

public interface IIngredient {
    DtoIngredientResponse saveIngredient(DtoIngredientSave dto);
    Page<DtoIngredientResponse> getAllIngredients(Pageable pageable);
    DtoIngredientResponse updateIngredient(Long id, DtoIngredientUpdate dto);
    void deleteIngredient(Long id);
}
