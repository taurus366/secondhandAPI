package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.dto.ClothDTO;
import com.secondhand.secondhand.model.dto.UserInformationDTO;
import com.secondhand.secondhand.model.entity.UserEntity;
import com.secondhand.secondhand.model.service.UserRegistrationServiceModel;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean isEmailExists(String email);
    UserInformationDTO registerNewUserAndLogin(UserRegistrationServiceModel registerServiceModel);
    Optional<UserEntity> findByLogin(String login);
    UserInformationDTO findUserByEmail(String email);
    boolean isAdmin(String userEmail);

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
