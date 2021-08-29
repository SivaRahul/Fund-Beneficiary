package com.ecommerce.rahul.utility;



import org.springframework.stereotype.Component;

@Component
public class RandomGen
{
  private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  public static long randomnumber() 
  {
	  while (true) {
	        long number = (long)(Math.random() * 100000000 * 100000000); 
	        if (String.valueOf(number).length() == 14)
	            return number;
	    }
  
  }
  
  public static String randomAlphaNumeric(int count) {
	  StringBuilder builder = new StringBuilder();
	  while (count-- != 0) {
		  int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		  builder.append(ALPHA_NUMERIC_STRING.charAt(character));
	  }
  return builder.toString();
  }
}



  