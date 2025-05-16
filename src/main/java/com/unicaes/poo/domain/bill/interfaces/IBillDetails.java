package com.unicaes.poo.domain.bill.interfaces;

import com.unicaes.poo.domain.bill.dto.*;

import java.util.List;

public interface IBillDetails {
    List<DtoBillDetailsList> getAllActive();
    DtoBillDetailsResponse getById(Long id);
    DtoBillDetailsResponse save(DtoBillDetailsSave dto);
    DtoBillDetailsResponse update(DtoBillDetailsUpdate dto);
    void deactivate(Long id);
}