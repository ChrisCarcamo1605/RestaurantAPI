package com.unicaes.poo.domain.user.dtos;

import com.unicaes.poo.domain.user.User;
import com.unicaes.poo.domain.user.UserType;

public record DtoUserResponse(Long id, String username, UserType type, String email, String password) {

    public DtoUserResponse(User user) {

        this(user.getId(), user.getUsername(),user.getType(),user.getEmail(),user.getPassword());

    }
}
