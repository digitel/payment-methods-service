package org.tmf.openapi.payment.domain;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class DigitalWallet {

	@NotEmpty
	private String service;
	@NotEmpty
	private String walletId;
	private String walletUrl;

}
