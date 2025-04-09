package com.unicaes.poo.controller;

import com.unicaes.poo.domain.user.IUserService;
import com.unicaes.poo.domain.user.User;
import com.unicaes.poo.domain.user.dtos.DtoSaveUser;
import com.unicaes.poo.domain.user.dtos.DtoUserResponse;
import com.unicaes.poo.domain.user.dtos.DtoUserUpdate;
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
    public ResponseEntity<DtoUserResponse> addUser(@RequestBody DtoSaveUser user, UriComponentsBuilder ucBuilder) {

        var newUser = userService.saveUser(user);
        URI uri = ucBuilder.path("/user_type").buildAndExpand(newUser.id()).toUri();
        return ResponseEntity.created(uri).body(newUser);
    }

    @GetMapping
    public ResponseEntity<List<DtoUserResponse>> getUsers() {
        var lists = userService.getUsers();
        return ResponseEntity.ok().body(lists);
    }

    @PutMapping
    public ResponseEntity<DtoUserResponse> updateUser(@RequestBody DtoUserUpdate dto) {
        var user = userService.updateUser(dto);
        return ResponseEntity.accepted().body(user);
    }

    @DeleteMapping
    public ResponseEntity<DtoUserResponse> deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
