package com.ecommerce.rahul.exception;

public class UserNameAlreadyExistsException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public UserNameAlreadyExistsException()
	  {
		  super();
	  }
	  public UserNameAlreadyExistsException(String message)
	  {
		  super(message);
	  }
	  
	  public UserNameAlreadyExistsException(String message, Throwable t)
	  {
		  super(message,t);
	  }


}
