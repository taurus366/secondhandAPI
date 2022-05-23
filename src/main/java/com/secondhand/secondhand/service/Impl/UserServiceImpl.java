package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.exception.ItemAlreadyExistsException;
import com.secondhand.secondhand.exception.UserAddressesLimitException;
import com.secondhand.secondhand.model.binding.UserAddressBindingModel;
import com.secondhand.secondhand.model.binding.UserChangePersonalDataBindingModel;
import com.secondhand.secondhand.model.dto.SpeedyAddressDTO;
import com.secondhand.secondhand.model.dto.UserInformationDTO;
import com.secondhand.secondhand.model.entity.*;
import com.secondhand.secondhand.model.entity.enums.RoleEnum;
import com.secondhand.secondhand.model.entity.enums.UserSexEnum;
import com.secondhand.secondhand.model.service.SpeedyNewAddressServiceModel;
import com.secondhand.secondhand.model.service.UserChangePasswordServiceModel;
import com.secondhand.secondhand.model.service.UserEditAddressServiceModel;
import com.secondhand.secondhand.model.service.UserRegistrationServiceModel;
import com.secondhand.secondhand.repository.*;
import com.secondhand.secondhand.service.SpeedyCityService;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private AddressEntity changedAddress = null;
    private final SpeedyCityService speedyCityService;
    private final UserSpeedyAddressRepository userSpeedyAddressRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ModelMapper modelMapper, SecondHandUserServiceImpl secondHandUserService, GuestTokenRepository guestTokenRepository, ClothRepository clothRepository, AddressRepository addressRepository, SpeedyCityService speedyCityService, UserSpeedyAddressRepository userSpeedyAddressRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.secondHandUserService = secondHandUserService;
        this.guestTokenRepository = guestTokenRepository;
        this.clothRepository = clothRepository;
        this.addressRepository = addressRepository;
        this.speedyCityService = speedyCityService;
        this.userSpeedyAddressRepository = userSpeedyAddressRepository;
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

//            newAddress = this.addressRepository
//                            .save(newAddress);

            userByEmail
                    .getAddresses()
                    .add(newAddress);

            UserEntity savedUserAddresses = this.userRepository
                    .save(userByEmail);

            List<AddressEntity> addresses = savedUserAddresses
                    .getAddresses();

            newAddress = addresses.get(addresses.size() -1);

        }else {
            throw new UserAddressesLimitException("User can't have more addresses than 3!");
        }
        return newAddress;
