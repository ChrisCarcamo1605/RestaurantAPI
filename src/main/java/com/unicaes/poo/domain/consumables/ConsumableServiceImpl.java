package com.unicaes.poo.domain.consumables;

import com.unicaes.poo.domain.consumables.dto.*;
import com.unicaes.poo.repository.ConsumableRepository;
import com.unicaes.poo.interfaces.consumable.Consumable;
import com.unicaes.poo.domain.supplier.Supplier;
import com.unicaes.poo.repository.SupplierRepository;
import com.unicaes.poo.infra.exceptions.QueryException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConsumableServiceImpl implements Consumable {

    private final ConsumableRepository consumableRepository;
    private final SupplierRepository supplierRepository;

    @Override
    @Transactional
    public DtoConsumableResponse createConsumable(DtoConsumableSave dto) {
        Supplier supplier = supplierRepository.findById(dto.supplierId())
                .orElseThrow(() -> new QueryException("Proveedor no encontrado con ID: " + dto.supplierId()));

        try {
            com.unicaes.poo.domain.consumables.Consumable consumable = new com.unicaes.poo.domain.consumables.Consumable();
            consumable.setName(dto.name());
            consumable.setPrice(BigDecimal.valueOf(dto.price()));
            consumable.setStock(dto.stock());
            consumable.setSupplier(supplier);
            consumable.setConsumableTypes(dto.consumableTypes());
            consumable.setMeasurementUnit(dto.measurementUnit());
            consumable.setActive(true);

            com.unicaes.poo.domain.consumables.Consumable savedConsumable = consumableRepository.save(consumable);
            return convertToResponseDto(savedConsumable);
        } catch (Exception e) {
            throw new QueryException("Error al crear el consumible: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deactivateConsumable(Long id) {
        com.unicaes.poo.domain.consumables.Consumable consumable = consumableRepository.findById(id)
                .orElseThrow(() -> new QueryException("Consumible no encontrado con ID: " + id));

        try {
            consumable.setActive(false);
            consumableRepository.save(consumable);
        } catch (Exception e) {
            throw new QueryException("Error al desactivar el consumible: " + e.getMessage());
        }
    }

    @Override
    @Transactional
    public DtoConsumableResponse updateConsumable(Long id, DtoConsumableUpdate dto) {
        com.unicaes.poo.domain.consumables.Consumable consumable = consumableRepository.findById(id)
                .orElseThrow(() -> new QueryException("Consumible no encontrado con ID: " + id));

        try {
            if (dto.name() != null) consumable.setName(dto.name());
            if (dto.price() != null) consumable.setPrice(BigDecimal.valueOf(dto.price()));
            if (dto.stock() != null) consumable.setStock(dto.stock());
            if (dto.consumableTypes() != null) consumable.setConsumableTypes(dto.consumableTypes());
            if (dto.measurementUnit() != null) consumable.setMeasurementUnit(dto.measurementUnit());
            if (dto.supplierId() != null) {
                Supplier supplier = supplierRepository.findById(dto.supplierId())
                        .orElseThrow(() -> new QueryException("Proveedor no encontrado con ID: " + dto.supplierId()));
                consumable.setSupplier(supplier);
            }

            com.unicaes.poo.domain.consumables.Consumable updatedConsumable = consumableRepository.save(consumable);
            return convertToResponseDto(updatedConsumable);
        } catch (Exception e) {
            throw new QueryException("Error al actualizar el consumible: " + e.getMessage());
        }
    }

    @Override
    public Page<DtoConsumableList> getAllConsumable(Pageable pageable) {
        try {
            return consumableRepository.findAll(pageable)
                    .map(this::convertToListDto);
        } catch (Exception e) {
            throw new QueryException("Error al obtener todos los consumibles: " + e.getMessage());
        }
    }

    private DtoConsumableResponse convertToResponseDto(com.unicaes.poo.domain.consumables.Consumable consumable) {
        return new DtoConsumableResponse(
                consumable.getId(),
                consumable.getName(),
                consumable.getPrice().doubleValue(),
                consumable.getStock(),
                consumable.getSupplier().getSupplierId(),
                consumable.getConsumableTypes(),
                consumable.getMeasurementUnit(),
                consumable.isActive()
        );
    }

    private DtoConsumableList convertToListDto(com.unicaes.poo.domain.consumables.Consumable consumable) {
        return new DtoConsumableList(
                consumable.getId(),
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
