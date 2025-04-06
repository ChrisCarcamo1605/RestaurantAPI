package com.unicaes.poo.domain.bill;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BillService implements IBillService {

    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> getAll() {
        return billRepository.findAll();
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void delete(Long id) {

        var bill = billRepository.getReferenceById(id);

        //bill.setActive(false);
        billRepository.save(bill);
    }






}
