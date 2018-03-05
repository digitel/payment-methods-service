package org.tmf.openapi.payment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.tmf.openapi.payment.domain.PaymentMethod;

@Repository
public interface PaymentMethodRepository
		extends MongoRepository<PaymentMethod, String>, QuerydslPredicateExecutor<PaymentMethod> {

	List<PaymentMethod> findByAccount_Id(String accountId);

}
