package com.cadastro.clientes.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private HttpStatus status;
	
    public CustomException(String msg, HttpStatus status){
    	super(msg);
    	this.status = status;
    }

	public HttpStatus getStatus() {
		return status;
	}

}
