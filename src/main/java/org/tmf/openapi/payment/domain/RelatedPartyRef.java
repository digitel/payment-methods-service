package org.tmf.openapi.payment.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RelatedPartyRef extends BaseRef {

	private String role;
	
	private TimePeriod validFor;

}
