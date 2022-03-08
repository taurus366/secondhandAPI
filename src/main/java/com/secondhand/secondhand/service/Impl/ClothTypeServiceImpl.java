package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.repository.ClothTypeRepository;
import com.secondhand.secondhand.service.ClothTypeService;
import org.springframework.stereotype.Service;

@Service
public class ClothTypeServiceImpl implements ClothTypeService {

    private final ClothTypeRepository clothTypeRepository;

    public ClothTypeServiceImpl(ClothTypeRepository clothTypeRepository) {
        this.clothTypeRepository = clothTypeRepository;
    }


    @Override
    public Boolean isClothTypeExists(String typeName) {
        return clothTypeRepository.findByName(typeName) != null;
    }
}
