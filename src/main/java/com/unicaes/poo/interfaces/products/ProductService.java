package com.unicaes.poo.interfaces.products;

import com.unicaes.poo.domain.products.dto.DtoProductResponse;
import com.unicaes.poo.domain.products.dto.DtoSaveProduct;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService extends Product, ProductType {
    @Override
    DtoProductResponse save(DtoSaveProduct dto);

    @Override
    List<DtoProductResponse> getProductsList(Pageable pageable);


    List<com.unicaes.poo.domain.products.Product> findPriceLess(double price);
}
