package com.unicaes.poo.domain.table.dto;

import jakarta.validation.constraints.Size;

public record DtoTableUpdate(Long id, String number, @Size(max = 3, message = "Name must be ≤ 3 characters") Integer capacity) {


}
