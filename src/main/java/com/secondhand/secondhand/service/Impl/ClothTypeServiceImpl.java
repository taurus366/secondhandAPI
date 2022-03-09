package com.secondhand.secondhand.service.Impl;

import com.secondhand.secondhand.model.dto.ClothTypeDTO;
import com.secondhand.secondhand.model.entity.ClothTypeEntity;
import com.secondhand.secondhand.repository.ClothTypeRepository;
import com.secondhand.secondhand.service.ClothTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClothTypeServiceImpl implements ClothTypeService {

    private final ClothTypeRepository clothTypeRepository;
    private final ModelMapper modelMapper;

    public ClothTypeServiceImpl(ClothTypeRepository clothTypeRepository, ModelMapper modelMapper) {
        this.clothTypeRepository = clothTypeRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Boolean isClothTypeExists(String typeName) {
        return clothTypeRepository.findByName(typeName) != null;
    }

    @Override
    public List<ClothTypeDTO> getAllClothType() {

        return clothTypeRepository.findAll()
                .stream()
                .map(this::getAsClothTypeDTO)
                .collect(Collectors.toList());
    }

    private ClothTypeDTO getAsClothTypeDTO(ClothTypeEntity clothType) {
        return modelMapper.map(clothType,ClothTypeDTO.class);
    }
}
