package com.foodForHungry.util;

/**
 * Created by mashara on 5/12/17.
 */


import javax.servlet.http.HttpServletRequest;

public class WebUtils {

	private HttpServletRequest request;

	public static String getClientIp(HttpServletRequest request) {

		String remoteAddr = "";

		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}

		return remoteAddr;
	}

}
