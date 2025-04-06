package com.unicaes.poo.domain.consumables;

import com.unicaes.poo.domain.consumables.dto.*;
import com.unicaes.poo.domain.consumableTypes.ConsumableTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IConsumable {
    DtoConsumableResponse createConsumable(DtoConsumableSave dto);
    DtoConsumableResponse getConsumableById(Long id);
    void deactivateConsumable(Long id);
    DtoConsumableResponse updateConsumable(Long id, DtoConsumableUpdate dto);
    Page<DtoConsumableList> getAllConsumable(Pageable pageable);
    Page<DtoConsumableList> getConsumablesByType(ConsumableTypes type, Pageable pageable);
    DtoConsumableResponse toggleConsumableStatus(Long id);
    List<DtoConsumableResponse> getLowStockConsumables(Double threshold);
    Page<DtoConsumableList> searchConsumablesByName(String name, Pageable pageable);
}