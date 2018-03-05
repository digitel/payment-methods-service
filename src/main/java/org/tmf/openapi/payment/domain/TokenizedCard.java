package org.tmf.openapi.payment.domain;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class TokenizedCard implements PaymentMethodDetail {

	private String brand;
	private String type;
	private String lastFourDigits;
	private String tokenType;
	@NotEmpty
	private String token;
	private String issuer;

}
