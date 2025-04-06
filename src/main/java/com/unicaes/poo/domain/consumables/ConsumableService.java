package com.unicaes.poo.domain.consumables;

import com.unicaes.poo.domain.consumables.dto.*;
import com.unicaes.poo.domain.consumableTypes.ConsumableTypes;
import com.unicaes.poo.domain.supplier.Supplier;
import com.unicaes.poo.domain.supplier.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConsumableService implements IConsumable {

    private final ConsumableRepository consumableRepository;
    private final SupplierRepository supplierRepository;

    @Override
    @Transactional
    public DtoConsumableResponse createConsumable(DtoConsumableSave dto) {
        Supplier supplier = supplierRepository.findById(dto.supplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + dto.supplierId()));

        Consumable consumable = new Consumable();
        consumable.setName(dto.name());
        consumable.setPrice(BigDecimal.valueOf(dto.price()));
        consumable.setStock(dto.stock());
        consumable.setSupplier(supplier);
        consumable.setConsumableTypes(dto.consumableTypes());
        consumable.setMeasurementUnit(dto.measurementUnit());
        consumable.setActive(true);

        Consumable savedConsumable = consumableRepository.save(consumable);
        return convertToResponseDto(savedConsumable);
    }

    @Override
    public DtoConsumableResponse getConsumableById(Long id) {
        Consumable consumable = consumableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("consumable not found with id: " + id));
        return convertToResponseDto(consumable);
    }

    @Override
    @Transactional
    public void deactivateConsumable(Long id) {
        Consumable consumable = consumableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("consumable not found"));
        consumable.setActive(false);
        consumableRepository.save(consumable);
    }

    @Override
    @Transactional
    public DtoConsumableResponse updateConsumable(Long id, DtoConsumableUpdate dto) {
        Consumable consumable = consumableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumable not found"));

        if (dto.name() != null) consumable.setName(dto.name());
        if (dto.price() != null) consumable.setPrice(BigDecimal.valueOf(dto.price()));
        if (dto.stock() != null) consumable.setStock(dto.stock());
        if (dto.consumableTypes() != null) consumable.setConsumableTypes(dto.consumableTypes());
        if (dto.measurementUnit() != null) consumable.setMeasurementUnit(dto.measurementUnit());
        if (dto.supplierId() != null) {
            Supplier supplier = supplierRepository.findById(dto.supplierId())
                    .orElseThrow(() -> new RuntimeException("Supplier not found"));
            consumable.setSupplier(supplier);
        }

        Consumable updatedConsumable = consumableRepository.save(consumable);
        return convertToResponseDto(updatedConsumable);
    }

    @Override
    public Page<DtoConsumableList> getAllConsumable(Pageable pageable) {
        return consumableRepository.findAll(pageable)
                .map(this::convertToListDto);
    }

    @Override
    public Page<DtoConsumableList> getConsumablesByType(ConsumableTypes type, Pageable pageable) {
        return consumableRepository.findByConsumableTypes(type, pageable)
                .map(this::convertToListDto);
    }

    @Override
    @Transactional
    public DtoConsumableResponse toggleConsumableStatus(Long id) {
        Consumable consumable = consumableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("consumable not found"));
        consumable.setActive(!consumable.isActive());
        Consumable toggledConsumable = consumableRepository.save(consumable);
        return convertToResponseDto(toggledConsumable);
    }

    @Override
    public List<DtoConsumableResponse> getLowStockConsumables(Double threshold) {
        return consumableRepository.findByStockLessThanAndActiveTrue(threshold).stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    @Override
    public Page<DtoConsumableList> searchConsumablesByName(String name, Pageable pageable) {
        return consumableRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(this::convertToListDto);
    }

    private DtoConsumableResponse convertToResponseDto(Consumable consumable) {
        return new DtoConsumableResponse(
                consumable.getId(),
                consumable.getName(),
                consumable.getPrice().doubleValue(),
                consumable.getStock(),
                consumable.getSupplier().getSupplierId(), // Cambiado a getSupplierId()
                consumable.getConsumableTypes(),
                consumable.getMeasurementUnit(),
                consumable.isActive()
        );
    }

    private DtoConsumableList convertToListDto(Consumable consumable) {
        return new DtoConsumableList(
                consumable.getName(),
                consumable.getPrice().doubleValue(),
                consumable.getStock(),
                consumable.getSupplier().getName(),
                consumable.getConsumableTypes(),
                consumable.getMeasurementUnit(),
                consumable.isActive()
        );
    }
}