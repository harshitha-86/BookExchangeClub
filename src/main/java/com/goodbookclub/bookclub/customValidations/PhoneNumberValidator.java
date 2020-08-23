package com.goodbookclub.bookclub.customValidations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String>{
	
	@Override
    public void initialize(PhoneNumber phoneNumber) {}

	@Override
	public boolean isValid(String phone, ConstraintValidatorContext ctx) {
		return phone != null && phone.matches("[0-9]+") && (phone.length() > 8) && (phone.length() < 14);
	}

}
