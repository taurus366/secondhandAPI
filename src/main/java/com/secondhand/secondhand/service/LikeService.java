package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.dto.ClothDTO;

import java.util.List;

public interface LikeService {

    ClothDTO giveUserLikeToCloth(String userEmail,Long itemId);
    ClothDTO giveGuestLikeToCloth(String guestCookie, Long itemId);

    ClothDTO deleteUserLikeFromCloth(String userEmail, Long itemId);
    ClothDTO deleteGuestLikeFromCloth(String guestCookie, Long itemId);

    List<ClothDTO> getUserLikes(String userEmail);
    List<ClothDTO> getGuestLikes(String guestCookie);
}
