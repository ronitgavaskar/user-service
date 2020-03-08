package com.gavaskar.app.ws.ui.controller;

import com.gavaskar.app.ws.service.UserService;
import com.gavaskar.app.ws.shared.dto.UserDto;
import com.gavaskar.app.ws.ui.model.request.UserDetailsReqModel;
import com.gavaskar.app.ws.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping("users")  //http://localhost:8080/users
public class UserController {

    @Autowired
    private UserService userService;

    // get user information
    @GetMapping(path="/{userId}",
        produces ={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public UserRest getUser(@PathVariable String userId) {
        //provide userId
        UserRest ret = new UserRest();

        UserDto user = userService.getUserByUserId(userId);
        BeanUtils.copyProperties(user, ret);

        return ret;
    }

    // create user
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces ={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
            )
    public UserRest createUser(@RequestBody UserDetailsReqModel userDetails) {
        UserRest ret = new UserRest();


        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, ret);

        return ret;
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
