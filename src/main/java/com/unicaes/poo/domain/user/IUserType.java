package com.unicaes.poo.domain.user;

import com.unicaes.poo.domain.user.dto.DtoUserTypeResponse;
import com.unicaes.poo.domain.user.dtos.DtoSaveUser;
import com.unicaes.poo.domain.user.dtos.DtoUserResponse;
import com.unicaes.poo.domain.user.dtos.DtoUserTypeUpdate;

import java.io.Serializable;
import java.util.List;

public interface IUserType {

    DtoUserTypeResponse saveType(DtoSaveUser dto);

    List<DtoUserTypeResponse> getTypes();

    DtoUserTypeResponse updateType(DtoUserTypeUpdate dto);

    void deleteType(Long id);

}
