package com.unicaes.poo.domain.bill;

import com.unicaes.poo.domain.products.ProductRepository;
import com.unicaes.poo.domain.bill.dto.*;
import com.unicaes.poo.infra.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillDetailsService implements IBillDetails {

    private final BillDetailsRepository billDetailsRepository;
    private final BillRepository billRepository;
    private final ProductRepository productRepository;

    @Override
    public List<DtoBillDetailsList> getAllActive() {
        return billDetailsRepository.findAllActiveAsDto();
    }

    @Override
    public DtoBillDetailsResponse getById(Long id) {
        return billDetailsRepository.findActiveById(id)
                .map(DtoBillDetailsResponse::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Bill detail not found with id: " + id));
    }

    @Override
    public DtoBillDetailsResponse save(DtoBillDetailsSave dto) {
        var bill = billRepository.findById(dto.billId())
                .orElseThrow(() -> new EntityNotFoundException("Bill not found with id: " + dto.billId()));

        var product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + dto.productId()));

        if (!product.isActive()) {
            throw new EntityNotFoundException("Cannot add inactive product to bill");
        }

        var detail = new BillDetails();
        detail.setBill(bill);
        detail.setProduct(product);
        detail.setQuantity(dto.quantity());
        detail.setUnitPrice(product.getPriceSell());
        detail.setTotal(product.getPriceSell().multiply(BigDecimal.valueOf(dto.quantity())));
        detail.setActive(true);

        return DtoBillDetailsResponse.fromEntity(billDetailsRepository.save(detail));
    }

    @Override
    public DtoBillDetailsResponse update(DtoBillDetailsUpdate dto) {
        // Verificar que el detalle existe
        if (!billDetailsRepository.existsByIdAndActiveIsTrue(dto.id())) {
            throw new EntityNotFoundException("Bill detail not found with id: " + dto.id());
        }

        billDetailsRepository.updateDetails(dto);
        return this.getById(dto.id());
    }

    @Override
    public void deactivate(Long id) {
        if (!billDetailsRepository.existsByIdAndActiveIsTrue(id)) {
            throw new EntityNotFoundException("Bill detail not found with id: " + id);
        }
        billDetailsRepository.deactivateById(id);
    }
}