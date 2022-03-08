package com.secondhand.secondhand.model.validator;

import com.secondhand.secondhand.service.ClothCompositionService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClothCompositionExistsValidator implements ConstraintValidator<ClothCompositionExists,String> {

private final ClothCompositionService clothCompositionService;

    public ClothCompositionExistsValidator(ClothCompositionService clothCompositionService) {
        this.clothCompositionService = clothCompositionService;
    }

    @Override
    public boolean isValid(String compositionName, ConstraintValidatorContext context) {
        return clothCompositionService.isClothCompositionExists(compositionName);
    }
}
