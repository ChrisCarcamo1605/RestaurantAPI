package com.unicaes.poo.domain.user;

import com.unicaes.poo.domain.user.dtos.DtoSaveUser;
import com.unicaes.poo.domain.user.dtos.DtoUserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Override
    public DtoUserResponse saveUser(DtoSaveUser dto) {
        return null;
    }

    @Override
    public List<DtoUserResponse> getUsers() {
        return List.of();
    }

    @Override
    public DtoUserResponse updateUser(Long id) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public DtoUserResponse saveType(DtoSaveUser dto) {
        return null;
    }

    @Override
    public List<DtoUserResponse> getTypes() {
        return List.of();
    }

    @Override
    public DtoUserResponse updateType(Long id) {
        return null;
    }

    @Override
    public void deleteType(Long id) {

    }
}
