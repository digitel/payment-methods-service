package org.tmf.openapi.payment.service;

import static org.tmf.openapi.payment.common.ListUtils.toList;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmf.openapi.payment.domain.PaymentMethod;
import org.tmf.openapi.payment.repository.PaymentMethodRepository;

import com.querydsl.core.types.Predicate;

@Service
public class PaymentMethodService {

	@Autowired
	private PaymentMethodRepository paymentMethodRepository;

	public @Valid PaymentMethod createPaymentMethod(@Valid PaymentMethod paymentMethod) {
		return paymentMethodRepository.save(paymentMethod);
	}

	public PaymentMethod findPaymentMethod(@NotNull String id) {
		return paymentMethodRepository.findById(id).get();
	}

	public List<PaymentMethod> findAllPaymentMethods(Predicate predicate) {
		return toList(paymentMethodRepository.findAll(predicate));
	}

	public List<PaymentMethod> findPaymentMethodByAccountId(String accountId) {
		return paymentMethodRepository.findByAccount_Id(accountId);
	}

	public void deletePaymentMethod(@NotNull String id) {
		PaymentMethod existingPaymentMethod = getExistingPaymentMethod(id);
		paymentMethodRepository.delete(existingPaymentMethod);

	}

	public PaymentMethod updatePaymentMethod(@Valid PaymentMethod paymentMethod) {

		return paymentMethodRepository.save(paymentMethod);
	}

	public PaymentMethod partialUpdatePaymentMethod(PaymentMethod paymentMethod) {

		if (null == paymentMethod.getId()) {
			throw new IllegalArgumentException("id is mandatory field for update.");
		}

		PaymentMethod existingPaymentMethod = getExistingPaymentMethod(paymentMethod.getId());

		if (null != paymentMethod.getName()) {
			existingPaymentMethod.setName(paymentMethod.getName());
		}
		if (null != paymentMethod.getDescription()) {
			existingPaymentMethod.setDescription(paymentMethod.getDescription());
		}
		if (null != paymentMethod.getValidFor()) {
			existingPaymentMethod.setValidFor(paymentMethod.getValidFor());
		}

		if (null != paymentMethod.getAccount()) {
			existingPaymentMethod.setAccount(paymentMethod.getAccount());
		}

		if (null != paymentMethod.getPreferred()) {
			existingPaymentMethod.setPreferred(paymentMethod.getPreferred());
		}

		if (null != paymentMethod.getRelatedParty()) {
			existingPaymentMethod.setRelatedParty(paymentMethod.getRelatedParty());
		}

		if (null != paymentMethod.getType()) {
			existingPaymentMethod.setType(paymentMethod.getType());
		}
		if (null != paymentMethod.getAuthorizationCode()) {
			existingPaymentMethod.setAuthorizationCode(paymentMethod.getAuthorizationCode());
		}
		if (null != paymentMethod.getStatus()) {
			existingPaymentMethod.setStatus(paymentMethod.getStatus());
		}
		if (null != paymentMethod.getStatusDate()) {
			existingPaymentMethod.setStatusDate(paymentMethod.getStatusDate());
		}

		return paymentMethodRepository.save(existingPaymentMethod);
	}

	private PaymentMethod getExistingPaymentMethod(@NotNull String id) {
		Optional<PaymentMethod> existingPaymentMethodOption = paymentMethodRepository.findById(id);
		if (!existingPaymentMethodOption.isPresent()) {
			throw new IllegalArgumentException("PaymentMethod with id " + id + " doesnot exists");
		}

		PaymentMethod existingPaymentMethod = existingPaymentMethodOption.get();
		return existingPaymentMethod;
	}

}
