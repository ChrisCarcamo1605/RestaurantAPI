package com.unicaes.poo.domain.consumables.interfaces;

import com.unicaes.poo.domain.consumableTypes.ConsumableTypes;
import com.unicaes.poo.domain.consumables.Consumable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumableRepository extends JpaRepository<Consumable, Long> {
    Page<Consumable> findByConsumableTypes(ConsumableTypes consumableTypes, Pageable pageable);
    List<Consumable> findByStockLessThanAndActiveTrue(Double threshold);
    Page<Consumable> findByNameContainingIgnoreCase(String name, Pageable pageable);
}