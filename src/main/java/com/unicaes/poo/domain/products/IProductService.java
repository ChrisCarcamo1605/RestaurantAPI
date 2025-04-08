package com.unicaes.poo.domain.products;

import com.unicaes.poo.domain.products.dto.DtoProductResponse;
import com.unicaes.poo.domain.products.dto.DtoSaveProduct;
import com.unicaes.poo.domain.products.dto.DtoTypeResponse;
import com.unicaes.poo.domain.products.dto.DtoUpdateProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService extends IProduct, IType {
    @Override
    DtoProductResponse save(DtoSaveProduct dto);

    @Override
    List<DtoProductResponse> getProductsList(Pageable pageable);

    @Override
    void deleteProduct(long id);

    @Override
    DtoProductResponse updateProduct(DtoUpdateProduct dto);

    @Override
    DtoTypeResponse save(String productName);

    @Override
    DtoTypeResponse update(long id, String productName);

    @Override
    void delete(long id);

    @Override
    List<DtoTypeResponse> findAll();
}
