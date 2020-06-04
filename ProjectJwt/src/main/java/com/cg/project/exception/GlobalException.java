package com.cg.project.exception;

 import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.project.response.Response;


@ControllerAdvice
public class GlobalException{
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleCustomValidationError(MethodArgumentNotValidException e){
		Response<?> response = new Response(true,e.getBindingResult().getFieldError().getDefaultMessage(),new ArrayList());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}




































//@ControllerAdvice
//public class GlobalException extends ResponseEntityExceptionHandler {
//    @ExceptionHandler(CustomerNotFoundException.class)
//    public ResponseEntity<?> resourceNotFoundException(CustomerNotFoundException ex, WebRequest request) {
//        Response errorDetails = new Response(true, ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
//    	Response errorDetails = new Response(true, ex.getMessage(), request.getDescription(false));
//        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
// 
////    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(CustomerNotFoundException ex,
//           HttpHeaders headers, HttpStatus status, WebRequest request) {
//    	Response errorDetails = new Response(true, ex.getMessage() ,
//               null);
//           return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
//    } 
//}