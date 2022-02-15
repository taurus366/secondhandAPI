package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.dto.UserRegistrationDTO;
import com.secondhand.secondhand.model.entity.UserEntity;
import com.secondhand.secondhand.model.service.UserRegistrationServiceModel;

import java.util.Optional;

public interface UserService {

    boolean isEmailExists(String email);
    UserRegistrationDTO registerNewUser(UserRegistrationServiceModel registerServiceModel);
    Optional<UserEntity> findByLogin(String login);
}
