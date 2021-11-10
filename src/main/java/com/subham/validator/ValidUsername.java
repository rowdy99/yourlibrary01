package com.subham.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = com.subham.validator.UsernameValidator.class)
public @interface ValidUsername {

	String message() default "Invalid username.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
