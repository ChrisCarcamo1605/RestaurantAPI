package com.unicaes.poo.domain.user.dto;

import com.unicaes.poo.domain.user.UserType;
import com.unicaes.poo.domain.user.dtos.DtoUserResponse;

public record DtoUserTypeResponse(Long id, String name) {

    public DtoUserTypeResponse(UserType type) {
        this(type.getTypeId(), type.getName());
    }
}
