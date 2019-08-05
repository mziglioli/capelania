package com.capelania.annotation.validator;

import static io.micrometer.core.instrument.util.StringUtils.isBlank;

import com.capelania.annotation.ValidEmail;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailConstraintValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return isBlank(email) || EmailValidator.validate(email);
    }
}
