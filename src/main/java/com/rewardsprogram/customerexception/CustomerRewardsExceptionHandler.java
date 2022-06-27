package com.rewardsprogram.customerexception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception handler for internal server error
 * 
 * @author Manasa
 *
 */
@ControllerAdvice
public class CustomerRewardsExceptionHandler {

//	@ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handle(Exception ex, 
//                HttpServletRequest request, HttpServletResponse response) {        
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }

//	@ExceptionHandler(value = CustomerDataNotfoundException.class)
//	public ResponseEntity<Object> exception(CustomerDataNotfoundException exception) {
//		return new ResponseEntity<>("Customer Id not found", HttpStatus.NOT_FOUND);
//	}

//	@ExceptionHandler(CustomerDataNotfoundException.class)
//	public ResponseEntity<ErrorResponse> handleCustomDataNotFoundExceptions(Exception e) {
//		HttpStatus status = HttpStatus.NOT_FOUND; // 404
//		// converting the stack trace to String
//		StringWriter stringWriter = new StringWriter();
//		PrintWriter printWriter = new PrintWriter(stringWriter);
//		e.printStackTrace(printWriter);
//		String stackTrace = stringWriter.toString();
//		return new ResponseEntity<>(new ErrorResponse(status, e.getMessage(), stackTrace), status);
//	}

	@ExceptionHandler(CustomErrorException.class)
	public ResponseEntity<ErrorResponse> handleCustomErrorExceptions(Exception e) {
		// casting the generic Exception e to CustomErrorException
		CustomErrorException customErrorException = (CustomErrorException) e;

		HttpStatus status = customErrorException.getStatus();

		// converting the stack trace to String
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		customErrorException.printStackTrace(printWriter);
		String stackTrace = stringWriter.toString();

		return new ResponseEntity<>(new ErrorResponse(status, customErrorException.getMessage(), stackTrace,
				customErrorException.getData()), status);
	}

}
