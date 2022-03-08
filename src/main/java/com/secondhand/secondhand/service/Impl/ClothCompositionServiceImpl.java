package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.repository.ClothCompositionRepository;
import com.secondhand.secondhand.service.ClothCompositionService;
import org.springframework.stereotype.Service;

@Service
public class ClothCompositionServiceImpl implements ClothCompositionService {

    private final ClothCompositionRepository clothCompositionRepository;

    public ClothCompositionServiceImpl(ClothCompositionRepository clothCompositionRepository) {
        this.clothCompositionRepository = clothCompositionRepository;
    }


    @Override
    public Boolean isClothCompositionExists(String compositionName) {
        return clothCompositionRepository.findByName(compositionName) != null;
    }
}
