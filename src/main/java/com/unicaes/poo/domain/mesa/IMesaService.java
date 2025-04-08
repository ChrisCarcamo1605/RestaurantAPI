package com.unicaes.poo.domain.mesa;

import com.unicaes.poo.domain.mesa.dto.*;

import java.util.List;

public interface IMesaService {
    List<DtoMesaList> findAll();
    DtoMesaResponse findById(Long id);
    DtoMesaResponse save(DtoMesaSave dto);
    DtoMesaResponse update(Long id, DtoMesaUpdate dto);
    void delete(Long id);
}
