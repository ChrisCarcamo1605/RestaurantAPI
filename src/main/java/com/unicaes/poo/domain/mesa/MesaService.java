package com.unicaes.poo.domain.mesa;

import com.unicaes.poo.domain.mesa.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MesaService implements IMesaService {

    @Autowired
    private MesaRepository mesaRepository;

    @Override
    public List<DtoMesaList> findAll() {
        return mesaRepository.findByActivoIsTrue()
                .stream()
                .map(DtoMesaList::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DtoMesaResponse findById(Long id) {
        Mesa mesa = mesaRepository.findById(id).orElseThrow();
        return DtoMesaResponse.fromEntity(mesa);
    }

    @Override
    public DtoMesaResponse save(DtoMesaSave dto) {
        Mesa mesa = dto.toEntity();
        return DtoMesaResponse.fromEntity(mesaRepository.save(mesa));
    }

    @Override
    public DtoMesaResponse update(Long id, DtoMesaUpdate dto) {
        Mesa mesa = mesaRepository.findById(id).orElseThrow();
        dto.updateEntity(mesa);
        return DtoMesaResponse.fromEntity(mesaRepository.save(mesa));
    }

    @Override
    public void delete(Long id) {

        var mesa =  mesaRepository.getReferenceById(id);
        mesa.setActivo(Boolean.FALSE);
        mesaRepository.save(mesa);
    }
}
