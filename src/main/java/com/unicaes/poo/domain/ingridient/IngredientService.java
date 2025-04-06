package com.unicaes.poo.domain.ingridient;

import com.unicaes.poo.domain.consumables.Consumable;
import com.unicaes.poo.domain.consumables.ConsumableRepository;
import com.unicaes.poo.domain.ingridient.dto.DtoIngredientResponse;
import com.unicaes.poo.domain.products.Product;
import com.unicaes.poo.domain.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.unicaes.poo.domain.ingridient.dto.DtoIngredientList;
import com.unicaes.poo.domain.ingridient.dto.DtoIngredientSave;

@Service
@RequiredArgsConstructor
public class IngredientService implements IIngridient{

    private final IngredientRepository ingredientRepository;
    private final ConsumableRepository consumableRepository;
    private final ProductRepository productRepository;

    @Transactional
    public DtoIngredientResponse saveIngredient(DtoIngredientSave dto) {
        Consumable consumable = consumableRepository.findById(dto.consumableId())
                .orElseThrow(() -> new RuntimeException("Consumable not found"));

        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Ingredient ingredient = new Ingredient();
        ingredient.setConsumable(consumable);
        ingredient.setQuantity(dto.quantity());
        ingredient.setProduct(product);

        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        return toResponseDto(savedIngredient);
    }

    public Page<DtoIngredientResponse> getAllIngredients(Pageable pageable) {
        return ingredientRepository.findAll(pageable)
                .map(this::toResponseDto);
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