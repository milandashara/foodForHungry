package com.foodForHungry.util;

import com.foodForHungry.exception.InvalidRequestException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by mashara on 5/12/17.
 */
public class DateUtil {

	public static Timestamp getCurrentTimestampInUTC(){
		LocalDateTime ldt = LocalDateTime.now();
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneOffset.UTC);
		Instant instant = Instant.from(zdt);
		return Timestamp.from(instant);
	}

	public static Timestamp getCurrentTimestamp(String toTimeZone) throws InvalidRequestException{
		if (!isValidTimeZone(toTimeZone)){
			throw new InvalidRequestException("invalid time zone");
		}
		LocalDateTime ldt = LocalDateTime.now();
		ZonedDateTime zdt = ZonedDateTime.of(ldt, TimeZone.getTimeZone(toTimeZone).toZoneId());
		Instant instant = Instant.from(zdt);
		return Timestamp.from(instant);
	}

	public static Timestamp getTimeStampInUTC(String dateStr) throws ParseException{

		DateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");
		formatter.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
		Timestamp timestamp = new Timestamp((formatter.parse(dateStr)).getTime());
		return timestamp;
	}

	public static String getTimeStampStr(Timestamp timestampInUtc,String toTimeZone) throws ParseException,InvalidRequestException{
		if (!isValidTimeZone(toTimeZone)){
			throw new InvalidRequestException("invalid time zone");
		}
		DateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");
		formatter.setTimeZone(TimeZone.getTimeZone(toTimeZone));
		return formatter.format(timestampInUtc);
	}

	public static boolean isValidTimeZone(String zone){
		return Arrays.asList(TimeZone.getAvailableIDs()).contains(zone);
	}
}
