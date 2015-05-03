package me.bobrob.com;

import com.mysql.jdbc.StringUtils;

public class password_checker {


	public static int checkPw(String passCheck) {
		int count_int = 0;
		int count_upper_case = 0;
		int count_lower_case = 0;
		int count_special_character = 0;
		
		//System.out.println("Test: " + passCheck);
		char[] c = passCheck.toCharArray();
		char[] specialChars = {'!', '@', '#', '$', '%', '^', '"', '&', '*', '(', ',', ')', '[', ']', '|', ';', '\'',  '.', '/', '{', '}', '\\', ':', '<', '>', '?', '|', ' ', '¬', '`', '¦' };
		
		
		//int count = 0;
		for (int i = 0, len = passCheck.length(); i < len; i++) {
			//checks how many numbers 
		    if (Character.isDigit(passCheck.charAt(i))) {
		    	count_int = checkCount(count_int);
		    }
		    //checks upper case
		    if (Character.isUpperCase(passCheck.charAt(i))) {
		    	count_upper_case = checkCount(count_upper_case);
		    }
		    //checks lower case
		    if (Character.isLowerCase(passCheck.charAt(i))) {
		    	count_lower_case = checkCount(count_lower_case);
		    }
		    //check special characters 
		    for(int x = 0; x < specialChars.length; x++){
		    	if(passCheck.charAt(i) == specialChars[x]){
			    	count_special_character = checkCount(count_special_character);
			    }
		    	//System.out.print("aaaabbb: s ");
		    }
		    
		}
		return count_int + count_upper_case + count_lower_case + count_special_character;
	}
	public static int checkCount(int count){
		if(count < 10){
			count++;
    	}
		
		return count;
	}
}