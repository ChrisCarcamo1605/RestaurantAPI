package com.unicaes.poo.domain.bill.interfaces;

import com.unicaes.poo.domain.bill.dto.*;

import java.util.List;

public interface IBill {

    List<DtoBillList> getAll();
    DtoBillResponse updateBill(DtoBillUpdate dto);
    DtoBillResponse save(DtoBillSave bill);
    void delete(Long id);

}
