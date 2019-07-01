package com.zfsoft.ms.sms.def;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.ms.sms.SmsClientAdapter;
import com.zfsoft.ms.sms.SmsEnum;
import com.zfsoft.ms.sms.http.HttpConnectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xgxt.action.Base;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 *@������		�� GreenTownSmsClient.java
 *@������		������ͨ���ŷ��ͽӿ�ʵ��
 *@������		��wandalong
 *@����ʱ��	��2017��8��21�� ����5:58:45
 *@�޸���		��
 *@�޸�ʱ��	��
 *@�汾��		: v1.0
 */
public class GreenTownSmsClient extends SmsClientAdapter {

	
	protected static Logger LOG = LoggerFactory.getLogger(GreenTownSmsClient.class);
	
	/**
	 * �����ύ��ַ:
	 *	���ſ���һ�����ύآ���� 5000 ���ֻ����룬ÿ��������Ӣ�Ķ��ż���������������뽨��ʹ �� POST ��ʽ��
	 *	URL ��ַΪ��http://IP:PORT/sms/send
	 *	ע������ IP:PORT Ϊ������ĵ�ַ�Ͷ˿ڡ�
	 *	IP Ϊ api.china95059.net��PORT Ĭ��Ϊ 8080��
	 */ 
	protected String smsurl = "http://api.china95059.net:8080/sms/send";

	protected static Map<String, String> statusMap = new HashMap<String, String>();
	
	static {
		statusMap.put("0", "�ύ�ɹ�/��ѯ�ɹ�");
		statusMap.put("101", "�޴��û�");
		statusMap.put("102", "�������");
		statusMap.put("103", "�ύ���죨�ύ�ٶȳ����������ƣ�");
		statusMap.put("104", "ϵͳæ����ƽ̨��ԭ����ʱ�޷������ύ�Ķ��ţ�");
		statusMap.put("105", "���ж��ţ��������ݰ������дʣ�");
		statusMap.put("106", "��Ϣ���ȴ��������ݳ���>2000 �� <=0��");
		statusMap.put("107", "����������ֻ�����");
		statusMap.put("108", "�ֻ��������������>5000 ��<=0��");
		statusMap.put("109", "�޷��Ͷ�ȣ����û����ö�������ʹ���꣩");
		statusMap.put("110", "���ڷ���ʱ����");
		statusMap.put("111", "�������˻����·��Ͷ������");
		statusMap.put("112", "attime ��������С�ڵ�ǰʱ����߸�ʽ���⣩");
		statusMap.put("113", "extno ��ʽ���󣨷����ֻ��߳��Ȳ��ԣ�");
		statusMap.put("115", "�Զ���˲��أ����ݰ�������һ�£�");
		statusMap.put("116", "ǩ�����Ϸ����û������ǩ����ǰ���£�");
		statusMap.put("117", "IP ��ַ��֤��");
		statusMap.put("118", "�û�û����Ӧ�ķ���Ȩ��");
		statusMap.put("119", "�û��ѹ���");
		
	}

	
	@Override
	protected void onPreHandle(HttpURLConnection conn) throws Exception {
		super.onPreHandle(conn);
		conn.setRequestMethod(SmsEnum.GreenTown.getMethod());
	}

	/**
	 * @param content �������ݣ��������ݳ���آ�ܳ��� 1000 ���֡�ʹ�� URL��ʽ����Ϊ UTF-8 ��ʽ���������ݣ���ǩ�������� 70 ���ַ�ʱ�Գ����ŵĸ�ʽ���ͣ��Ʒ�Ϊ 67 ��/������
	 * @param mobile  ���Ž��պ��룬������루��Ӣ�Ķ��Ÿ�������Ϊһ��Ⱥ������; ���ſ���һ�����ύ������ 5000 ���ֻ����롣�����������뽨��ʹ �� POST ��ʽ��
	 */
	@Override
	public boolean send(String content, String mobile) {

		try {
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			//SMS�������ṩ��appid
			paramsMap.put("name", Base.getInitProperties().get("apikey"));
			//SMS�������ṩ��appid��Ӧ��password
			paramsMap.put("pswd", Base.getInitProperties().get("password"));
			paramsMap.put("mobile", mobile);
			// ����������������ݣ��������ݳ���آ�ܳ��� 1000 ���֡�ʹ�� URL��ʽ����Ϊ UTF-8 ��ʽ���������ݣ���ǩ�������� 70 ���ַ�ʱ�Գ����ŵĸ�ʽ���ͣ��Ʒ�Ϊ 67 ��/������
			//paramsMap.put("msg", URLEncoder.encode(content, "UTF-8"));
			paramsMap.put("msg", content);
			// ��ѡ�������Ƿ���Ҫ״̬���棬Ĭ�� true��true��������Ҫ״̬���棻false ����Ҫ״̬����
			paramsMap.put("needstatus", String.valueOf(true));
			// ��ѡ��������չ�룬�û�������չ�룬2 λ��Ĭ����
			paramsMap.put("sender", "");
			// ��ѡ�������������ͣ�Ĭ��json,�ݲ�֧������
			paramsMap.put("type", "json");
			// ��ѡ��������ʱ����ʱ�䡣yyyyMMddHHmm ��ʽ
			//map.put("attime", "json");
			
			/*	esptime �ط���������Ӧʱ�䣬yyyyMMddHHmmss ��ʽ
				respstatus �ط���������Ӧ״̬���� 1.3.3 ��Ӧ״ֵ̬
				msgid �ɷ�������respstatus Ϊ 0 ʱ�ŷ��أ�13 λ�ַ������ṩ����״̬�� ��ƥ��ʱʹ�á�һ�η�������ֻ����һ�� msgid���� respstatus ��Ϊ 0���˲�����
			*/
			String url = Base.getInitProperties().get("smsurl") == null ? smsurl : Base.getInitProperties().get("smsurl");
			JSONObject jsonObject = HttpConnectionUtils.httpRequestWithPost(url, paramsMap, jsonHandler);
			String respstatus = jsonObject.getString("respstatus");
			if("0".equals(respstatus)){
				return true;
			} else {
				LOG.warn(statusMap.get(respstatus));
				return false;
			}
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return false;
	}
}
