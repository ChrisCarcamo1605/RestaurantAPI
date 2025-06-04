package com.unicaes.poo.controller;


import com.unicaes.poo.interfaces.user.UserService;
import com.unicaes.poo.domain.user.dtos.DtoSaveUser;
import com.unicaes.poo.domain.user.dtos.DtoUserResponse;
import com.unicaes.poo.domain.user.dtos.DtoUserUpdate;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> addUser(/*@Valid*/ @RequestBody DtoSaveUser dto, UriComponentsBuilder ucBuilder) {
        DtoUserResponse user = userService.saveUser(dto);

        URI uri = ucBuilder.path("/user/{id}").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(
                MessageResponse.<DtoUserResponse>builder()
                        .message("Usuario añadido exitosamente")
                        .data(user)
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<?> getUser() {
        List<DtoUserResponse> users = userService.getUsers();
        return ResponseEntity.ok().body(
                MessageResponse.<List<DtoUserResponse>>builder()
                        .message("Usuarios recuperados exitosamente")
                        .data(users)
                        .build()
        );
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> updateUser(/*@Valid*/ @RequestBody DtoUserUpdate dto) {
        DtoUserResponse user = userService.updateUser(dto);
        if (user == null) {
            return ResponseEntity.status(404).body(
                    MessageResponse.<DtoUserResponse>builder()
                            .message("Usuario no encontrado para actualizar.")
                            .data(null)
                            .build()
            );
        }
        return ResponseEntity.accepted().body(
                MessageResponse.<DtoUserResponse>builder()
                        .message("Usuario actualizado exitosamente")
                        .data(user)
                        .build()
        );
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
