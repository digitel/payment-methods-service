package org.tmf.openapi.payment.domain;

import java.net.URI;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Document
@JsonFilter("paymentMethodFilter")
@Data
@EqualsAndHashCode(of = "id")
@ToString(includeFieldNames = true)
public class PaymentMethod {

	@Id
	private String id;

	@NotEmpty
	private String name;

	private String description;

	@Valid
	private TimePeriod validFor;

	// @Valid
	private AccountRef account;

	private Boolean preferred;

	@Valid
	private RelatedPartyRef relatedParty;

	// @JsonProperty("@type")
	@JsonTypeId
	@NotNull
	private PaymentMethodType type;

	private String authorizationCode;

	private String status;

	private Date statusDate;

	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "type")
	@JsonSubTypes({ @JsonSubTypes.Type(value = AccountRef.class, name = PaymentMethodType.Constants.ACCOUNT_REF),
			@JsonSubTypes.Type(value = Check.class, name = PaymentMethodType.Constants.CHECK),
			@JsonSubTypes.Type(value = BankCard.class, name = PaymentMethodType.Constants.BANK_CARD),
			@JsonSubTypes.Type(value = DigitalWallet.class, name = PaymentMethodType.Constants.DIGITAL_WALLET),
			@JsonSubTypes.Type(value = BankAccountDebit.class, name = PaymentMethodType.Constants.BANK_ACCOUNT_DEBIT),
			@JsonSubTypes.Type(value = BankAccountTransfer.class, name = PaymentMethodType.Constants.BANK_ACCOUNT_TRANSFER),
			@JsonSubTypes.Type(value = BankAccount.class, name = PaymentMethodType.Constants.BANK_ACCOUNT),
			@JsonSubTypes.Type(value = Voucher.class, name = PaymentMethodType.Constants.VOUCHER),
			@JsonSubTypes.Type(value = Cash.class, name = PaymentMethodType.Constants.CASH),
			@JsonSubTypes.Type(value = TokenizedCard.class, name = PaymentMethodType.Constants.TOKENIZED_CARD),
			@JsonSubTypes.Type(value = BucketRef.class, name = PaymentMethodType.Constants.BUCKET_REF),
			@JsonSubTypes.Type(value = LoyaltyRef.class, name = PaymentMethodType.Constants.LOYALTY_REF) })

	@Valid
	@NotNull
	private PaymentMethodDetail details;

	@Transient
	private URI href;

}
