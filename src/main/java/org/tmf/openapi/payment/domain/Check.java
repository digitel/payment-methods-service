package org.tmf.openapi.payment.domain;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class Check implements PaymentMethodDetail {

	@NotEmpty
	private String checkId;
	@NotEmpty
	private String drawer;
	@NotEmpty
	private String payee;
	@NotEmpty
	private String bank;

	private String date;

}
