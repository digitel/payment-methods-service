package org.tmf.openapi.payment.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.tmf.openapi.payment.common.ObjectMapper.mapObjectWithExcludeFilter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.tmf.openapi.payment.domain.PaymentMethod;
import org.tmf.openapi.payment.service.PaymentMethodService;

import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/paymentMethods/v1")
public class PaymentMethodController {

	@Autowired
	PaymentMethodService paymentMethodService;

	@PostMapping(value = "/paymentMethod", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> createPaymentMethod(@RequestBody @Valid PaymentMethod paymentMethod,
			UriComponentsBuilder b) throws URISyntaxException {

		paymentMethod = paymentMethodService.createPaymentMethod(paymentMethod);

		return ResponseEntity.created(new URI("/paymentMethod/" + paymentMethod.getId()))
				.body(mapObjectWithExcludeFilter(populateHref(paymentMethod), null));
	}

	@DeleteMapping(value = "/paymentMethod/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> deletePaymentMethod(@PathVariable String id) {
		paymentMethodService.deletePaymentMethod(id);
		return ResponseEntity.noContent().build();

	}

	@GetMapping(value = "/paymentMethod/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> getAllPaymentMethods(@PathVariable String id,
			@RequestParam MultiValueMap<String, String> requestParams) {
		return ResponseEntity.ok(
				mapObjectWithExcludeFilter(populateHref(paymentMethodService.findPaymentMethod(id)), requestParams));

	}

	@GetMapping(value = "/paymentMethod", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> getPaymentMethod(
			@QuerydslPredicate(root = PaymentMethod.class) Predicate predicate,
			@RequestParam MultiValueMap<String, String> requestParams) {
		return ResponseEntity.ok(mapObjectWithExcludeFilter(
				populateHref(paymentMethodService.findAllPaymentMethods(predicate)), requestParams));

	}

	@GetMapping(value = "/account/{accountId}/paymentMethod", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> getPaymentMethodByAccountId(@PathVariable String accountId,
			@RequestParam MultiValueMap<String, String> requestParams) {
		return ResponseEntity.ok(mapObjectWithExcludeFilter(
				populateHref(paymentMethodService.findPaymentMethodByAccountId(accountId)), requestParams));

	}

	@PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> patchPaymentMethod(@PathVariable String id,
			@RequestBody PaymentMethod paymentMethod) {

		validatePaymentMethod(id, paymentMethod);
		return ResponseEntity.ok(mapObjectWithExcludeFilter(
				populateHref(paymentMethodService.partialUpdatePaymentMethod(paymentMethod)), null));

	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> updatePaymentMethod(@PathVariable String id,
			@RequestBody PaymentMethod paymentMethod) {

		validatePaymentMethod(id, paymentMethod);
		return ResponseEntity.ok(mapObjectWithExcludeFilter(
				populateHref(paymentMethodService.updatePaymentMethod(paymentMethod)), null));

	}

	private void validatePaymentMethod(String id, PaymentMethod paymentMethod) {
		if ((null == paymentMethod.getId()) || (null != paymentMethod.getId() && !paymentMethod.getId().equals(id))) {
			throw new IllegalArgumentException("id cannot be updated.");
		}
	}

	private PaymentMethod populateHref(PaymentMethod paymentMethod) {
		paymentMethod.setHref(
				linkTo(PaymentMethodController.class).slash("paymentMethod").slash(paymentMethod.getId()).toUri());
		return paymentMethod;
	}

	private List<PaymentMethod> populateHref(List<PaymentMethod> paymentMethods) {
		for (PaymentMethod paymentMethod : paymentMethods) {
			populateHref(paymentMethod);
		}
		return paymentMethods;
	}

}
