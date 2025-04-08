package com.unicaes.poo.domain.bill;


import com.unicaes.poo.domain.bill.dto.DtoBillList;
import com.unicaes.poo.domain.bill.dto.DtoBillResponse;
import com.unicaes.poo.domain.bill.dto.DtoBillSave;
import com.unicaes.poo.domain.cliente.ClienteRepository;
import com.unicaes.poo.domain.cliente.ClienteService;
import com.unicaes.poo.domain.cliente.dto.DtoClienteResponse;
import com.unicaes.poo.domain.user.UserRepository;
import com.unicaes.poo.infra.exceptions.QueryException;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillService implements IBillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ClienteRepository clienteService;

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


}
