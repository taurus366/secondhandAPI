package com.secondhand.secondhand.model.validator;

import com.secondhand.secondhand.service.ClothTypeService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClothTypeExistsValidator implements ConstraintValidator<ClothTypeExists,String> {


        private final ClothTypeService clothTypeService;

    public ClothTypeExistsValidator(ClothTypeService clothTypeService) {
        this.clothTypeService = clothTypeService;
    }

    @Override
    public boolean isValid(String typeName, ConstraintValidatorContext context) {
        return clothTypeService.isClothTypeExists(typeName);
    }
}
