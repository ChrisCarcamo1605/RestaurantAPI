package com.unicaes.poo.domain.user;

import com.unicaes.poo.domain.products.dto.DtoSaveType;
import com.unicaes.poo.domain.user.dtos.DtoUserTypeResponse;
import com.unicaes.poo.domain.user.dtos.DtoSaveUser;
import com.unicaes.poo.domain.user.dtos.DtoUserResponse;
import com.unicaes.poo.domain.user.dtos.DtoUserTypeUpdate;
import com.unicaes.poo.domain.user.dtos.DtoUserUpdate;
import com.unicaes.poo.domain.user.interfaces.IUserService;
import com.unicaes.poo.domain.user.interfaces.UserRepository;
import com.unicaes.poo.domain.user.interfaces.UserTypeRepository;
import com.unicaes.poo.infra.exceptions.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTypeRepository userTypeRepository;

    @Override
    public DtoUserResponse saveUser(DtoSaveUser dto) {

        try {
            var type = userTypeRepository.getReferenceById(dto.type());
            var user = new User();
            user.setUsername(dto.username());
            user.setType(type);
            user.setPassword(dto.password());
            user.setEmail(dto.email());
            var response = userRepository.save(user);
            System.out.println("hasta llegamo");
            return new DtoUserResponse(response);
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public List<DtoUserResponse> getUsers() {
        return userRepository.findByActiveIsTrue().stream().map(DtoUserResponse::new).collect(Collectors.toList());
    }

    @Override
    public DtoUserResponse updateUser(DtoUserUpdate dto) {
        try {

            var type = userTypeRepository.getReferenceById(dto.type());
            var user = userRepository.getReferenceById(dto.id());

            if (dto.username() != null) {
                user.setUsername(dto.username());
            }
            if (dto.type() != null) {
                user.setType(type);
            }
            if (dto.password() != null) {
                user.setPassword(dto.password());
            }
            if (dto.email() != null) {
                user.setEmail(dto.email());
            }
            return new DtoUserResponse(userRepository.save(user));

        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }

    }

    @Override
    public void deleteUser(Long id) {

        try {
            var user = userRepository.getReferenceById(id);
            user.setActive(false);
            userRepository.save(user);

        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }


    }

    @Override
    public DtoUserTypeResponse saveType(DtoSaveType dto) {
        try {
            var type = new UserType();
            type.setName(dto.name());
            return new DtoUserTypeResponse(userTypeRepository.save(type));
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }
    }

    @Override
    public List<DtoUserTypeResponse> getTypes() {
        try {
            return userTypeRepository.findAll().stream().map(DtoUserTypeResponse::new).collect(Collectors.toList());
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }

    }

    @Override
    public DtoUserTypeResponse updateType(DtoUserTypeUpdate dto) {
        try {
            var type = userTypeRepository.getReferenceById(dto.id());
            type.setName(dto.name());
            return new DtoUserTypeResponse(userTypeRepository.save(type));
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }
    }


    @Override
    public void deleteType(Long id) {

        try {
            var type = userTypeRepository.getReferenceById(id);
            userTypeRepository.delete(type);
        } catch (Exception e) {
            throw new QueryException(e.getMessage());
        }

    }
}
