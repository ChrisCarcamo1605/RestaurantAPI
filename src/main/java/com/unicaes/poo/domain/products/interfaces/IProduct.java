package com.unicaes.poo.domain.products.interfaces;

import com.unicaes.poo.domain.products.dto.DtoProductResponse;
import com.unicaes.poo.domain.products.dto.DtoSaveProduct;
import com.unicaes.poo.domain.products.dto.DtoUpdateProduct;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProduct {

    DtoProductResponse save(DtoSaveProduct dto);
    List<DtoProductResponse> getProductsList(Pageable pageable);
    void deleteProduct(long id);
    DtoProductResponse updateProduct(DtoUpdateProduct dto);

}
