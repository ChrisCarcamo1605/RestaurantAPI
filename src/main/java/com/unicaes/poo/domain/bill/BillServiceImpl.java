package com.unicaes.poo.domain.bill;


import com.unicaes.poo.domain.bill.dto.*;
import com.unicaes.poo.repository.BillDetailsRepository;
import com.unicaes.poo.interfaces.bill.BillService;
import com.unicaes.poo.repository.CustomerRepository;
import com.unicaes.poo.repository.ProductRepository;
import com.unicaes.poo.repository.UserRepository;
import com.unicaes.poo.repository.*;
import com.unicaes.poo.infra.exceptions.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private CustomerRepository clienteService;

    @Autowired
    private BillDetailsRepository billDetailsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<DtoBillList> getAll() {
        try {

            return billRepository.findByActiveIsTrue().stream()
                    .map(DtoBillList::toEntity)
                    .collect(Collectors.toList());


        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }

    }

    @Override
    public DtoBillResponse updateBill(DtoBillUpdate dto) {

        try {
            var bill = billRepository.getReferenceById(dto.id());
            var waiter = userRepository.getReferenceById(dto.waiter());
            if (dto.doneDate() != null) {
                bill.setDoneDate(dto.doneDate());
            }
            if (dto.totalAmount() != null) {
                bill.setTotalAmount(dto.totalAmount());
            }
            if (dto.waiter() != null) {
                bill.setWaiter(waiter);
            }

            return DtoBillResponse.toEntity(billRepository.save(bill));
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }

    }

    @Override
    public DtoBillResponse save(DtoBillSave bill) {

        try {
            var customer = clienteService.getReferenceById(bill.customer());
            var user = userRepository.getReferenceById(bill.waiter());
            var newBill = new Bill();

            newBill.setCustomer(customer);
            newBill.setWaiter(user);
            newBill.setEmissionDate(bill.emissionDate());
            newBill.setDoneDate(bill.doneDate());
            newBill.setTotalAmount(bill.totalAmount());
            billRepository.save(newBill);
            return DtoBillResponse.toEntity(newBill);
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }

    }

    @Override
    public void delete(Long id) {
        try {
            var bill = billRepository.getReferenceById(id);
            bill.setActive(false);
            billRepository.save(bill);
        } catch (QueryException e) {
            throw new QueryException(e.getMessage());
        }
    }


    @Override
    public List<DtoBillDetailsList> getAllActive() {
        try {
            return billDetailsRepository.findAllActiveAsDto();
        } catch (QueryException e) {
            throw new QueryException(e.getMessage());
        }

    }

    @Override
    public DtoBillDetailsResponse getById(Long id) {

        try {
            return billDetailsRepository.findActiveById(id)
                    .map(DtoBillDetailsResponse::fromEntity)
                    .orElseThrow(() -> new QueryException("Bill detail not found with id: " + id));
        } catch (QueryException e) {

            throw new QueryException(e.getMessage());
        }

    }

    @Override
    public DtoBillDetailsResponse save(DtoBillDetailsSave dto) {
        try {

            System.out.println("DENTRO DEL SAVEEEEEEEEEEEEE");
            var bill = billRepository.getReferenceById(dto.billId());


            var product = productRepository.getReferenceById(dto.productId());
            System.out.println("DENTRO DEL 1111");
            if (!product.isActive()) {
                throw new QueryException("Cannot add inactive product to bill");
            }

            System.out.println("DENTRO DEL 222222222222222");
            var detail = new BillDetails();
            detail.setBill(bill);
            detail.setProduct(product);
            detail.setQuantity(dto.quantity());
            detail.setUnitPrice(product.getPriceSell());
            detail.setTotal(product.getPriceSell().multiply(BigDecimal.valueOf(dto.quantity())));
            detail.setActive(true);

            return DtoBillDetailsResponse.fromEntity(billDetailsRepository.save(detail));
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }

    }

    @Override
    public DtoBillDetailsResponse update(DtoBillDetailsUpdate dto) {
        // Verificar que el detalle existe
        try {
            if (!billDetailsRepository.existsByIdAndActiveIsTrue(dto.id())) {
                throw new QueryException("Bill detail not found with id: " + dto.id());
            }
        } catch (QueryException e) {
            throw new QueryException(e.getMessage());
        }


        billDetailsRepository.updateDetails(dto);
        return this.getById(dto.id());
    }

    @Override
    public void deactivate(Long id) {
        if (!billDetailsRepository.existsByIdAndActiveIsTrue(id)) {
            throw new QueryException("Bill detail not found with id: " + id);
        }
        billDetailsRepository.deactivateById(id);
    }
}
