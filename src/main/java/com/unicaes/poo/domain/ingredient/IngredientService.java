package com.unicaes.poo.domain.ingredient;

import com.unicaes.poo.domain.consumables.Consumable;
import com.unicaes.poo.domain.consumables.interfaces.ConsumableRepository;
import com.unicaes.poo.domain.ingredient.dto.*;
import com.unicaes.poo.domain.ingredient.interfaces.IIngredient;
import com.unicaes.poo.domain.ingredient.interfaces.IngredientRepository;
import com.unicaes.poo.domain.products.Product;
import com.unicaes.poo.domain.products.interfaces.ProductRepository;
import com.unicaes.poo.infra.exceptions.QueryException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IngredientService implements IIngredient {

    private final IngredientRepository ingredientRepository;
    private final ConsumableRepository consumableRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public DtoIngredientResponse saveIngredient(DtoIngredientSave dto) {
        Consumable consumable = consumableRepository.findById(dto.consumableId())
                .orElseThrow(() -> new QueryException("Consumible no encontrado con ID: " + dto.consumableId()));

        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new QueryException("Producto no encontrado con ID: " + dto.productId()));

        if (dto.quantity() <= 0) {
            throw new QueryException("La cantidad debe ser mayor que cero");
        }

        try {
            Ingredient ingredient = new Ingredient();
            ingredient.setConsumable(consumable);
            ingredient.setQuantity(dto.quantity());
            ingredient.setProduct(product);

            Ingredient savedIngredient = ingredientRepository.save(ingredient);
            return toResponseDto(savedIngredient);
        } catch (Exception e) {
            throw new QueryException("Error al guardar el ingrediente: " + e.getMessage());
        }
    }

    @Override
    public Page<DtoIngredientResponse> getAllIngredients(Pageable pageable) {
        try {
            return ingredientRepository.findAll(pageable)
                    .map(this::toResponseDto);
        } catch (Exception e) {
            throw new QueryException("Error al obtener los ingredientes: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public DtoIngredientResponse updateIngredient(Long id, DtoIngredientUpdate dto) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new QueryException("Ingrediente no encontrado con ID: " + id));

        try {
            if (dto.consumableId() != null) {
                Consumable consumable = consumableRepository.findById(dto.consumableId())
                        .orElseThrow(() -> new QueryException("Consumible no encontrado con ID: " + dto.consumableId()));
                ingredient.setConsumable(consumable);
            }

            if (dto.quantity() != null) {
                if (dto.quantity() <= 0) {
                    throw new QueryException("La cantidad debe ser mayor que cero");
                }
                ingredient.setQuantity(dto.quantity());
            }

            if (dto.productId() != null) {
                Product product = productRepository.findById(dto.productId())
                        .orElseThrow(() -> new QueryException("Producto no encontrado con ID: " + dto.productId()));
                ingredient.setProduct(product);
            }

            Ingredient updatedIngredient = ingredientRepository.save(ingredient);
            return toResponseDto(updatedIngredient);
        } catch (Exception e) {
            throw new QueryException("Error al actualizar el ingrediente: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteIngredient(Long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new QueryException("Ingrediente no encontrado con ID: " + id);
        }

        try {
            ingredientRepository.deleteById(id);
        } catch (Exception e) {
            throw new QueryException("Error al eliminar el ingrediente: " + e.getMessage());
        }
    }

    private DtoIngredientResponse toResponseDto(Ingredient ingredient) {
        return new DtoIngredientResponse(
                ingredient.getId(),
                ingredient.getConsumable().getId(),
                ingredient.getConsumable().getName(),
                ingredient.getQuantity(),
                ingredient.getConsumable().getMeasurementUnit().toString(),
                ingredient.getProduct().getId(),
                ingredient.getProduct().getName()
        );
    }


}
