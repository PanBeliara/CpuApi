package com.maven.cpuApi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 309074609588842606L;

	public RecordNotFoundException(String exception)
	{
		super(exception);
	}
}
