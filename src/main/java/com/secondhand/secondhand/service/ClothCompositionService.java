package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.dto.ClothCompositionDTO;

import java.util.List;

public interface ClothCompositionService {

    Boolean isClothCompositionExists(String compositionName);
    List<ClothCompositionDTO> getAllClothComposition();
}
