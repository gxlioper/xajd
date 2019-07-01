package com.zfsoft.ms.sms.def;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.ms.sms.SmsClientAdapter;
import com.zfsoft.ms.sms.SmsEnum;
import com.zfsoft.ms.sms.http.HttpConnectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xgxt.action.Base;


import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Baidu106SmsClient extends SmsClientAdapter {

    protected static Logger LOG = LoggerFactory.getLogger(Baidu106SmsClient.class);

    /**
     * SMS�����̵��õ�ַ
     */
    protected String smsurl = "http://apis.baidu.com/kingtto_media/106sms/106sms";
    /**
     * SMS�������ṩ��apikey
     */
    protected String apikey;

    public Baidu106SmsClient(){
        super();
    }

    @Override
    protected void onPreHandle(HttpURLConnection conn) throws Exception {
        super.onPreHandle(conn);
        // ����apikey��HTTP header
        //conn.setRequestProperty("apikey",  "���Լ���apikey");
        conn.setRequestProperty("apikey",  Base.getInitProperties().get("apikey"));
        conn.setRequestMethod(SmsEnum.BAIDU_106_SMS.getMethod());
    }

    /**
     *  JSON����ʾ�� :
     *  -------------------------------------------------------------------------------------------------------
     *	{
     *		"returnstatus": "Success",---------- ����״ֵ̬���ɹ�����Success ʧ�ܷ��أ�Faild
     *		"message": "ok",---------- ������Ϣ
     *		"remainpoint": "0",---------- ��Ӫ�̽��������壬�ɲ��ý���
     *		"taskID": "123456",---------- ���ر������������ID
     *		"successCounts": "1"---------- ���سɹ�������
     *	}
     *
     *	XML ����ʾ����
     *	-------------------------------------------------------------------------------------------------------
     *	<?xml version="1.0" encoding="utf-8" ?>
     *	<returnsms>
     *		<returnstatus>status</returnstatus>---------- ����״ֵ̬���ɹ�����Success ʧ�ܷ��أ�Faild
     *		<message>message</message>---------- ������Ϣ
     *		<remainpoint> remainpoint</remainpoint>---------- ��Ӫ�̽��������壬�ɲ��ý���
     *		<taskID>taskID</taskID>---------- ���ر������������ID
     *		<successCounts>successCounts</successCounts>---------- ���سɹ�������
     *	</returnsms>
     *
     */
    @Override
    public boolean send(String content, String mobile) {
        try {
            Map<String, Object> paramsMap = new HashMap<String, Object>();
            paramsMap.put("mobile", mobile);
            paramsMap.put("content", URLEncoder.encode(content, "UTF-8"));
            String url = Base.getInitProperties().get("smsurl") == null ? smsurl : Base.getInitProperties().get("smsurl");
            JSONObject jsonObject = HttpConnectionUtils.httpRequestWithPost(url, paramsMap, jsonHandler);
            if("Success".equalsIgnoreCase(jsonObject.getString("returnstatus"))){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
        return false;
    }
}
