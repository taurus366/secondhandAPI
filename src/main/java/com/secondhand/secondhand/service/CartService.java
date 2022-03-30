package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.dto.ClothDTO;

import javax.servlet.http.Cookie;
import java.util.List;

public interface CartService {

    List<ClothDTO> getUserCart(String userEmail);
    List<ClothDTO> getGuestCart(String cookie);

    void changeGuestCartToUserCart(String userEmail, Cookie[] guestCookie);

    ClothDTO addItemToUserCart(String userEmail, Long itemId);
    boolean removeItemFromUserCart(String userEmail, Long itemID);

    ClothDTO addItemToGuestCart(String guestCookie, Long itemId);
    boolean removeItemFromGuestCart(String guestCookie, Long itemId);
}
