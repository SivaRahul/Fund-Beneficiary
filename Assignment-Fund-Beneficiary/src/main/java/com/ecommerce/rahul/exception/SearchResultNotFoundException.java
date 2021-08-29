package com.ecommerce.rahul.exception;

public class SearchResultNotFoundException extends RuntimeException
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public SearchResultNotFoundException()
  {
	  super();
  }
  public SearchResultNotFoundException(String message)
  {
	  super(message);
  }
  
  public SearchResultNotFoundException(String message, Throwable t)
  {
	  super(message,t);
  }
  
  
}
