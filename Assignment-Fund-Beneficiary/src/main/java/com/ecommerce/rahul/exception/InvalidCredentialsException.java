package com.ecommerce.rahul.exception;

public class InvalidCredentialsException extends RuntimeException
{
    
	private static final long serialVersionUID = 1L;
	
	public InvalidCredentialsException()
    {
    	super();
    	
    }
    public InvalidCredentialsException(String message)
    {
    	super(message);
    }
    public InvalidCredentialsException(String message,Throwable t)
    {
    	super(message,t);
    }
}
