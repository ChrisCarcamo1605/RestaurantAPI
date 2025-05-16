package com.unicaes.poo.domain.products.interfaces;

import com.unicaes.poo.domain.products.Product;
import com.unicaes.poo.domain.products.dto.DtoProductResponse;
import com.unicaes.poo.domain.products.dto.DtoSaveProduct;
import com.unicaes.poo.domain.products.dto.DtoTypeResponse;
import com.unicaes.poo.domain.products.dto.DtoUpdateProduct;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService extends IProduct, IType {
    @Override
    DtoProductResponse save(DtoSaveProduct dto);

    @Override
    List<DtoProductResponse> getProductsList(Pageable pageable);


    List<Product> findPriceLess(double price);
}
