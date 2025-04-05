package com.unicaes.poo.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DtoSaveUser(@NotBlank String username, @NotBlank String password, @Email String email) {
}
