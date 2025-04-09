package com.unicaes.poo.controller;

import com.unicaes.poo.domain.products.dto.DtoSaveType;
import com.unicaes.poo.domain.user.IUserService;
import com.unicaes.poo.domain.user.User;
import com.unicaes.poo.domain.user.dtos.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user_type")
public class UserTypeController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<DtoUserTypeResponse> addUser(@Valid @RequestBody DtoSaveType user, UriComponentsBuilder ucBuilder) {

        var newUser = userService.saveType(user);
        URI uri = ucBuilder.path("/user_type").buildAndExpand(newUser.id()).toUri();
        return ResponseEntity.created(uri).body(newUser);
    }

    @GetMapping
    public ResponseEntity<List<DtoUserTypeResponse>> getUsers() {
        var lists = userService.getTypes();
        return ResponseEntity.ok().body(lists);
    }

    @PutMapping
    public ResponseEntity<DtoUserTypeResponse> updateUser(@Valid @RequestBody DtoUserTypeUpdate dto) {
        var user = userService.updateType(dto);
        return ResponseEntity.accepted().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DtoUserResponse> deleteUser(@PathVariable Long id) {
        userService.deleteType(id);
        return ResponseEntity.noContent().build();
    }

}
