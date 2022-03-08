package com.secondhand.secondhand.model.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ClothTypeExistsValidator.class)
public @interface ClothTypeExists {

    String message() default "Type doesn't exists";

    Class<?>[]groups() default {};

    Class<? extends Payload>[] payload() default {};


}
