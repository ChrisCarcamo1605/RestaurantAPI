package com.unicaes.poo.interfaces.user;

import com.unicaes.poo.domain.products.dto.DtoSaveType;
import com.unicaes.poo.domain.user.dtos.DtoUserTypeResponse;
import com.unicaes.poo.domain.user.dtos.DtoUserTypeUpdate;

import java.util.List;

public interface IUserType {

    DtoUserTypeResponse saveType(DtoSaveType dto);

    List<DtoUserTypeResponse> getTypes();

    DtoUserTypeResponse updateType(DtoUserTypeUpdate dto);

    void deleteType(Long id);
}
