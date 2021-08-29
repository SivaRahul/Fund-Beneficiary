package com.ecommerce.rahul.exception;

public class AadharAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AadharAlreadyExistsException()
	  {
		  super();
	  }
	  public AadharAlreadyExistsException(String message)
	  {
		  super(message);
	  }
	  
	  public AadharAlreadyExistsException(String message, Throwable t)
	  {
		  super(message,t);
	  }

}
