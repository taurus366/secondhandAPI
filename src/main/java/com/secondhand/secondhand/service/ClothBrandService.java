package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.dto.ClothBrandDTO;

import java.util.List;

public interface ClothBrandService {
   boolean isBrandExists(String brandName);
   List<ClothBrandDTO> getAllClothBrand();
}
