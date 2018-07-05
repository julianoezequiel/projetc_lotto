package br.com.ottol.utils;

import java.util.Calendar;
import java.util.Date;

public class Utils {

	public static Date adicionaMin(Date d,Integer min){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MINUTE, min);
		return c.getTime();
	}
	
}
