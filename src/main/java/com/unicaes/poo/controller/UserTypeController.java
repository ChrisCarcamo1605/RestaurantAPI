package com.unicaes.poo.controller;

import com.unicaes.poo.domain.products.dto.DtoSaveType;
import com.unicaes.poo.interfaces.user.UserService;
import com.unicaes.poo.domain.user.dtos.*;
import com.unicaes.poo.payload.MessageResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user_type")
public class UserTypeController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> addUserType(/*@Valid*/ @RequestBody DtoSaveType dto, UriComponentsBuilder ucBuilder) {
        DtoUserTypeResponse newUserType = userService.saveType(dto);
        URI uri = ucBuilder.path("/user_type/{id}").buildAndExpand(newUserType.id()).toUri();
        return ResponseEntity.created(uri).body(
                MessageResponse.<DtoUserTypeResponse>builder()
                        .message("Tipo de usuario añadido exitosamente")
                        .data(newUserType)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<?> getUserTypes() {
        List<DtoUserTypeResponse> userTypes = userService.getTypes();
        return ResponseEntity.ok().body(
                MessageResponse.<List<DtoUserTypeResponse>>builder()
                        .message("Tipos de usuario recuperados exitosamente")
                        .data(userTypes)
                        .build()
        );
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> updateUserType(@RequestBody DtoUserTypeUpdate dto) {
        DtoUserTypeResponse updatedUserType = userService.updateType(dto);
        if (updatedUserType == null) {
            return ResponseEntity.status(404).body(
                    MessageResponse.<DtoUserTypeResponse>builder()
                            .message("Tipo de usuario no encontrado para actualizar.")
                            .data(null)
                            .build()
            );
        }

        return ResponseEntity.accepted().body(
                MessageResponse.<DtoUserTypeResponse>builder()
                        .message("Tipo de usuario actualizado exitosamente")
                        .data(updatedUserType)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DtoUserResponse> deleteUser(@PathVariable Long id) {
        userService.deleteType(id);
        return ResponseEntity.noContent().build();
    }

}
