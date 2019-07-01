package com.zfsoft.ms.sms.def;

import com.zfsoft.ms.sms.SmsClientAdapter;
import com.zfsoft.ms.sms.http.HttpConnectionUtils;
import xgxt.action.Base;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ZdtSmsClient extends SmsClientAdapter {

	/**
	 * SMS�����̵��õ�ַ
	 */ 
	protected String openurl = "http://open.zdt.zju.edu.cn:8080/UcOpen/api/sendSms/";
	protected String smsurl = "http://zdt-sms.zju.edu.cn:8086/smsInterface/sendsms";

	public ZdtSmsClient(){
		super();
	}

	/**
	 * @param content
	 * @param mobile  ���Ž��պ��룬������루��Ӣ�Ķ��Ÿ�������Ϊһ��Ⱥ������
	 */
	@Override
	public boolean send(String content, String mobile) {
		int ret = 99;
		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("username", Base.getInitProperties().get("apikey"));
			paramsMap.put("password", Base.getInitProperties().get("password"));
			paramsMap.put("to", mobile); // ���Ž��պ��룬������루��Ӣ�Ķ��Ÿ�������Ϊһ��Ⱥ������
			paramsMap.put("text", URLEncoder.encode(content, "GBK"));
			paramsMap.put("msgtype", Base.getInitProperties().get("msgType") == null ? "1" : Base.getInitProperties().get("msgType")); // �������� 1:��ͨ���� 2:������(����64�ַ�)
			paramsMap.put("source", Base.getInitProperties().get("source"));
			
			//String result = requestGet(getSmsurl(), paramsMap);
			String url = Base.getInitProperties().get("smsurl") == null ? smsurl : Base.getInitProperties().get("smsurl");
			String result = HttpConnectionUtils.httpRequestWithPost(url, paramsMap, textHandler);
			if (result != null) {
				ret = Integer.parseInt(result);
			} else {
				ret = -99;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		if (ret > 0) {
			return true;
		} else {
			return false;
		}
	}
}
