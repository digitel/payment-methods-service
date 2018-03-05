package org.tmf.openapi.payment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RelatedPartyRef extends BaseRef {


	@JsonProperty("@referredType")
	private String referredType;
	
	private String role;

}
