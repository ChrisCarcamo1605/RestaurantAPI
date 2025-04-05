package com.unicaes.poo.domain.goods.dto;

import com.unicaes.poo.domain.goodsTypes.GoodsTypes;
import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;

public record DtoGoodList(
        String name,
        Double price,
        Double stock,
        String supplierName,
        GoodsTypes goodType,
        MeasurementUnit measurementUnit,
        Boolean active
) {}