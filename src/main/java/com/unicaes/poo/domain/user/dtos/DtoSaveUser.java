package com.unicaes.poo.domain.user.dtos;

import com.unicaes.poo.domain.user.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoSaveUser(@NotBlank String username, @NotNull Long type, @NotBlank String password,
                          @Email String email) {
}
