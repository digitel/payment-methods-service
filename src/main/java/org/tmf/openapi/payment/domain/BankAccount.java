package org.tmf.openapi.payment.domain;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BankAccount implements PaymentMethodDetail {

	@NotEmpty
	private String accountNumber;
	@NotEmpty
	private String accountNumberType;

	@JsonProperty("BIC")
	private String bic;
	private String owner;
	private String bank;

}
