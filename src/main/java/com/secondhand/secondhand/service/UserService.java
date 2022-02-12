package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.dto.UserRegistrationDTO;
import com.secondhand.secondhand.model.service.UserRegistrationServiceModel;

public interface UserService {

    boolean isEmailExists(String email);
    UserRegistrationDTO registerNewUser(UserRegistrationServiceModel registerServiceModel);
}
