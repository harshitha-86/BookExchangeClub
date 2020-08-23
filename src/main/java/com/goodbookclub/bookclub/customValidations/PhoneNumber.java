package com.goodbookclub.bookclub.customValidations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
	
	//error message
	String message() default "Invalid phone number";
	
	//represents group of constraints
	Class<?>[] groups() default {};
	
	//represents additional information about annotation
	Class<? extends Payload>[] payload() default {};

}
