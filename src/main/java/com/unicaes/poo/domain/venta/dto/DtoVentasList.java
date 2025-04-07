package com.unicaes.poo.domain.venta.dto;

import com.unicaes.poo.domain.venta.Venta;

public record DtoVentasList(Long idVenta, Long bill_id) {

    public DtoVentasList(Venta venta) {
        this(venta.getIdVenta(), venta.getIdTicket());
    }
}
