package com.example.common;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<CustomErrorResponse>handleNotFoundException(NotFoundException e) {
		CustomErrorResponse error=new CustomErrorResponse("NOT_FOUND_EXCEPTION",e.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setStatus(HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<CustomErrorResponse>(error,HttpStatus.NOT_FOUND);
		
	}
	
	

}
