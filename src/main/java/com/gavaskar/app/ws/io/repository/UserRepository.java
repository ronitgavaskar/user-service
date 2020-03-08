package com.gavaskar.app.ws.io.repository;

import com.gavaskar.app.ws.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//CrudRepository has defined methods, we just pass in Entity object

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findUserByEmail(String email);

}
