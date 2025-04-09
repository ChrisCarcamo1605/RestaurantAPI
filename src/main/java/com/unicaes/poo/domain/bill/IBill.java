package com.unicaes.poo.domain.bill;

import com.unicaes.poo.domain.bill.dto.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IBill {

    List<DtoBillList> getAll();
    DtoBillResponse updateBill(DtoBillUpdate dto);
    DtoBillResponse save(DtoBillSave bill);
    void delete(Long id);

}