//        return null;
    }

    @Override
    public AddressEntity changeExistsAddress(UserEditAddressServiceModel userEditAddressServiceModel) {

        UserEntity userEntity = userRepository
                .findByEmailGraphAddress(userEditAddressServiceModel.getUserAuthEmail()).orElseThrow(() -> new UsernameNotFoundException("We couldn't find the user"));




       userEntity.setAddresses(userEntity.getAddresses()
               .stream()
               .map(addressEntity -> asAddress(addressEntity,userEditAddressServiceModel))
               .collect(Collectors.toList()));

        UserEntity savedUserAddresses = userRepository
                .save(userEntity);

        return savedUserAddresses
                .getAddresses()
                .stream()
                .filter(addressEntity -> Objects.equals(addressEntity.getId(), userEditAddressServiceModel.getId()))
                .collect(Collectors.toList())
                .stream().findFirst().get();
    }

    @Override
    public boolean deleteOneOwnAddress(Long addressId, String userEmail) {

        UserEntity userEntity = this.userRepository
                .findByEmailGraphAddress(userEmail).orElseThrow(() -> new UsernameNotFoundException("We couldn't find user"));

        AddressEntity addressForDelete = null;

        List<AddressEntity> addresses = userEntity.getAddresses();

        for (AddressEntity address : addresses) {
            if (Objects.equals(address.getId(), addressId)){
                userEntity.getAddresses().remove(address);
                addressForDelete = address;
                break;
            }
        }


        UserEntity save = this.userRepository
                .save(userEntity);


        assert addressForDelete != null;
        this.addressRepository
                .deleteById(addressForDelete.getId());

        return save.getId() != null;
    }

    @Override
    public List<SpeedyAddressDTO> addSpeedyOfficeAddressToUser(SpeedyNewAddressServiceModel speedyNewAddressServiceModel) {

        SpeedyCityEntity cityById = speedyCityService
                .getCityByIdTogetherAddresses(speedyNewAddressServiceModel.getCityId());

        SpeedyAddressEntity speedyAddressEntityFromDB = cityById.getSpeedyAddresses()
                .stream()
                .filter(speedyAddressEntity1 -> speedyAddressEntity1.getId() == speedyNewAddressServiceModel
                        .getSpeedyOfficeId())
                .collect(Collectors.toList())
                .get(0);

        UserSpeedyAddressEntity speedyAddressEntity = new UserSpeedyAddressEntity();
        speedyAddressEntity
                .setFirstName(speedyNewAddressServiceModel.getFirstName())
                .setLastName(speedyNewAddressServiceModel.getLastName())
                .setPhoneNumber(speedyNewAddressServiceModel.getPhoneNumber())
                .setCity(cityById)
                .setAddress(speedyAddressEntityFromDB)
                .setCreated(Instant.now())
                .setModified(Instant.now());

        UserEntity byEmail = this.userRepository
                .findByEmailGraphSpeedyAddresses(speedyNewAddressServiceModel.getUserEmail()).orElseThrow( () -> new UsernameNotFoundException("We couldn't find the user wirh email : " + " " + speedyNewAddressServiceModel.getUserEmail()));


        if (byEmail.getSpeedyAddressList().size() >= 3) {

//            TODO -> create new Exception for it !
            throw new UserAddressesLimitException("You can't have more than 3 speedy office addresses!");
        }

       byEmail
               .getSpeedyAddressList()
               .add(speedyAddressEntity);

       this.userRepository
               .save(byEmail);




        return this.userRepository
                .findByEmailGraphSpeedyAddressesCityAddress(speedyNewAddressServiceModel.getUserEmail())
                .orElseThrow()
                .getSpeedyAddressList()
                .stream()
                .map(this::asSpeedyAddressDTO)
                .collect(Collectors.toList());
    }

//    TODO -> complete it !
    private SpeedyAddressDTO asSpeedyAddressDTO(UserSpeedyAddressEntity speedyAddressEntity){

        SpeedyAddressDTO speedyAddressDTO = new SpeedyAddressDTO();

      return speedyAddressDTO
                .setId(speedyAddressEntity.getId())
                .setFirstName(speedyAddressEntity.getFirstName())
              .setLastName(speedyAddressEntity.getLastName())
                .setPhoneNumber(speedyAddressEntity.getPhoneNumber())
                .setCity(speedyAddressEntity.getCity().getName())
                .setAddress(speedyAddressEntity.getAddress().getName());
    }

    //     HERE WHILE I AM TRYING TO RETURN USER INFORMATION I ALSO PUT ADDRESSES INTO INFO
    private UserEntity putAddresses(UserEntity userEntityAddresses,UserEntity userEntity) {
      return userEntity
               .setAddresses(userEntityAddresses.getAddresses());
    }

    private AddressEntity asAddress(AddressEntity addressEntity,UserEditAddressServiceModel userEditAddressServiceModel) {

                    if (Objects.equals(addressEntity.getId(), userEditAddressServiceModel.getId())){
                        this.changedAddress = (AddressEntity) addressEntity
                                .setStreet(userEditAddressServiceModel.getStreet())
                                .setStreetNumber(userEditAddressServiceModel.getStreetNumber())
                                .setApartment(userEditAddressServiceModel.getApartment())
                                .setBlock(userEditAddressServiceModel.getBlock())
                                .setCity(userEditAddressServiceModel.getCity())
                                .setDetailsAboutAddress(userEditAddressServiceModel.getDetailsAboutAddress())
                                .setEntry(userEditAddressServiceModel.getEntry())
                                .setFirstName(userEditAddressServiceModel.getFirstName())
                                .setFloor(userEditAddressServiceModel.getFloor())
                                .setLastName(userEditAddressServiceModel.getLastName())
                                .setMunicipality(userEditAddressServiceModel.getMunicipality())
                                .setNeighborhood(userEditAddressServiceModel.getNeighborhood())
                                .setPhoneNumber(userEditAddressServiceModel.getPhoneNumber())
                                .setZip(userEditAddressServiceModel.getZip())
                                .setCreated(Instant.now())
                                .setModified(Instant.now());

                        return this.changedAddress;
                    }
        return addressEntity;
    }

}
