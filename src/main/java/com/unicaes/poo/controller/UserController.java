package com.unicaes.poo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public ResponseEntity getUser(){

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateUser(){

        return ResponseEntity.ok().build();
    }
}
