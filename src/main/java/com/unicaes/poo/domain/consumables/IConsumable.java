package com.unicaes.poo.domain.consumables;

import com.unicaes.poo.domain.consumables.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IConsumable {
    DtoConsumableResponse createConsumable(DtoConsumableSave dto);
    void deactivateConsumable(Long id);
    DtoConsumableResponse updateConsumable(Long id, DtoConsumableUpdate dto);
    Page<DtoConsumableList> getAllConsumable(Pageable pageable);
}