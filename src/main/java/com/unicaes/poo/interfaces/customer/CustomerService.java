package com.unicaes.poo.interfaces.customer;

import com.unicaes.poo.domain.customer.dto.*;

import java.util.List;

public interface CustomerService {
    List<DtoCustomerList> findAll();
    DtoCustomerResponse findById(Long id);
    DtoCustomerResponse save(DtoCustomerSave dto);
    DtoCustomerResponse update(Long id, DtoCustomerUpdate dto);
    void delete(Long id);
}
