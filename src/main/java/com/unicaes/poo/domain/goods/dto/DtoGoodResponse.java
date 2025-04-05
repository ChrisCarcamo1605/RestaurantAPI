package com.unicaes.poo.domain.goods.dto;

import com.unicaes.poo.domain.goodsTypes.GoodsTypes;
import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;

public record DtoGoodResponse(
        Long goodId,
        String name,
        Double price,
        Double stock,
        Long supplierId,
        GoodsTypes goodType,
        MeasurementUnit measurementUnit,
        Boolean active
) {}