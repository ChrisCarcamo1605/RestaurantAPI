package com.unicaes.poo.domain.cliente;

import com.unicaes.poo.domain.cliente.dto.*;

import java.util.List;

public interface IClienteService {
    List<DtoClienteList> findAll();
    DtoClienteResponse findById(Long id);
    DtoClienteResponse save(DtoClienteSave dto);
    DtoClienteResponse update(Long id, DtoClienteUpdate dto);
    void delete(Long id);
}
