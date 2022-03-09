package com.secondhand.secondhand.service.Impl;
import com.secondhand.secondhand.model.dto.ClothCompositionDTO;
import com.secondhand.secondhand.model.entity.ClothCompositionEntity;
import com.secondhand.secondhand.repository.ClothCompositionRepository;
import com.secondhand.secondhand.service.ClothCompositionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClothCompositionServiceImpl implements ClothCompositionService {

    private final ClothCompositionRepository clothCompositionRepository;
    private final ModelMapper modelMapper;

    public ClothCompositionServiceImpl(ClothCompositionRepository clothCompositionRepository, ModelMapper modelMapper) {
        this.clothCompositionRepository = clothCompositionRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Boolean isClothCompositionExists(String compositionName) {
        return clothCompositionRepository.findByName(compositionName) != null;
    }

    @Override
    public List<ClothCompositionDTO> getAllClothComposition() {
        return this.clothCompositionRepository
                .findAll()
                .stream()
                .map(this::getAsClothCompositionDTO)
                .collect(Collectors.toList());
    }

    private ClothCompositionDTO getAsClothCompositionDTO(ClothCompositionEntity clothComposition){
        return modelMapper.map(clothComposition,ClothCompositionDTO.class);
    }
}
