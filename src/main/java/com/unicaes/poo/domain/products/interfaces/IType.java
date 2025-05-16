package com.unicaes.poo.domain.products.interfaces;

import com.unicaes.poo.domain.products.dto.DtoTypeResponse;

import java.util.List;

public interface IType {

    DtoTypeResponse save(String productName);
    DtoTypeResponse update(long id, String productName);
    void delete(long id);
    List<DtoTypeResponse> findAll();

}
