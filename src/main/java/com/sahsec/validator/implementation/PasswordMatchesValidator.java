package com.sahsec.validator.implementation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sahsec.DTO.UserDto;
import com.sahsec.validator.PasswordMatches;


public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
        //
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserDto user = (UserDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }

}