package org.tmf.openapi.payment.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { TimePeriodValidator.class })

public @interface ValidTimePeriod {

	String message() default "{org.tmf.openapi.payment.validation.date.timeperiod}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
