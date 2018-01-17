package com.foodForHungry;

import com.foodForHungry.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.*;
import java.util.TimeZone;

/**
 * Created by mashara on 5/12/17.
 */


public class DateUtilTest {

	@Test
	public void dateUtilTest()  {
		try {


			LocalDateTime ldt = LocalDateTime.now();

			ZonedDateTime zdt = ZonedDateTime.of(ldt, TimeZone.getTimeZone("Asia/Singapore").toZoneId());
			Instant instant = Instant.from(zdt);

			for (String zone : TimeZone.getAvailableIDs()) {
				System.out.println(zone);
			}

			System.out.println(TimeZone.getDefault().toZoneId());

			System.out.println(DateUtil.getTimeStampStr(Timestamp.from(instant), "UTC"));
			System.out.println(DateUtil.getTimeStampStr(Timestamp.from(instant), "IST"));
			System.out.println(DateUtil.getTimeStampStr(Timestamp.from(instant), TimeZone.getDefault().toZoneId().toString()));

		}catch (Exception e){
			Assert.fail();
		}
	}
}
