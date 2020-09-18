package br.com.ot.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Utils {

	public static Date adicionaMin(Date d,Integer min){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.MINUTE, min);
		return c.getTime();
	}
	
	public static HashMap<String,List<DEZ>> MONTAR_LISTA_B(){
		HashMap<String,List<DEZ>> map = new HashMap<>();
		map.put("B_01", Arrays.asList(DEZ.A,DEZ.B,DEZ.C,DEZ.D,DEZ.E));
		map.put("B_02", Arrays.asList(DEZ.A,DEZ.B,DEZ.C,DEZ.D,DEZ.F));
		map.put("B_03", Arrays.asList(DEZ.A,DEZ.B,DEZ.D,DEZ.E,DEZ.F));
		map.put("B_04", Arrays.asList(DEZ.A,DEZ.B,DEZ.C,DEZ.E,DEZ.F));
		map.put("B_05", Arrays.asList(DEZ.A,DEZ.C,DEZ.D,DEZ.E,DEZ.F));
		map.put("B_06", Arrays.asList(DEZ.B,DEZ.C,DEZ.D,DEZ.E,DEZ.F));
		
		return map;
	}
	
	public static HashMap<String,List<DEZ>> MONTAR_LISTA_C(){
		HashMap<String,List<DEZ>> map = new HashMap<>();
		map.put("C_01", Arrays.asList(DEZ.A,DEZ.B,DEZ.C,DEZ.D));
		map.put("C_02", Arrays.asList(DEZ.A,DEZ.B,DEZ.C,DEZ.E));
		map.put("C_03", Arrays.asList(DEZ.A,DEZ.B,DEZ.C,DEZ.F));
		map.put("C_04", Arrays.asList(DEZ.A,DEZ.B,DEZ.D,DEZ.E));
		map.put("C_05", Arrays.asList(DEZ.A,DEZ.B,DEZ.D,DEZ.F));
		map.put("C_06", Arrays.asList(DEZ.A,DEZ.B,DEZ.E,DEZ.F));
		map.put("C_07", Arrays.asList(DEZ.A,DEZ.C,DEZ.D,DEZ.E));
		map.put("C_08", Arrays.asList(DEZ.A,DEZ.C,DEZ.D,DEZ.F));
		map.put("C_09", Arrays.asList(DEZ.A,DEZ.C,DEZ.E,DEZ.F));
		map.put("C_10", Arrays.asList(DEZ.A,DEZ.D,DEZ.E,DEZ.F));
		map.put("C_11", Arrays.asList(DEZ.B,DEZ.C,DEZ.D,DEZ.E));		
		map.put("C_12", Arrays.asList(DEZ.B,DEZ.C,DEZ.D,DEZ.F));
		map.put("C_13", Arrays.asList(DEZ.B,DEZ.C,DEZ.E,DEZ.F));
		map.put("C_14", Arrays.asList(DEZ.B,DEZ.D,DEZ.E,DEZ.F));
		map.put("C_15", Arrays.asList(DEZ.C,DEZ.D,DEZ.E,DEZ.F));
		return map;
	}
	
	public static HashMap<String,List<DEZ>> MONTAR_LISTA_D(){
		HashMap<String,List<DEZ>> map = new HashMap<>();
		
		map.put("D_01", Arrays.asList(DEZ.A,DEZ.B,DEZ.C));
		map.put("D_02", Arrays.asList(DEZ.A,DEZ.B,DEZ.D));
		map.put("D_03", Arrays.asList(DEZ.A,DEZ.B,DEZ.E));
		map.put("D_04", Arrays.asList(DEZ.A,DEZ.B,DEZ.F));
		map.put("D_05", Arrays.asList(DEZ.A,DEZ.C,DEZ.D));
		map.put("D_06", Arrays.asList(DEZ.A,DEZ.C,DEZ.E));
		map.put("D_07", Arrays.asList(DEZ.A,DEZ.C,DEZ.F));
		map.put("D_08", Arrays.asList(DEZ.A,DEZ.D,DEZ.E));
		map.put("D_09", Arrays.asList(DEZ.A,DEZ.D,DEZ.F));
		map.put("D_10", Arrays.asList(DEZ.A,DEZ.E,DEZ.F));
		map.put("D_11", Arrays.asList(DEZ.B,DEZ.C,DEZ.D));
		map.put("D_12", Arrays.asList(DEZ.B,DEZ.C,DEZ.E));		
		map.put("D_13", Arrays.asList(DEZ.B,DEZ.C,DEZ.F));
		map.put("D_14", Arrays.asList(DEZ.B,DEZ.D,DEZ.E));
		map.put("D_15", Arrays.asList(DEZ.B,DEZ.D,DEZ.F));
		map.put("D_16", Arrays.asList(DEZ.B,DEZ.E,DEZ.F));		
		map.put("D_17", Arrays.asList(DEZ.C,DEZ.D,DEZ.E));
		map.put("D_18", Arrays.asList(DEZ.C,DEZ.D,DEZ.F));
		map.put("D_19", Arrays.asList(DEZ.C,DEZ.E,DEZ.F));
		map.put("D_20", Arrays.asList(DEZ.D,DEZ.E,DEZ.F));		
		
		return map;
	}
	
	public static HashMap<String,List<DEZ>> MONTAR_LISTA_E(){
		HashMap<String,List<DEZ>> map = new HashMap<>();
		
		map.put("E_01", Arrays.asList(DEZ.A,DEZ.B));
		map.put("E_02", Arrays.asList(DEZ.A,DEZ.C));
		map.put("E_03", Arrays.asList(DEZ.A,DEZ.D));
		map.put("E_04", Arrays.asList(DEZ.A,DEZ.E));
		map.put("E_05", Arrays.asList(DEZ.A,DEZ.F));
		map.put("E_06", Arrays.asList(DEZ.B,DEZ.C));
		map.put("E_07", Arrays.asList(DEZ.B,DEZ.D));
		map.put("E_08", Arrays.asList(DEZ.B,DEZ.E));
		map.put("E_09", Arrays.asList(DEZ.B,DEZ.F));
		map.put("E_10", Arrays.asList(DEZ.C,DEZ.D));
		map.put("E_11", Arrays.asList(DEZ.C,DEZ.E));
		map.put("E_12", Arrays.asList(DEZ.C,DEZ.F));		
		map.put("E_13", Arrays.asList(DEZ.D,DEZ.E));
		map.put("E_14", Arrays.asList(DEZ.D,DEZ.F));
		map.put("E_15", Arrays.asList(DEZ.E,DEZ.F));
		
		return map;
	}
	
	//9567-5533
}
