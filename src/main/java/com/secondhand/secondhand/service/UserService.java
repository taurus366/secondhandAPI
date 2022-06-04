package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.binding.UserAddressBindingModel;
import com.secondhand.secondhand.model.binding.UserChangePersonalDataBindingModel;
import com.secondhand.secondhand.model.dto.SpeedyAddressDTO;
import com.secondhand.secondhand.model.dto.UserInformationDTO;
import com.secondhand.secondhand.model.entity.AddressEntity;
import com.secondhand.secondhand.model.entity.UserEntity;
import com.secondhand.secondhand.model.service.SpeedyNewAddressServiceModel;
import com.secondhand.secondhand.model.service.UserChangePasswordServiceModel;
import com.secondhand.secondhand.model.service.UserEditAddressServiceModel;
import com.secondhand.secondhand.model.service.UserRegistrationServiceModel;

public interface UserService {

    UserInformationDTO validateUserIfLoggedAndRoles(String email);
    boolean isEmailExists(String email);
    UserInformationDTO registerNewUserAndLogin(UserRegistrationServiceModel registerServiceModel);
    UserEntity findUserByEmailDefault(String email);
    UserInformationDTO findUserByEmailLoginGraph(String email);
    boolean isAdmin(String userEmail);

    UserEntity userChangePassword(UserChangePasswordServiceModel serviceModel);
    UserEntity userChangeData(UserChangePersonalDataBindingModel userChangePersonalDataBindingModel);

    AddressEntity addNewAddress(UserAddressBindingModel userAddressBindingModel);
    AddressEntity changeExistsAddress(UserEditAddressServiceModel userEditAddressServiceModel);

    boolean deleteOneOwnAddress(Long addressId, String userEmail);

    SpeedyAddressDTO addSpeedyOfficeAddressToUser(SpeedyNewAddressServiceModel speedyNewAddressServiceModel);

    UserInformationDTO userPersonalData(String email);
    UserInformationDTO userAddresses(String email);

//    List<ClothDTO> getUserCart(String userEmail);
//    List<ClothDTO> getGuestCart(String cookie);
//
//    void changeGuestCartToUserCart(String userEmail, Cookie[] guestCookie);
//
//    ClothDTO addItemToUserCart(String userEmail, Long itemId);
//    boolean removeItemFromUserCart(String userEmail, Long itemID);
//
//    ClothDTO addItemToGuestCart(String guestCookie, Long itemId);
//    boolean removeItemFromGuestCart(String guestCookie, Long itemId);
}
