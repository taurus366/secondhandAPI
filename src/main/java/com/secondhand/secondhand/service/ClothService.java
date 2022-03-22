package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.dto.ClothDTO;
import com.secondhand.secondhand.model.service.ClothCreateServiceModel;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface ClothService {

    ClothCreateServiceModel createNewCloth(ClothCreateServiceModel clothServiceModel) throws IOException;

//    Page<ClothDTO> getClothes(int pageNo,int pageSize, String sortBy);

    Page<ClothDTO> getAllClothes(int pageNo, int pageSize, String brand, String size, Long discount, String color, Long priceLow, Long priceHigh, String sex, List<String> type, String itemType, String sortBy);

    ClothDTO getClothByID(Long id);
}
