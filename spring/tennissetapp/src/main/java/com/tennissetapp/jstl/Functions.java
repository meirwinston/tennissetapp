package com.tennissetapp.jstl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Functions {
	public static String test() {
        return "TEST";
    }
	
	public static int bitwiseAnd(int a, int b){
		return (a & b);
	}
	
	public static String formatDate(Date date){
		if(date == null) return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss");
		return dateFormat.format(date);
	}
	
//	public static String serialize(Object obj) throws IOException{
//		return JacksonUtils.serialize(obj);
//	}

}
