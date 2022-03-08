package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.dto.UserInformationDTO;
import com.secondhand.secondhand.model.entity.UserEntity;
import com.secondhand.secondhand.model.service.UserRegistrationServiceModel;

import java.util.Optional;

public interface UserService {

    boolean isEmailExists(String email);
    UserInformationDTO registerNewUserAndLogin(UserRegistrationServiceModel registerServiceModel);
    Optional<UserEntity> findByLogin(String login);
    UserInformationDTO findUserByEmail(String email);
    boolean isAdmin(String userEmail);
}
