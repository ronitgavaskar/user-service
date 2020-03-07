package com.gavaskar.app.ws.ui.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")  //http://localhost:8080/users
public class UserController {

    // get user information
    @GetMapping
    public String getUser() {
        return "get user was called";
    }

    // create user
    @PostMapping
    public String createUser() {
        return "create user was called";
    }

    //update user information
    @PutMapping
    public String updateUser() {
        return "update user was called";
    }

    // delete user
    @DeleteMapping
    public String deleteUser() {
        return "delete user was called";
    }

}
