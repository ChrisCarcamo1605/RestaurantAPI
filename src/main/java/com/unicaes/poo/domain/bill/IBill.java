package com.unicaes.poo.domain.bill;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IBill {

    List<Bill> getAll();                         // Obtener todas las facturas
    Bill save(Bill bill);                        // Guardar o actualizar factura
    void delete(Long id);                        // Eliminar factura
                                                // Consulta personalizada
}
