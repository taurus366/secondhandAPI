package com.secondhand.secondhand.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberExists,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        String s = value.length() == 9 ? "0" + value : value;
        Pattern p = Pattern.compile("^(359|0)[0-9]{9}");
        Matcher m = p.matcher(s);

        return m.matches();
    }
}
