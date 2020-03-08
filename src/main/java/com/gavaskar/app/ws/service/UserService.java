package com.gavaskar.app.ws.service;

import com.gavaskar.app.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    // createUser which needs to accept UserDto user and return UserDto
    UserDto createUser(UserDto user);

    //getUser which needs to accept an email and return a DTO for the user found
    UserDto getUser(String email);

    //getUserByUserId which needs to accept a public userId and returns DTO for user found
    UserDto getUserByUserId(String userId);
}
