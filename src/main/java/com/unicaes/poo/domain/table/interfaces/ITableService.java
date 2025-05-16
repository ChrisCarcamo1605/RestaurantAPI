package com.unicaes.poo.domain.table.interfaces;

import com.unicaes.poo.domain.table.dto.*;

import java.util.List;

public interface ITableService {
    List<DtoTableList> findAll();
    DtoTableResponse findById(Long id);
    DtoTableResponse save(DtoTableSave dto);
    DtoTableResponse update(DtoTableUpdate dto);
    void delete(Long id);
}
