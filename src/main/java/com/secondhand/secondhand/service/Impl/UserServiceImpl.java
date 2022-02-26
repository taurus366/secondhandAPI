package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.model.dto.UserInformationDTO;
import com.secondhand.secondhand.model.entity.RoleEntity;
import com.secondhand.secondhand.model.entity.UserEntity;
import com.secondhand.secondhand.model.entity.enums.RoleEnum;
import com.secondhand.secondhand.model.entity.enums.UserSexEnum;
import com.secondhand.secondhand.model.service.UserRegistrationServiceModel;
import com.secondhand.secondhand.repository.RoleRepository;
import com.secondhand.secondhand.repository.UserRepository;
import com.secondhand.secondhand.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final SecondHandUserServiceImpl secondHandUserService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ModelMapper modelMapper, SecondHandUserServiceImpl secondHandUserService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.secondHandUserService = secondHandUserService;
    }

    @Override
    public boolean isEmailExists(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    @Override
    public UserInformationDTO registerNewUserAndLogin(UserRegistrationServiceModel registerServiceModel) {

        RoleEntity userRoleFromDB = roleRepository.findByRole(RoleEnum.USER);

        UserEntity newUser = new UserEntity();
        newUser
                .setFirstName(registerServiceModel.getFirstName())
                .setLastName(registerServiceModel.getLastName())
                .setEmail(registerServiceModel.getEmail())
                .setPassword(passwordEncoder.encode(registerServiceModel.getPassword()))
                .setActive(true)
                .setSex(UserSexEnum.valueOf(registerServiceModel.getSex().name()))
                .getRoles().add(userRoleFromDB);
        newUser
                .setCreated(Instant.now())
                .setModified(Instant.now());

        UserEntity savedUser = userRepository
                .save(newUser);

        UserDetails userDetails = secondHandUserService.loadUserByUsername(newUser.getEmail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                newUser.getPassword(),
                userDetails.getAuthorities()
        );
        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

//        if (savedUser != null){
//
////            UserDetails userDetails =
//
//        }

        return modelMapper.map(savedUser, UserInformationDTO.class);

    }

    @Override
    public Optional<UserEntity> findByLogin(String login) {


        return Optional.empty();
    }

    @Override
    public UserInformationDTO findUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("The user couldn't found"));

        return modelMapper.map(userEntity, UserInformationDTO.class);
    }
}
