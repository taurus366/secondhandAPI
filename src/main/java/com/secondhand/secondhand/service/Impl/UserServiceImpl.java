package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.model.dto.UserRegistrationDTO;
import com.secondhand.secondhand.model.entity.RoleEntity;
import com.secondhand.secondhand.model.entity.UserEntity;
import com.secondhand.secondhand.model.entity.enums.RoleEnum;
import com.secondhand.secondhand.model.entity.enums.SexEnum;
import com.secondhand.secondhand.model.service.UserRegistrationServiceModel;
import com.secondhand.secondhand.repository.RoleRepository;
import com.secondhand.secondhand.repository.UserRepository;
import com.secondhand.secondhand.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    @Override
    public UserRegistrationDTO registerNewUser(UserRegistrationServiceModel registerServiceModel) {

        RoleEntity userRoleFromDB = roleRepository.findByRole(RoleEnum.USER);

        UserEntity newUser = new UserEntity();
        newUser
                .setFirstName(registerServiceModel.getFirstName())
                .setLastName(registerServiceModel.getLastName())
                .setEmail(registerServiceModel.getEmail())
                .setPassword(passwordEncoder.encode(registerServiceModel.getPassword()))
                .setActive(true)
                .setSex(SexEnum.valueOf(registerServiceModel.getSex().name()))
                .getRoles().add(userRoleFromDB);
        newUser
                .setCreated(Instant.now())
                .setModified(Instant.now());

        UserEntity savedUser = userRepository
                .save(newUser);

//        if (savedUser != null){
//
////            UserDetails userDetails =
//
//        }

        return modelMapper.map(savedUser, UserRegistrationDTO.class);

    }
}
