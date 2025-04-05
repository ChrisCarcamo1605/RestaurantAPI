package com.unicaes.poo.domain.goods;

import com.unicaes.poo.domain.goods.dto.*;
import com.unicaes.poo.domain.goodsTypes.GoodsTypes;
import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;
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
public class GoodService implements IGood {

    private final GoodRepository goodRepository;
    private final SupplierRepository supplierRepository;

    @Override
    @Transactional
    public DtoGoodResponse createGood(DtoGoodSave dto) {
        Supplier supplier = supplierRepository.findById(dto.supplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + dto.supplierId()));

        Good good = new Good();
        good.setName(dto.name());
        good.setPrice(BigDecimal.valueOf(dto.price()));
        good.setStock(dto.stock());
        good.setSupplier(supplier);
        good.setGoodType(dto.goodType());
        good.setMeasurementUnit(dto.measurementUnit());
        good.setActive(true);

        Good savedGood = goodRepository.save(good);
        return convertToResponseDto(savedGood);
    }

    @Override
    public DtoGoodResponse getGoodById(Long id) {
        Good good = goodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Good not found with id: " + id));
        return convertToResponseDto(good);
    }

    @Override
    @Transactional
    public void deactivateGood(Long id) {
        Good good = goodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Good not found"));
        good.setActive(false);
        goodRepository.save(good);
    }

    @Override
    @Transactional
    public DtoGoodResponse updateGood(Long id, DtoGoodUpdate dto) {
        Good good = goodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Good not found"));

        if (dto.name() != null) good.setName(dto.name());
        if (dto.price() != null) good.setPrice(BigDecimal.valueOf(dto.price()));
        if (dto.stock() != null) good.setStock(dto.stock());
        if (dto.goodType() != null) good.setGoodType(dto.goodType());
        if (dto.measurementUnit() != null) good.setMeasurementUnit(dto.measurementUnit());
        if (dto.supplierId() != null) {
            Supplier supplier = supplierRepository.findById(dto.supplierId())
                    .orElseThrow(() -> new RuntimeException("Supplier not found"));
            good.setSupplier(supplier);
        }

        Good updatedGood = goodRepository.save(good);
        return convertToResponseDto(updatedGood);
    }

    @Override
    public Page<DtoGoodList> getAllGoods(Pageable pageable) {
        return goodRepository.findAll(pageable)
                .map(this::convertToListDto);
    }

    @Override
    public Page<DtoGoodList> getGoodsByType(GoodsTypes type, Pageable pageable) {
        return goodRepository.findByGoodType(type, pageable)
                .map(this::convertToListDto);
    }

    @Override
    @Transactional
    public DtoGoodResponse toggleGoodStatus(Long id) {
        Good good = goodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Good not found"));
        good.setActive(!good.isActive());
        Good toggledGood = goodRepository.save(good);
        return convertToResponseDto(toggledGood);
    }

    @Override
    public List<DtoGoodResponse> getLowStockGoods(Double threshold) {
        return goodRepository.findByStockLessThanAndActiveTrue(threshold).stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    @Override
    public Page<DtoGoodList> searchGoodsByName(String name, Pageable pageable) {
        return goodRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(this::convertToListDto);
    }

    private DtoGoodResponse convertToResponseDto(Good good) {
        return new DtoGoodResponse(
                good.getId(),
                good.getName(),
                good.getPrice().doubleValue(),
                good.getStock(),
                good.getSupplier().getSupplierId(), // Cambiado a getSupplierId()
                good.getGoodType(),
                good.getMeasurementUnit(),
                good.isActive()
        );
    }

    private DtoGoodList convertToListDto(Good good) {
        return new DtoGoodList(
                good.getName(),
                good.getPrice().doubleValue(),
                good.getStock(),
                good.getSupplier().getName(),
                good.getGoodType(),
                good.getMeasurementUnit(),
                good.isActive()
        );
    }
}