package org.tmf.openapi.payment.domain;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class BankCard implements PaymentMethodDetail {

	@NotEmpty
	private String brand;

	@NotEmpty
	private String type;

	@NotEmpty
	private String cardNumber;

	@NotEmpty
	private String expirationDate;

	@NotEmpty
	private String cvv;

	private String lastFourDigit;

	@NotEmpty
	private String nameOnCard;

	@NotEmpty
	private String bank;

}
