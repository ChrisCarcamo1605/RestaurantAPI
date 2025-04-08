package com.unicaes.poo.domain.bill;

import com.unicaes.poo.domain.bill.dto.DtoBillList;
import com.unicaes.poo.domain.bill.dto.DtoBillResponse;
import com.unicaes.poo.domain.bill.dto.DtoBillSave;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IBill {

    List<DtoBillList> getAll();                         // Obtener todas las facturas
    DtoBillResponse save(DtoBillSave bill);                        // Guardar o actualizar factura
    void delete(Long id);                        // Eliminar factura
                                                // Consulta personalizada
}
