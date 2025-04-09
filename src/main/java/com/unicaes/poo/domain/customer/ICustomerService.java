package com.unicaes.poo.domain.customer;

import com.unicaes.poo.domain.customer.dto.*;

import java.util.List;

public interface ICustomerService {
    List<DtoCustomerList> findAll();
    DtoCustomerResponse findById(Long id);
    DtoCustomerResponse save(DtoCustomerSave dto);
    DtoCustomerResponse update(Long id, DtoCustomerUpdate dto);
    void delete(Long id);
}
