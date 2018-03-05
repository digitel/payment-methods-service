package org.tmf.openapi.payment.common;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Customize the response for MethodArgumentNotValidException.
	 * <p>
	 * This method delegates to {@link #handleExceptionInternal}.
	 * 
	 * @param ex
	 *            the exception
	 * @param headers
	 *            the headers to be written to the response
	 * @param status
	 *            the selected response status
	 * @param request
	 *            the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		BindingResult result = ex.getBindingResult();
		String message = "";
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
		for (org.springframework.validation.FieldError fieldError : fieldErrors) {
			message = message +  " Field " +  fieldError.getField() + " -> " + fieldError.getDefaultMessage() + " ";
		}
		CustomExceptionResponse exceptionResponse = createResponse(ex, request, message);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Customize the response for HttpMessageNotReadableException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param ex the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {


		CustomExceptionResponse exceptionResponse = createResponse(ex, request, null);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Provides handling for Bad Requests exceptions.
	 * 
	 * @param ex
	 *            the target exception
	 * @param request
	 *            the current request
	 */
	@ExceptionHandler({ ConstraintViolationException.class, IllegalArgumentException.class })
	@Nullable
	public final ResponseEntity<CustomExceptionResponse> handleConstraintViolationException(Exception ex,
			WebRequest request) {

		CustomExceptionResponse exceptionResponse = createResponse(ex, request, null);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);

	}

	private CustomExceptionResponse createResponse(Exception ex, WebRequest request, String message) {
		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse();

		Throwable cause = ExceptionUtils.getRootCause(ex);
		if (null == cause) {
			cause = ex;
		}

		exceptionResponse.setTimestamp(new Date());
		exceptionResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
		exceptionResponse.setError("Bad Request");
		if (null != message) {
			exceptionResponse.setMessage(message);
		} else {
			exceptionResponse.setMessage(cause.getMessage());
		}

		exceptionResponse.setPath(request.getDescription(false));
		return exceptionResponse;
	}

	/**
	 * Provides handling for Bad Requests exceptions.
	 * 
	 * @param ex
	 *            the target exception
	 * @param request
	 *            the current request
	 */
	@ExceptionHandler({ NoSuchElementException.class })
	@Nullable
	public final ResponseEntity<CustomExceptionResponse> handleNoSuchElementException(Exception ex,
			WebRequest request) {

		CustomExceptionResponse exceptionResponse = new CustomExceptionResponse();

		exceptionResponse.setTimestamp(new Date());
		exceptionResponse.setStatus(HttpStatus.NOT_FOUND.toString());
		exceptionResponse.setError("Not Found");
		exceptionResponse.setMessage(ex.getMessage());
		exceptionResponse.setPath(request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

	}
}
