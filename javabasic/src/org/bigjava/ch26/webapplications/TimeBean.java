package org.bigjava.ch26.webapplications;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeBean {
	private DateFormat timeFormatter;
	
	/**
	 * Initializes the formatter
	 * */
	public TimeBean()
	{
		timeFormatter = DateFormat.getTimeInstance();
	}
	
	/**
	 * Read-only time property
	 * @return the formatted time
	 * */
	public String getTime()
	{
		Date time = new Date();
		String timeString = timeFormatter.format(time);
		return timeString;
	}
}
