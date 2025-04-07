package com.unicaes.poo.domain.venta;

import com.unicaes.poo.domain.venta.dto.DtoVentaResponse;
import com.unicaes.poo.domain.venta.dto.DtoSaveVenta;
import com.unicaes.poo.domain.venta.dto.DtoUpdateVenta;

import java.util.List;

public interface IVenta {
    DtoVentaResponse saveVenta(DtoSaveVenta dto);
    List<DtoVentaResponse> getAllVentas();
    DtoVentaResponse getVentaById(Long id);
    DtoVentaResponse updateVenta(Long id, DtoUpdateVenta dto);
    void deleteVenta(Long id);
}
