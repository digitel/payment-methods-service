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

	private PaymentMethod getExistingPaymentMethod(@NotNull String id) {
		Optional<PaymentMethod> existingPaymentMethodOption = paymentMethodRepository.findById(id);
		if (!existingPaymentMethodOption.isPresent()) {
			throw new IllegalArgumentException("PaymentMethod with id " + id + " doesnot exists");
		}

		PaymentMethod existingPaymentMethod = existingPaymentMethodOption.get();
		return existingPaymentMethod;
	}

}
