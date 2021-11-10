package com.subham.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<com.subham.validator.ValidEmail, String>{

	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
	
	@Override
	public void initialize(com.subham.validator.ValidEmail constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return validateEmail(value);
	}
	
	public boolean validateEmail(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(email);
		
		
		
		return matcher.matches();
	}

}
