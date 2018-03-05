package org.tmf.openapi.payment.domain;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Voucher implements PaymentMethodDetail {

	@NotEmpty
	private String code;
	private String description;
	private QuantityType value;
	private Date expirationDate;
	private String campaign;

}
