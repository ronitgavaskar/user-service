package com.gavaskar.app.ws.service;

import com.gavaskar.app.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    // createUser which needs to accept UserDto user and return UserDto
    UserDto createUser(UserDto user);
}
