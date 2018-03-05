package org.tmf.openapi.payment.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoyaltyRef extends BaseRef implements PaymentMethodDetail {

}
