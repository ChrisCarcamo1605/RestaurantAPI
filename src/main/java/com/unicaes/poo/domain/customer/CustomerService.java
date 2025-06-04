package com.unicaes.poo.domain.customer;

import com.unicaes.poo.domain.customer.dto.*;
import com.unicaes.poo.repository.CustomerRepository;
import com.unicaes.poo.infra.exceptions.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService implements com.unicaes.poo.interfaces.customer.CustomerService {

    @Autowired
    private CustomerRepository clienteRepository;

    @Override
    public List<DtoCustomerList> findAll() {
        try {
            return clienteRepository.findAll()
                    .stream()
                    .map(DtoCustomerList::fromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new QueryException("Error al obtener la lista de clientes: " + e.getMessage());
        }
    }

    @Override
    public DtoCustomerResponse findById(Long id) {
        try {
            Customer cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new QueryException("Cliente no encontrado con id: " + id));
            return DtoCustomerResponse.fromEntity(cliente);
        } catch (Exception e) {
            throw new QueryException("Error al buscar cliente por id: " + e.getMessage());
        }
    }


    @Override
    public DtoCustomerResponse save(DtoCustomerSave dto) {
        try {
            Customer cliente = dto.toEntity();
            return DtoCustomerResponse.fromEntity(clienteRepository.save(cliente));
        } catch (Exception e) {
            throw new QueryException("Error al guardar cliente: " + e.getMessage());
        }
    }


    @Override
    public DtoCustomerResponse update(Long id, DtoCustomerUpdate dto) {
        try {
            Customer cliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new QueryException("Cliente no encontrado con id: " + id));
            dto.updateEntity(cliente);
            return DtoCustomerResponse.fromEntity(clienteRepository.save(cliente));
        } catch (Exception e) {
            throw new QueryException("Error al actualizar cliente: " + e.getMessage());
        }
    }


    @Override
    public void delete(Long id) {
        try {
            clienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new QueryException("Error al eliminar cliente: " + e.getMessage());
        }
    }
}
