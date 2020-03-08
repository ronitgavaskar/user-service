package com.gavaskar.app.ws.service.impl;

import com.gavaskar.app.ws.io.repository.UserRepository;
import com.gavaskar.app.ws.io.entity.UserEntity;
import com.gavaskar.app.ws.service.UserService;
import com.gavaskar.app.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);


        userEntity.setEncryptedPassword("test");
        userEntity.setUserId("testUserId");



        UserEntity savedUser = userRepository.save(userEntity);

        UserDto returnVal = new UserDto();
        BeanUtils.copyProperties(savedUser, returnVal);

        return returnVal;
    }
}
