package com.assignment.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static Date convertStringToDate(String inputDateString, String desiredFormat) throws ParseException {
		DateFormat formatter = new SimpleDateFormat(desiredFormat);
		formatter.setLenient(false);
		return formatter.parse(inputDateString);
	}

	/**
	 * convert string to desired date
	 * 
	 * @param desiredFormat
	 * @param inputDateString
	 * @return Date
	 * @throws ParseException
	 */
	public static String convertDateToString(String desiredFormat, Date inputDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(desiredFormat);
		return formatter.format(inputDate);
	}
}
