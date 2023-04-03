package com.digital.bank.transactions.util.interfaces;

import jakarta.validation.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.time.OffsetDateTime;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { OffsetDateTimeValidator.class })
@Documented
public @interface ValidOffsetDateTime {
    String message() default "Invalid offset date time";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
 class OffsetDateTimeValidator implements ConstraintValidator<ValidOffsetDateTime, OffsetDateTime> {
     private static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(.\\d{1,3})?Z$";
     private static final Pattern pattern = Pattern.compile(DATE_PATTERN);

     @Override
    public void initialize(ValidOffsetDateTime constraintAnnotation) {}

    @Override
    public boolean isValid(OffsetDateTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if(value.isAfter(OffsetDateTime.now())){
           return false;
        }
        String dateString = DateTimeFormatter.ISO_DATE_TIME.format(value);
        Matcher matcher = pattern.matcher(dateString);
        return matcher.matches();
    }
}