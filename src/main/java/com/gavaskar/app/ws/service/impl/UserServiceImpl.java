package com.gavaskar.app.ws.service.impl;

import com.gavaskar.app.ws.io.repository.UserRepository;
import com.gavaskar.app.ws.io.entity.UserEntity;
import com.gavaskar.app.ws.service.UserService;
import com.gavaskar.app.ws.shared.Utils;
import com.gavaskar.app.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Utils utils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) {

        UserEntity userExists = userRepository.findByEmail(user.getEmail());
        if(userExists != null) {
            throw new RuntimeException("Record already exists!!");
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);


        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(utils.generateUserId(30));

        UserEntity savedUser = userRepository.save(userEntity);

        UserDto returnVal = new UserDto();
        BeanUtils.copyProperties(savedUser, returnVal);

        return returnVal;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
