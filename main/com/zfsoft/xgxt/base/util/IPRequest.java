package com.zfsoft.xgxt.base.util;

import javax.servlet.http.HttpServletRequest;

public class IPRequest {

	
	/**
	 * 
	 * @描述:获取IP地址
	 * @作者：Penghui.Qu
	 * @日期：2013-5-17 上午08:52:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public static String getIpAddress(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");

		String localIP = "127.0.0.1";

		if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}

		if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

}
