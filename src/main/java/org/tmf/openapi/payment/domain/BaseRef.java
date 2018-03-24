package org.tmf.openapi.payment.domain;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "id")
@ToString(includeFieldNames = true)
public abstract class BaseRef {

	@NotEmpty
	private String id;

	private String href;

	private String name;

	private String description;

	@JsonProperty("@referredType")
	private String referredType;

}
