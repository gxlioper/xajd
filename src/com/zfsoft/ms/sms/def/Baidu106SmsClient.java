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
     * SMS服务商调用地址
     */
    protected String smsurl = "http://apis.baidu.com/kingtto_media/106sms/106sms";
    /**
     * SMS服务商提供的apikey
     */
    protected String apikey;

    public Baidu106SmsClient(){
        super();
    }

    @Override
    protected void onPreHandle(HttpURLConnection conn) throws Exception {
        super.onPreHandle(conn);
        // 填入apikey到HTTP header
        //conn.setRequestProperty("apikey",  "您自己的apikey");
        conn.setRequestProperty("apikey",  Base.getInitProperties().get("apikey"));
        conn.setRequestMethod(SmsEnum.BAIDU_106_SMS.getMethod());
    }

    /**
     *  JSON返回示例 :
     *  -------------------------------------------------------------------------------------------------------
     *	{
     *		"returnstatus": "Success",---------- 返回状态值：成功返回Success 失败返回：Faild
     *		"message": "ok",---------- 返回信息
     *		"remainpoint": "0",---------- 运营商结算无意义，可不用解析
     *		"taskID": "123456",---------- 返回本次任务的序列ID
     *		"successCounts": "1"---------- 返回成功短信数
     *	}
     *
     *	XML 返回示例：
     *	-------------------------------------------------------------------------------------------------------
     *	<?xml version="1.0" encoding="utf-8" ?>
     *	<returnsms>
     *		<returnstatus>status</returnstatus>---------- 返回状态值：成功返回Success 失败返回：Faild
     *		<message>message</message>---------- 返回信息
     *		<remainpoint> remainpoint</remainpoint>---------- 运营商结算无意义，可不用解析
     *		<taskID>taskID</taskID>---------- 返回本次任务的序列ID
     *		<successCounts>successCounts</successCounts>---------- 返回成功短信数
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
