package org.tmf.openapi.payment.domain;

import lombok.Data;

@Data
public class Cash implements PaymentMethodDetail {

	private String cashierInfo;

}
