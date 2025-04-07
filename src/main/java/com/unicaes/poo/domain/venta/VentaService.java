package com.unicaes.poo.domain.venta;

import com.unicaes.poo.domain.venta.dto.DtoVentaResponse;
import com.unicaes.poo.domain.venta.dto.DtoSaveVenta;
import com.unicaes.poo.domain.venta.dto.DtoUpdateVenta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public DtoVentaResponse saveVenta(DtoSaveVenta dtoSaveVenta) {
        Venta venta = new Venta();
        venta.setCliente(dtoSaveVenta.getCliente());
        venta.setTotal(dtoSaveVenta.getTotal());
        venta = ventaRepository.save(venta);
        return new DtoVentaResponse(venta.getId(), venta.getCliente(), venta.getTotal());
    }

    public List<DtoVentaResponse> getAllVentas() {
        List<Venta> ventas = ventaRepository.findAll();
        return ventas.stream()
                .map(venta -> new DtoVentaResponse(venta.getId(), venta.getCliente(), venta.getTotal()))
                .collect(Collectors.toList());
    }

    public DtoVentaResponse getVentaById(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        return new DtoVentaResponse(venta.getId(), venta.getCliente(), venta.getTotal());
    }

    public DtoVentaResponse updateVenta(Long id, DtoUpdateVenta dtoUpdateVenta) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        venta.setCliente(dtoUpdateVenta.getCliente());
        venta.setTotal(dtoUpdateVenta.getTotal());
        venta = ventaRepository.save(venta);
        return new DtoVentaResponse(venta.getId(), venta.getCliente(), venta.getTotal());
    }

    public void deleteVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        ventaRepository.delete(venta);
    }
}
