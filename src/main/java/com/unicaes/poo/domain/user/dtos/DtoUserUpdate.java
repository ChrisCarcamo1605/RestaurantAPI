package com.unicaes.poo.domain.user.dtos;

import com.unicaes.poo.domain.user.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DtoUserUpdate(Long id, String username, Long type, String password, @Email String email) {
}
