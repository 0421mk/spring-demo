package com.example.demo.util;

public class Util {

	public static boolean empty(Object obj) {
		if(obj == null) {
			return true;
		}
		
		// String이 아니라면 비어있지 않다.
		if(obj instanceof String == false) {
			return false;
		}
		
		String str = (String)obj;
		
		return str.trim().length() == 0;
	}
	
	public static String f(String format, Object... args) {
		return String.format(format, args);
	}

}
