package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.dto.ClothDTO;
import com.secondhand.secondhand.model.service.ClothCreateServiceModel;
import com.secondhand.secondhand.service.Impl.SecondHandUser;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ClothService {

    ClothCreateServiceModel createNewCloth(ClothCreateServiceModel clothServiceModel) throws IOException;

//    Page<ClothDTO> getClothes(int pageNo,int pageSize, String sortBy);

    Page<ClothDTO> getAllClothes(int pageNo, int pageSize, String brand, String size, Long discount, String color, Long priceLow, Long priceHigh, String sex, List<String> type, String itemType, String sortBy);

    ClothDTO getClothByID(Long id);

//    ClothDTO likeOrUnlikeCloth(Long itemId, )

//    List<ClothDTO> getUserCart(Long userId);
//    List<ClothDTO> getGuestCart(String cookie);
//
//    void changeGuestClothToUserCloth(HttpServletRequest request, HttpServletResponse respons,Long userId);
}
