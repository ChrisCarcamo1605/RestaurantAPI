package com.unicaes.poo.domain.ingridient;

import com.unicaes.poo.domain.consumables.Consumable;
import com.unicaes.poo.domain.consumables.ConsumableRepository;
import com.unicaes.poo.domain.ingridient.dto.*;
import com.unicaes.poo.domain.products.Product;
import com.unicaes.poo.domain.products.ProductRepository;
import com.unicaes.poo.infra.exceptions.NotFoundException;
import com.unicaes.poo.infra.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IngredientService implements IIngridient {

    private final IngredientRepository ingredientRepository;
    private final ConsumableRepository consumableRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public DtoIngredientResponse saveIngredient(DtoIngredientSave dto) {
        try {
            Consumable consumable = consumableRepository.findById(dto.consumableId())
                    .orElseThrow(() -> new NotFoundException("Consumible no encontrado con ID: " + dto.consumableId()));

            Product product = productRepository.findById(dto.productId())
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado con ID: " + dto.productId()));

            if (dto.quantity() <= 0) {
                throw new ValidationException("La cantidad debe ser mayor que cero");
            }

            Ingredient ingredient = new Ingredient();
            ingredient.setConsumable(consumable);
            ingredient.setQuantity(dto.quantity());
            ingredient.setProduct(product);

            Ingredient savedIngredient = ingredientRepository.save(ingredient);
            return toResponseDto(savedIngredient);
        } catch (NotFoundException | ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el ingrediente: " + e.getMessage(), e);
        }
    }

    @Override
    public Page<DtoIngredientResponse> getAllIngredients(Pageable pageable) {
        try {
            return ingredientRepository.findAll(pageable)
                    .map(this::toResponseDto);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los ingredientes: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public DtoIngredientResponse updateIngredient(Long id, DtoIngredientUpdate dto) {
        try {
            Ingredient ingredient = ingredientRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Ingrediente no encontrado con ID: " + id));

            // Actualización parcial - solo campos no nulos
            if (dto.consumableId() != null) {
                Consumable consumable = consumableRepository.findById(dto.consumableId())
                        .orElseThrow(() -> new NotFoundException("Consumible no encontrado con ID: " + dto.consumableId()));
                ingredient.setConsumable(consumable);
            }

            if (dto.quantity() != null) {
                if (dto.quantity() <= 0) {
                    throw new ValidationException("La cantidad debe ser mayor que cero");
                }
                ingredient.setQuantity(dto.quantity());
            }

            if (dto.productId() != null) {
                Product product = productRepository.findById(dto.productId())
                        .orElseThrow(() -> new NotFoundException("Producto no encontrado con ID: " + dto.productId()));
                ingredient.setProduct(product);
            }

            Ingredient updatedIngredient = ingredientRepository.save(ingredient);
            return toResponseDto(updatedIngredient);
        } catch (NotFoundException | ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el ingrediente: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void deleteIngredient(Long id) {
        try {
            if (!ingredientRepository.existsById(id)) {
                throw new NotFoundException("Ingrediente no encontrado con ID: " + id);
            }
            ingredientRepository.deleteById(id);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el ingrediente: " + e.getMessage(), e);
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

    private DtoIngredientList toListDto(Ingredient ingredient) {
        return new DtoIngredientList(
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