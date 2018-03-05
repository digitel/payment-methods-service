package org.tmf.openapi.payment.domain;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "id")
@ToString(includeFieldNames = true)
public abstract class BaseRef {

	@NotEmpty
	private String id;

	@NotEmpty
	private String href;

	private String name;

	private String description;

}
