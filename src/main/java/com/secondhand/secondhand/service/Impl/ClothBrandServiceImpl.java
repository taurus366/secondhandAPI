package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.model.dto.ClothBrandDTO;
import com.secondhand.secondhand.model.entity.ClothBrandEntity;
import com.secondhand.secondhand.repository.ClothBrandRepository;
import com.secondhand.secondhand.service.ClothBrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClothBrandServiceImpl implements ClothBrandService {

    private final ClothBrandRepository clothBrandRepository;
    private final ModelMapper modelMapper;

    public ClothBrandServiceImpl(ClothBrandRepository clothBrandRepository, ModelMapper modelMapper) {
        this.clothBrandRepository = clothBrandRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean isBrandExists(String brandName) {
        return clothBrandRepository.findByName(brandName) != null;
    }

    @Override
    public List<ClothBrandDTO> getAllClothBrand() {

        return this.clothBrandRepository.findAll()
                .stream()
                .map(this::getAsClothBrandDTO)
                .collect(Collectors.toList());
    }

    private ClothBrandDTO getAsClothBrandDTO(ClothBrandEntity clothBrandEntity){
        return modelMapper.map(clothBrandEntity,ClothBrandDTO.class);
    }
}
