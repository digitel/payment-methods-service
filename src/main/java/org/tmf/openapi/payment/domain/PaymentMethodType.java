package org.tmf.openapi.payment.domain;

public enum PaymentMethodType {

	accountRef(Constants.ACCOUNT_REF), check(Constants.CHECK), bankCard(Constants.BANK_CARD), digitalWallet(
			Constants.DIGITAL_WALLET), bankAccountDebit(Constants.BANK_ACCOUNT_DEBIT), bankAccountTransfer(
					Constants.BANK_ACCOUNT_TRANSFER), bankAccount(Constants.BANK_ACCOUNT), voucher(
							Constants.VOUCHER), cash(Constants.CASH), tokenizedCard(
									Constants.TOKENIZED_CARD), loyaltyRef(Constants.LOYALTY_REF), bucketRef(Constants.BUCKET_REF);

	PaymentMethodType(String paymentMethod) {
		//TODO what this string do ?
	}

	public static class Constants {
		public static final String ACCOUNT_REF = "accountRef";
		public static final String CHECK = "check";

		public static final String BANK_CARD = "bankCard";
		public static final String DIGITAL_WALLET = "digitalWallet";
		public static final String BANK_ACCOUNT_DEBIT = "bankAccountDebit";
		public static final String BANK_ACCOUNT_TRANSFER = "bankAccountTransfer";
		public static final String BANK_ACCOUNT = "bankAccount";
		public static final String VOUCHER = "voucher";
		public static final String CASH = "cash";
		public static final String TOKENIZED_CARD = "tokenizedCard";
		public static final String LOYALTY_REF = "loyaltyRef";
		public static final String BUCKET_REF = "bucketRef";
		

	}

}
