package com.unicaes.poo.interfaces.bill;

import com.unicaes.poo.domain.bill.dto.DtoBillList;
import com.unicaes.poo.domain.bill.dto.DtoBillResponse;
import com.unicaes.poo.domain.bill.dto.DtoBillSave;
import com.unicaes.poo.domain.bill.dto.DtoBillUpdate;

import java.util.List;

public interface BillService extends BillDetails {
    List<DtoBillList> getAll();
    DtoBillResponse updateBill(DtoBillUpdate dto);
    DtoBillResponse save(DtoBillSave bill);
    void delete(Long id);

}
