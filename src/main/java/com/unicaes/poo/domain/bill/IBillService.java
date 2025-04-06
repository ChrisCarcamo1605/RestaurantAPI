package com.unicaes.poo.domain.bill;

import java.util.List;

public interface IBillService extends IBill{

    @Override
    List<Bill> getAll();
    @Override // Obtener todas las facturas
    Bill save(Bill bill);
    @Override // Guardar o actualizar factura
    void delete(Long id);
}
