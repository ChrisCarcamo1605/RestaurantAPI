package com.unicaes.poo.domain.customer;

import com.unicaes.poo.domain.customer.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository clienteRepository;

    @Override
    public List<DtoCustomerList> findAll() {

        System.out.println("dentro del get pa");
        return clienteRepository.findAll()
                .stream()
                .map(DtoCustomerList::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DtoCustomerResponse findById(Long id) {
        Customer cliente = clienteRepository.findById(id).orElseThrow();
        return DtoCustomerResponse.fromEntity(cliente);
    }

    @Override
    public DtoCustomerResponse save(DtoCustomerSave dto) {
        Customer cliente = dto.toEntity();
        var a = clienteRepository.save(cliente);
        System.out.println("dentro del save");
        return DtoCustomerResponse.fromEntity(a);
    }

    @Override
    public DtoCustomerResponse update(Long id, DtoCustomerUpdate dto) {
        Customer cliente = clienteRepository.findById(id).orElseThrow();
        dto.updateEntity(cliente);
        return DtoCustomerResponse.fromEntity(clienteRepository.save(cliente));
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
