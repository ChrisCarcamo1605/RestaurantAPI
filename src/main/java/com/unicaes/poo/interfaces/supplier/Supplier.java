package com.unicaes.poo.interfaces.supplier;

import com.unicaes.poo.domain.supplier.dto.DtoSupplierList;
import com.unicaes.poo.domain.supplier.dto.DtoSupplierSave;
import com.unicaes.poo.domain.supplier.dto.DtoSuppliersResponse;
import com.unicaes.poo.domain.supplier.dto.DtoUpdateSupplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Supplier {
    DtoSuppliersResponse createSupplier(DtoSupplierSave dto);
    DtoSuppliersResponse getSupplierById(Long id);
    void deactivateSupplier(long id);
    Page<DtoSupplierList> getSuppliersPage(Pageable pageable);
    DtoSuppliersResponse updateSupplier(Long id, DtoUpdateSupplier dto);
}