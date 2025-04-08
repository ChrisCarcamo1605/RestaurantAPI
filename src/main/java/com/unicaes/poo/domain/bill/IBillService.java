package com.unicaes.poo.domain.bill;

import com.unicaes.poo.domain.bill.dto.DtoBillList;
import com.unicaes.poo.domain.bill.dto.DtoBillResponse;
import com.unicaes.poo.domain.bill.dto.DtoBillSave;

import java.util.List;

public interface IBillService extends IBill{

    @Override
    List<DtoBillList> getAll();
    @Override // Obtener todas las facturas
    DtoBillResponse save(DtoBillSave bill);
    @Override // Guardar o actualizar factura
    void delete(Long id);
}
