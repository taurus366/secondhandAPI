package com.secondhand.secondhand.model.validator;

import com.secondhand.secondhand.service.ClothBrandService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClothBrandExistsValidator implements ConstraintValidator<ClothBrandExists, String> {

    private final ClothBrandService clothBrandService;

    public ClothBrandExistsValidator(ClothBrandService clothBrandService) {
        this.clothBrandService = clothBrandService;
    }

    @Override
    public boolean isValid(String brandName, ConstraintValidatorContext context) {
        return clothBrandService.isBrandExists(brandName);
    }
}
