package com.gavaskar.app.ws.ui.controller;

import com.gavaskar.app.ws.exceptions.UserServiceException;
import com.gavaskar.app.ws.io.entity.UserEntity;
import com.gavaskar.app.ws.service.UserService;
import com.gavaskar.app.ws.shared.dto.UserDto;
import com.gavaskar.app.ws.ui.model.request.UserDetailsReqModel;
import com.gavaskar.app.ws.ui.model.response.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;

@RestController
@RequestMapping("users")  //http://localhost:8080/users
public class UserController {

    @Autowired
    private UserService userService;

    // pagination is 0-indexed VERY IMPORTANT
    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ArrayList<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "25") int limit) {
        ArrayList<UserRest> ret = new ArrayList<>();

        ArrayList<UserDto> users = userService.getAllUsers(page, limit);

        for (UserDto user: users) {
            UserRest userModel = new UserRest();
            BeanUtils.copyProperties(user, userModel);
            ret.add(userModel);
        }

        return ret;

    }

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
    public UserRest createUser(@RequestBody UserDetailsReqModel userDetails) throws Exception {
        UserRest ret = new UserRest();

        if (userDetails.getFirstName().isEmpty())
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, ret);

        return ret;
    }

    //update user information

    /**
     * First difference: we will be using HTTP PUT Request
     * Provide ID of user to update
     * JSON Body to update such as firstName, lastName
     * Only if user has authenticated and if user has JWT value for application
     *
     */
    @PutMapping(
            path = "/{userId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public UserRest updateUser(@RequestBody UserDetailsReqModel userDetails, @PathVariable String userId) {

        UserRest ret = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto user = userService.updateUser(userDto, userId);
        BeanUtils.copyProperties(user, ret);

        return ret;
    }

    // delete user

    /**
     * Get proper authorization
     * UserId will be passed in as parameter to delete and identifier of which user
     */

    @DeleteMapping(
            path = "/{userId}",
            produces ={ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
    )
    public OperationStatus deleteUser(@PathVariable String userId) {
        OperationStatus ret = new OperationStatus();

        ret.setOperationName(RequestOperationName.DELETE.getOperation());
        userService.deleteUser(userId);

        ret.setOperationResult(RequestOperationStatus.SUCCESS.getStatus());
        return ret;
    }

}
