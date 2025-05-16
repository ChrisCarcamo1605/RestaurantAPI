package com.unicaes.poo.domain.user.interfaces;

import com.unicaes.poo.domain.user.dtos.DtoSaveUser;
import com.unicaes.poo.domain.user.dtos.DtoUserResponse;
import com.unicaes.poo.domain.user.dtos.DtoUserUpdate;

import java.util.List;

public interface IUser {

    DtoUserResponse saveUser(DtoSaveUser dto);

    List<DtoUserResponse> getUsers();

    DtoUserResponse updateUser(DtoUserUpdate dto);

    void deleteUser(Long id);
}
