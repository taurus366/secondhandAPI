package com.secondhand.secondhand.service;

import com.secondhand.secondhand.model.dto.ClothTypeDTO;
import com.secondhand.secondhand.model.entity.ClothTypeEntity;

import java.util.List;

public interface ClothTypeService {

    Boolean isClothTypeExists(String typeName);
    List<ClothTypeDTO> getAllClothType();
}
