package com.unicaes.poo.domain.user;

import com.unicaes.poo.domain.user.dtos.DtoSaveUser;
import com.unicaes.poo.domain.user.dtos.DtoUserResponse;

import java.util.List;

public interface IUser {

    DtoUserResponse saveUser(DtoSaveUser dto);

    List<DtoUserResponse> getUsers();

    DtoUserResponse updateUser(Long id);

    void deleteUser(Long id);
}
