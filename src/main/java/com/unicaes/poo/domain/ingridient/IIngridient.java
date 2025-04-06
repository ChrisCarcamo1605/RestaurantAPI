package com.unicaes.poo.domain.ingridient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.unicaes.poo.domain.ingridient.dto.DtoIngredientResponse;
import com.unicaes.poo.domain.ingridient.dto.DtoIngredientSave;

public interface IIngridient{

    /**
     * Guarda un nuevo ingrediente en el sistema
     * @param dto Datos del ingrediente a guardar
     * @return DTO con la respuesta del ingrediente guardado
     */
    DtoIngredientResponse saveIngredient(DtoIngredientSave dto);

    /**
     * Obtiene todos los ingredientes paginados
     * @param pageable Configuración de paginación
     * @return Página de DTOs con la lista de ingredientes
     */
    Page<DtoIngredientResponse> getAllIngredients(Pageable pageable);
}
