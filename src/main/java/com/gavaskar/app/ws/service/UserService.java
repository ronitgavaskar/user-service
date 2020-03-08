package com.gavaskar.app.ws.service;

import com.gavaskar.app.ws.shared.dto.UserDto;

public interface UserService {

    // createUser which needs to accept UserDto user and return UserDto
    UserDto createUser(UserDto user);
}
