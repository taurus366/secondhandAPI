package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.exception.UserAddressesLimitException;
import com.secondhand.secondhand.model.binding.UserAddressBindingModel;
import com.secondhand.secondhand.model.binding.UserChangePersonalDataBindingModel;
import com.secondhand.secondhand.model.dto.UserInformationDTO;
import com.secondhand.secondhand.model.entity.*;
import com.secondhand.secondhand.model.entity.enums.RoleEnum;
import com.secondhand.secondhand.model.entity.enums.UserSexEnum;
import com.secondhand.secondhand.model.service.UserChangePasswordServiceModel;
import com.secondhand.secondhand.model.service.UserRegistrationServiceModel;
import com.secondhand.secondhand.repository.*;
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
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final SecondHandUserServiceImpl secondHandUserService;
    private final GuestTokenRepository guestTokenRepository;
    private final ClothRepository clothRepository;
    private final AddressRepository addressRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ModelMapper modelMapper, SecondHandUserServiceImpl secondHandUserService, GuestTokenRepository guestTokenRepository, ClothRepository clothRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.secondHandUserService = secondHandUserService;
        this.guestTokenRepository = guestTokenRepository;
        this.clothRepository = clothRepository;
        this.addressRepository = addressRepository;
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
    public UserEntity findUserByEmail(String email) {

//        return modelMapper.map(userEntity, UserInformationDTO.class);
        UserEntity userEntityAddresses = userRepository.findByEmailGraphAddress(email).orElseThrow( () -> new UsernameNotFoundException("The user couldn't found"));


        UserEntity userEntity = userRepository.findByEmailGraph(email).orElseThrow(() -> new UsernameNotFoundException("The user couldn't found"));
        return this.putAddresses(userEntityAddresses,userEntity);
    }

    @Override
    public boolean isAdmin(String userEmail) {

        return this.userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException(userEmail + "doesn't exists in database"))
                .getRoles()
                .stream()
                .map(RoleEntity::getRole)
                .anyMatch(roleEnum -> roleEnum.equals(RoleEnum.ADMINISTRATOR));
    }

    @Override
    public UserEntity userChangePassword(UserChangePasswordServiceModel serviceModel) {

        UserEntity userEntity = this.userRepository
                .findByEmailGraph(serviceModel.getUserEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Couldn't find the user !"));

        userEntity
                .setPassword(passwordEncoder.encode(serviceModel.getNewPassword()));

       return this.userRepository
                .save(userEntity);
    }

    @Override
    public UserEntity userChangeData(UserChangePersonalDataBindingModel userChangePersonalDataBindingModel) {

        UserEntity userEntity= this.userRepository
                .findByEmailGraph(userChangePersonalDataBindingModel.getUserEmail()).orElseThrow(() -> new UsernameNotFoundException("Couldn't find the user!"));

      if (userChangePersonalDataBindingModel.getFirstName() != null && userChangePersonalDataBindingModel.getFirstName().length() > 0){
          userEntity.setFirstName(userChangePersonalDataBindingModel.getFirstName());
      }
      if (userChangePersonalDataBindingModel.getPhoneNumber() != null && userChangePersonalDataBindingModel.getPhoneNumber().length() > 0) {
          userEntity.setPhoneNumber(userChangePersonalDataBindingModel.getPhoneNumber());
      }
      if (userChangePersonalDataBindingModel.getLastName() != null && userChangePersonalDataBindingModel.getLastName().length() > 0){
          userEntity.setLastName(userChangePersonalDataBindingModel.getLastName());
      }

        return this.userRepository
                .save(userEntity);
    }

    @Override
    public AddressEntity addNewAddress(UserAddressBindingModel userAddressBindingModel) {

        UserEntity userByEmail = this.userRepository
                .findByEmailGraphAddress(userAddressBindingModel.getUserEmail()).orElseThrow( () -> new UsernameNotFoundException("Couldn't find user!"));


        AddressEntity newAddress = new AddressEntity();;

        if (userByEmail.getAddresses().size() < 3) {

            newAddress
                    .setStreet(userAddressBindingModel.getStreet())
                    .setStreetNumber(userAddressBindingModel.getStreetNumber())
                    .setApartment(userAddressBindingModel.getApartment())
                    .setBlock(userAddressBindingModel.getBlock())
                    .setCity(userAddressBindingModel.getCity())
                    .setDetailsAboutAddress(userAddressBindingModel.getDetailsAboutAddress())
                    .setEntry(userAddressBindingModel.getEntry())
                    .setFirstName(userAddressBindingModel.getFirstName())
                    .setFloor(userAddressBindingModel.getFloor())
                    .setLastName(userAddressBindingModel.getLastName())
                    .setMunicipality(userAddressBindingModel.getMunicipality())
                    .setNeighborhood(userAddressBindingModel.getNeighborhood())
                    .setPhoneNumber(userAddressBindingModel.getPhoneNumber())
                    .setZip(userAddressBindingModel.getZip())
                    .setCreated(Instant.now())
                    .setModified(Instant.now());

            userByEmail
                    .getAddresses()
                    .add(newAddress);
//
            this.userRepository
                    .save(userByEmail);


        }else {
            throw new UserAddressesLimitException("User can't have more addresses than 3!");
        }
        return newAddress;
//        return null;
    }

    private UserEntity putAddresses(UserEntity userEntityAddresses,UserEntity userEntity) {
      return userEntity
               .setAddresses(userEntityAddresses.getAddresses());
    }

}
