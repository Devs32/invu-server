package kr.co.devs32.web.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import kr.co.devs32.web.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> handleEntityNotFoundException(EntityNotFoundException ex) {
		log.error("EntityNotFoundException: {}", ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<String>> handleGenericException(Exception ex) {
		log.error("Exception: {}", ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"));
	}

	@ExceptionHandler(CustomException.BusinessException.class)
	public ResponseEntity<ApiResponse> handleBusinessException(CustomException.BusinessException e) {
		log.error("BusinessException: {}", e.getMessage(), e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(ApiResponse.error(HttpStatus.BAD_REQUEST.value(), e.getMessage()));
	}

}
