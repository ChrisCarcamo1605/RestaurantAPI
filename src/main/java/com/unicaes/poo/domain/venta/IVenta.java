package com.unicaes.poo.domain.venta;

import com.unicaes.poo.domain.venta.dto.DtoSaveVenta;

import java.util.List;

public interface IVenta {

    public Venta save(DtoSaveVenta dto);
}
