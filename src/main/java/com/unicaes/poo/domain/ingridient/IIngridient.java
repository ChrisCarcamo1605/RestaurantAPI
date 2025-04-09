package com.unicaes.poo.domain.ingridient;
import com.unicaes.poo.domain.ingridient.dto.DtoIngredientUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.unicaes.poo.domain.ingridient.dto.DtoIngredientResponse;
import com.unicaes.poo.domain.ingridient.dto.DtoIngredientSave;

public interface IIngridient {
    DtoIngredientResponse saveIngredient(DtoIngredientSave dto);
    Page<DtoIngredientResponse> getAllIngredients(Pageable pageable);
    DtoIngredientResponse updateIngredient(Long id, DtoIngredientUpdate dto);
    void deleteIngredient(Long id);
}
