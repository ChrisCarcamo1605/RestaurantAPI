package com.unicaes.poo.domain.products;

import com.unicaes.poo.domain.products.dto.DtoSaveProduct;

import java.util.List;

public interface IProduct {

    public Product save(DtoSaveProduct dto);
}
