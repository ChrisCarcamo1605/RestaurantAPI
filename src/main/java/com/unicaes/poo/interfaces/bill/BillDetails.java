package com.unicaes.poo.interfaces.bill;

import com.unicaes.poo.domain.bill.dto.*;

import java.util.List;

public interface BillDetails {
    List<DtoBillDetailsList> getAllActive();
    DtoBillDetailsResponse getById(Long id);
    DtoBillDetailsResponse save(DtoBillDetailsSave dto);
    DtoBillDetailsResponse update(DtoBillDetailsUpdate dto);
    void deactivate(Long id);
}