package com.gavaskar.app.ws.service.impl;

import com.gavaskar.app.ws.exceptions.UserServiceException;
import com.gavaskar.app.ws.io.repository.UserRepository;
import com.gavaskar.app.ws.io.entity.UserEntity;
import com.gavaskar.app.ws.service.UserService;
import com.gavaskar.app.ws.shared.Utils;
import com.gavaskar.app.ws.shared.dto.UserDto;
import com.gavaskar.app.ws.ui.model.response.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Utils utils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) throws Exception {

        UserEntity userExists = userRepository.findByEmail(user.getEmail());
        if(userExists != null) {
            throw new Exception(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
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
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);

        if (userEntity == null) throw new UsernameNotFoundException("User not found");

        UserDto returnVal = new UserDto();
        BeanUtils.copyProperties(userEntity, returnVal);

        return returnVal;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) throw new UsernameNotFoundException("User not found!");

        UserDto returnVal = new UserDto();
        BeanUtils.copyProperties(userEntity, returnVal);

        return returnVal;
    }

    @Override
    public UserDto updateUser(UserDto user, String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) {
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }

        UserDto returnVal = new UserDto();

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        UserEntity updatedDetails = userRepository.save(userEntity);
        BeanUtils.copyProperties(updatedDetails, returnVal);

        return returnVal;
    }

    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) {
            throw new UserServiceException("Record with ID " + userId + " not found.");
        }

        userRepository.delete(userEntity);
    }

    @Override
    public ArrayList<UserDto> getAllUsers(int page, int limit) {
        if (page > 0) {
            page -= 1;
        }

        PageRequest pageableReq = PageRequest.of(page, limit);

        Page<UserEntity> allUsersPage = userRepository.findAll(pageableReq);
        List<UserEntity> users = allUsersPage.getContent();

        ArrayList<UserDto> returnVal = new ArrayList<>();
        for (UserEntity userEntity: users) {
            UserDto user = new UserDto();
            BeanUtils.copyProperties(userEntity, user);
            returnVal.add(user);
        }

        return returnVal;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // username is email
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new RuntimeException("Username not found!");
        else {
            // User is from Spring framework and takes in email, password and a collection
            // user is found
            return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
        }
    }
}
