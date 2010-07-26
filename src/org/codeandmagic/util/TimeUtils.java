package org.codeandmagic.util;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	public final static long MINUTE = 60 * 1000;
	public final static long HOUR = 60 * MINUTE;
	public final static long DAY = HOUR * 24;
	public final static long WEEK = DAY * 7;
	public final static long MONTH = DAY * 31;
	public final static long YEAR = DAY * 356;

	public static Date ceiling(Date date, int field) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		switch (field) {
		case Calendar.YEAR:
			c.clear(Calendar.MONTH); // ATENTION! No break, this will cascade!
		case Calendar.MONTH:
			c.clear(Calendar.DATE);
		case Calendar.DATE:
			c.clear(Calendar.HOUR);
		case Calendar.HOUR:
			c.clear(Calendar.MINUTE);
		case Calendar.MINUTE:
			c.clear(Calendar.SECOND);
		default:
			break;
		}
		return c.getTime();
	}

	public static Date floor(Date date, int field) {
		Calendar c = Calendar.getInstance();
		c.setTime(TimeUtils.floor(date, field));
		c.set(field, c.get(field) + 1);
		return c.getTime();
	}

	public static int detectTimeScale(long time, int minUnitsToJumpToNextLevel) {
		if (time < TimeUtils.MINUTE * minUnitsToJumpToNextLevel) {
			return Calendar.SECOND;
		}
		else if (time < TimeUtils.HOUR * minUnitsToJumpToNextLevel) {
			return Calendar.MINUTE;
		}
		else if (time < TimeUtils.DAY * minUnitsToJumpToNextLevel) {
			return Calendar.HOUR;
		}
		else if (time < TimeUtils.MONTH * minUnitsToJumpToNextLevel) {
			return Calendar.DATE;
		}
		else if (time < TimeUtils.YEAR * minUnitsToJumpToNextLevel) {
			return Calendar.MONTH;
		}
		else {
			return Calendar.YEAR;
		}
	}
}
