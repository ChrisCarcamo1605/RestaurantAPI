package com.unicaes.poo.domain.table;

import com.unicaes.poo.domain.table.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService implements ITableService {

    @Autowired
    private TableRepository mesaRepository;

    @Override
    public List<DtoTableList> findAll() {
        return mesaRepository.findByActiveIsTrue()
                .stream()
                .map(DtoTableList::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DtoTableResponse findById(Long id) {
        Table mesa = mesaRepository.findById(id).orElseThrow();
        return DtoTableResponse.fromEntity(mesa);
    }

    @Override
    public DtoTableResponse save(DtoTableSave dto) {
        Table mesa = dto.toEntity();
        return DtoTableResponse.fromEntity(mesaRepository.save(mesa));
    }

    @Override
    public DtoTableResponse update(DtoTableUpdate dto) {
        Table mesa = mesaRepository.getReferenceById(dto.id());

        if(dto.capacity() != null){
            mesa.setCapacity(dto.capacity());
        }
        if(dto.number() != null){
            mesa.setNumber(dto.number());
        }
        return DtoTableResponse.fromEntity(mesaRepository.save(mesa));
    }

    @Override
    public void delete(Long id) {

        var mesa =  mesaRepository.getReferenceById(id);
        mesa.setActive(Boolean.FALSE);
        mesaRepository.save(mesa);
    }
}
