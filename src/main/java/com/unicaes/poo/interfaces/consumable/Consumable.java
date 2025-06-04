package com.unicaes.poo.interfaces.consumable;

import com.unicaes.poo.domain.consumables.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Consumable {
    DtoConsumableResponse createConsumable(DtoConsumableSave dto);
    void deactivateConsumable(Long id);
    DtoConsumableResponse updateConsumable(Long id, DtoConsumableUpdate dto);
    Page<DtoConsumableList> getAllConsumable(Pageable pageable);
}