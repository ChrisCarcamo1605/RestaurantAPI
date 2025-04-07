package com.unicaes.poo.domain.venta;

import com.unicaes.poo.domain.venta.dto.DtoSaveVenta;
import com.unicaes.poo.domain.venta.dto.DtoUpdateVenta;
import com.unicaes.poo.domain.venta.dto.DtoVentasList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    // Crear una nueva venta
    public Venta save(DtoSaveVenta dto) {
        Venta venta = new Venta();
        venta.setIdTicket(dto.bill_id());
        venta.setActive(true);
        return ventaRepository.save(venta);
    }

    // Obtener todas las ventas activas
    public Page<DtoVentasList> getVentasList(Pageable pageable) {
        return ventaRepository.findByActiveTrue(pageable).map(DtoVentasList::new);
    }

    // Actualizar una venta existente
    public Venta updateVenta(Long id, DtoUpdateVenta dto) {
        var venta = ventaRepository.getReferenceById(id);
        if (venta == null) {
            throw new RuntimeException("Venta no encontrada");
        }
        if (dto.bill_id() != null) {
            venta.setIdTicket(dto.bill_id());
        }
        return ventaRepository.save(venta);
    }

    // Eliminar una venta (marcarla como inactiva)
    public void deleteVenta(Long id) {
        var venta = ventaRepository.getReferenceById(id);
        venta.setActive(false);
        ventaRepository.save(venta);
    }
}
