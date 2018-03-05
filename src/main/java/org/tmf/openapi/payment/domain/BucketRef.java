package org.tmf.openapi.payment.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BucketRef extends BaseRef implements PaymentMethodDetail {

}
