package com.unicaes.poo.domain.venta;

import com.unicaes.poo.domain.venta.dto.DtoSaveVenta;
import com.unicaes.poo.domain.venta.dto.DtoUpdateVenta;
import com.unicaes.poo.domain.venta.dto.DtoVentasList;
import com.unicaes.poo.infra.exceptions.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public Venta save(DtoSaveVenta dto) {

        try{
        Venta venta = new Venta();
        venta.setIdTicket(dto.bill_id());
        venta.setActive(true);
        return ventaRepository.save(venta);
        }catch(Exception e){
            throw new QueryException(e.getMessage());
        }
    }

    public Page<DtoVentasList> getVentasList(Pageable pageable) {
        try  { return ventaRepository.findByActiveTrue(pageable).map(DtoVentasList::new);  } catch (Exception e) {
                throw new QueryException(e.getMessage());
        }

    }

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

    public void deleteVenta(Long id) {
        var venta = ventaRepository.getReferenceById(id);
        venta.setActive(false);
        ventaRepository.save(venta);
    }
}
