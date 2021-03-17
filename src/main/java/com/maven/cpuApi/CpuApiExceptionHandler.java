package com.maven.cpuApi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CpuApiExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex,
			WebRequest request)
	{
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Internal server error",
				details);
		
		System.out.println(ex);

		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmptyRecordException.class)
	public final ResponseEntity<Object> handleEmptyRecordException(Exception ex,
			WebRequest request)
	{
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Record is empty", details);

		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<Object> handleRecordNotFoundException(
			Exception ex, WebRequest request)
	{
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Could not find the record",
				details);

		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<Object> handleDataIntegrityViolationException(Exception ex,
			WebRequest request)
	{
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Record is duplicated", details);

		return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request)
	{
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors())
			details.add(error.getDefaultMessage());

		ErrorResponse error = new ErrorResponse("Validation failed on server side", details);

		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
}
