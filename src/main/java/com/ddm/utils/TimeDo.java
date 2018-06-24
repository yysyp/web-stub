package com.ddm.utils;

import java.text.SimpleDateFormat;


public class TimeDo {

	/**
	 * 
	 * @return
	 */
	public static String DefaultDateTimeStyle = "yyyy-MM-dd HH:mm:ss";
	//public static String DefaultDateTimeStyleForFileName = "yyyy-MM-dd HH.mm.ss";
	public static String getTimeStamp(Object date, String style) {
		if(style == null || style.trim().equals("")) {
			style = DefaultDateTimeStyle;
		}
		SimpleDateFormat df = new SimpleDateFormat(style);
		if(date == null) {
			date = new java.util.Date();
		}
		return df.format(date);
	}
	
	public static String getTimeStampX() {
		return getTimeStamp(null, "yyyyMMdd_HH.mm.ss");
	}
}
