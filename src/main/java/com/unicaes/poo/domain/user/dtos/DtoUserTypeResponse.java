package com.unicaes.poo.domain.user.dtos;

import com.unicaes.poo.domain.user.UserType;

public record DtoUserTypeResponse(Long id, String name) {

    public DtoUserTypeResponse(UserType type) {
        this(type.getTypeId(), type.getName());
    }
}
