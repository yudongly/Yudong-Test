package com.silanis.lottery.utils;

import java.text.DecimalFormat;

public class LotteryUtils {

	public static boolean isNullOrEmpty(String s) {
		return s == null || "".equals(s);
	}
	
	public static double round(double d) {
		return Math.round(d);
	}
	
	public static double format(double value) {
		DecimalFormat df=new DecimalFormat("0.00");
		String format = df.format(value); 
		return Double.valueOf(format);
	}
}
