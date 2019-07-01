package com.zfsoft.xgxt.base.util;

import javax.servlet.http.HttpServletRequest;

public class IPRequest {

	
	/**
	 * 
	 * @����:��ȡIP��ַ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-17 ����08:52:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * String �������� 
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
