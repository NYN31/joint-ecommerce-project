package com.jointproject.ecommerce.be.utility.annotations.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = StrongPasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface StrongPassword {

    String message() default "must contain uppercase, lowercase, and numeric character";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
