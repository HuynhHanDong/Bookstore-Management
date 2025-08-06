package com.example.BookstoreManagement.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsbnValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIsbn {
    String message() default "Invalid ISBN format. ISBN must be 10 or 13 digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}