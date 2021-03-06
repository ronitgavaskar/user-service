package com.gavaskar.app.ws.service;

import com.gavaskar.app.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

public interface UserService extends UserDetailsService {

    // createUser which needs to accept UserDto user and return UserDto
    UserDto createUser(UserDto user) throws Exception;

    //getUser which needs to accept an email and return a DTO for the user found
    UserDto getUser(String email);

    //getUserByUserId which needs to accept a public userId and returns DTO for user found
    UserDto getUserByUserId(String userId);

    //updateUser takes in userId which is how we will get back the user to update
    UserDto updateUser(UserDto user, String userId);

    //deleteUser takes in userId and deletes user from database
    void deleteUser(String userId);

    //getAllUsers gets a list of users
    ArrayList<UserDto> getAllUsers(int page, int limit);

}
