package com.unicaes.poo.repository;

import com.unicaes.poo.domain.ingredient.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    // Buscar ingredientes por producto (paginado)
    Page<Ingredient> findByProductId(Long productId, Pageable pageable);

    // Buscar ingredientes por consumible
    List<Ingredient> findByConsumableId(Long consumableId);

    // Buscar ingredientes por cantidad (mayor o igual que)
    List<Ingredient> findByQuantityGreaterThanEqual(Double quantity);

    // Buscar ingredientes que contengan un consumible con name específico
    Page<Ingredient> findByConsumableNameContainingIgnoreCase(String name, Pageable pageable);

    // Buscar ingredientes por producto y cantidad mínima
    List<Ingredient> findByProductIdAndQuantityGreaterThanEqual(Long productId, Double minQuantity);


}