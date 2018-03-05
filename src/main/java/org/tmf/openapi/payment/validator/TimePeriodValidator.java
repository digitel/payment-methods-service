package org.tmf.openapi.payment.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.tmf.openapi.payment.domain.TimePeriod;

public class TimePeriodValidator implements ConstraintValidator<ValidTimePeriod, TimePeriod> {

	@Override
	public boolean isValid(TimePeriod timePeriod, ConstraintValidatorContext context) {

		if (null == timePeriod.getStartDateTime()) {
			return false;
		}
		if (null == timePeriod.getEndDateTime()) {
			return true;
		}

		return timePeriod.getStartDateTime().before(timePeriod.getEndDateTime());
	}

}