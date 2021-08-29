package com.ecommerce.rahul.exception;

public class PanNumberAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public PanNumberAlreadyExistsException()
	  {
		  super();
	  }
	  public PanNumberAlreadyExistsException(String message)
	  {
		  super(message);
	  }
	  
	  public PanNumberAlreadyExistsException(String message, Throwable t)
	  {
		  super(message,t);
	  }

}
