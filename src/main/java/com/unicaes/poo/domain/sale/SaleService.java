package com.unicaes.poo.domain.sale;

import com.unicaes.poo.domain.sale.dto.DtoSaveSale;
import com.unicaes.poo.domain.sale.dto.DtoUpdateSale;
import com.unicaes.poo.domain.sale.dto.DtoSaleList;
import com.unicaes.poo.infra.exceptions.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public Sale save(DtoSaveSale dto) {




        try {
            Sale sale = new Sale();
            sale.setIdBill(dto.billId());
            sale.setActive(true);
            sale.setSaleDate(dto.saleDate());
            sale.setTotal(dto.total());



            return saleRepository.save(sale);
        } catch (Exception e) {
            throw new QueryException("Error al guardar la venta: " + e.getMessage());
        }
    }

    public List<DtoSaleList> getSalesList() {
        try
        {
            return saleRepository.findByActiveTrue()
                    .stream()
                    .map(sale -> new DtoSaleList(sale))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new QueryException("Error al obtener la lista de ventas: " + e.getMessage());
        }
    }

    public Sale updateSale(Long id, DtoUpdateSale dto) {
        try {
            var sale = saleRepository.findById(id)
                    .orElseThrow(() -> new QueryException("El ID proporcionado no corresponde a ninguna venta"));

            if (dto.billId() != null) {
                sale.setIdBill(dto.billId());
            }
            if (dto.saleDate() != null) {
                sale.setSaleDate(dto.saleDate());
            }
            if (dto.total() != null) {
                sale.setTotal(dto.total());
            }
            return saleRepository.save(sale);
        } catch (Exception e) {
            throw new QueryException("Error al actualizar la venta: " + e.getMessage());
        }
    }

    public void deleteSale(Long id) {
        try {
            var sale = saleRepository.findById(id)
                    .orElseThrow(() -> new QueryException("Venta no encontrada"));

            sale.setActive(false);
            saleRepository.save(sale);
        } catch (Exception e) {
            throw new QueryException("Error al eliminar la venta: " + e.getMessage());
        }
    }
}
