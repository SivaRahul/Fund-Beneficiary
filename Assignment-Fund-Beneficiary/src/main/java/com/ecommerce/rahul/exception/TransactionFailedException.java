package com.ecommerce.rahul.exception;

public class TransactionFailedException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public TransactionFailedException()
	  {
		  super();
	  }
	  public TransactionFailedException(String message)
	  {
		  super(message);
	  }
	  
	  public TransactionFailedException(String message, Throwable t)
	  {
		  super(message,t);
	  }
}
	  
