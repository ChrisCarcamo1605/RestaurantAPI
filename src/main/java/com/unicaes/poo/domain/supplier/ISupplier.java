package com.unicaes.poo.domain.supplier;

import com.unicaes.poo.domain.products.dto.DtoProductResponse;
import com.unicaes.poo.domain.supplier.dto.DtoSupplierList;
import com.unicaes.poo.domain.supplier.dto.DtoSuppliersResponse;
import com.unicaes.poo.domain.supplier.dto.DtoUpdateSupplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISupplier {
    DtoSuppliersResponse createSupplier(DtoSupplierList dto);
    DtoSuppliersResponse getSupplierById(Long id);
    void deleteSupplier(long id);
    DtoSuppliersResponse activeSupplier(Long id);
    Page<DtoSupplierList> getSuppliersPage(Pageable pageable);
    DtoSuppliersResponse deleteSupplier(Long id, DtoUpdateSupplier dto);
    DtoSuppliersResponse toggleSupplierStatus(Long id);
}