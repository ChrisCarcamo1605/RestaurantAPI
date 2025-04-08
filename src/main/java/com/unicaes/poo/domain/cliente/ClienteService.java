package com.unicaes.poo.domain.cliente;

import com.unicaes.poo.domain.cliente.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<DtoClienteList> findAll() {

        System.out.println("dentro del get pa");
        return clienteRepository.findAll()
                .stream()
                .map(DtoClienteList::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DtoClienteResponse findById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        return DtoClienteResponse.fromEntity(cliente);
    }

    @Override
    public DtoClienteResponse save(DtoClienteSave dto) {
        Cliente cliente = dto.toEntity();
        var a = clienteRepository.save(cliente);
        System.out.println("dentro del save");
        return DtoClienteResponse.fromEntity(a);
    }

    @Override
    public DtoClienteResponse update(Long id, DtoClienteUpdate dto) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        dto.updateEntity(cliente);
        return DtoClienteResponse.fromEntity(clienteRepository.save(cliente));
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
