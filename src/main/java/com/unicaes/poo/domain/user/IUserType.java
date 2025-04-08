package com.unicaes.poo.domain.user;

import com.unicaes.poo.domain.user.dtos.DtoSaveUser;
import com.unicaes.poo.domain.user.dtos.DtoUserResponse;

import java.io.Serializable;
import java.util.List;

public interface IUserType {

    DtoUserResponse saveType(DtoSaveUser dto);

    List<DtoUserResponse> getTypes();

    DtoUserResponse updateType(Long id);

    void deleteType(Long id);

}
