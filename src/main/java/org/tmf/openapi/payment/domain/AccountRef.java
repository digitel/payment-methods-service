package org.tmf.openapi.payment.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountRef extends BaseRef implements PaymentMethodDetail {

}
