package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.repository.ClothBrandRepository;
import com.secondhand.secondhand.service.ClothBrandService;
import org.springframework.stereotype.Service;

@Service
public class ClothBrandServiceImpl implements ClothBrandService {

    private final ClothBrandRepository clothBrandRepository;

    public ClothBrandServiceImpl(ClothBrandRepository clothBrandRepository) {
        this.clothBrandRepository = clothBrandRepository;
    }


    @Override
    public boolean isBrandExists(String brandName) {
        return clothBrandRepository.findByName(brandName) != null;
    }
}
