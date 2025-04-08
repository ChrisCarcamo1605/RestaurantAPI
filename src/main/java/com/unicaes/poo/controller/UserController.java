package com.unicaes.poo.controller;


import com.unicaes.poo.domain.measurementUnit.MeasurementUnit;
import com.unicaes.poo.domain.user.IUserService;
import com.unicaes.poo.domain.user.dtos.DtoSaveUser;
import com.unicaes.poo.domain.user.dtos.DtoUserResponse;
import com.unicaes.poo.domain.user.dtos.DtoUserUpdate;
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
    IUserService userService;

    @PostMapping
    public ResponseEntity<DtoUserResponse> addUser(@RequestBody DtoSaveUser dto, UriComponentsBuilder ucBuilder) {

        var user = userService.saveUser(dto);
        URI uri = ucBuilder.path("/user").buildAndExpand(user.id()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping
    public ResponseEntity<List<DtoUserResponse>> getUser() {

        var users = userService.getUsers();
        return ResponseEntity.ok().body(users);
    }

    @PutMapping
    public ResponseEntity<DtoUserResponse> updateUser(@RequestBody DtoUserUpdate dto) {

        var user = userService.updateUser(dto);
        return ResponseEntity.ok().build();
    }
}
