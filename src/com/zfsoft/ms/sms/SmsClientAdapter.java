package com.zfsoft.ms.sms;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.ms.sms.http.handler.JSONResponseHandler;
import com.zfsoft.ms.sms.http.handler.PlainTextResponseHandler;
import com.zfsoft.ms.sms.http.handler.ResponseHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SmsClientAdapter implements SmsClient {

    protected ResponseHandler<String> textHandler = new PlainTextResponseHandler();
    protected ResponseHandler<JSONObject> jsonHandler = new JSONResponseHandler();
    @Override
    public boolean send(String content, String mobile) {
        return false;
    }
    protected void onPreHandle(HttpURLConnection conn) throws Exception {

        conn.setConnectTimeout(5000);
        conn.setReadTimeout(3000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.setRequestProperty("Charset", "UTF-8"); // 设置编码

    }

    protected String requestGet(String httpUrl, Map<String, String> httpArg) {
        if (httpUrl == null || "".equals(httpUrl.trim())) {
            return null;
        }
        BufferedReader reader = null;
        StringBuffer result = new StringBuffer();
        try {
            StringBuffer paramsBuffer = new StringBuffer();
            if (httpArg != null && httpArg.size() > 0) {
                Set<String> set = httpArg.keySet();
                Iterator<String> it = set.iterator();
                int count = 0;
                while (it.hasNext()) {
                    String key = it.next();
                    String value = httpArg.get(key);
                    if (count > 0) {
                        paramsBuffer.append("&");
                    }
                    //内容转码由调用者实现
                    //paramsBuffer.append(key + "=" + URLEncoder.encode(value, "UTF-8"));
                    paramsBuffer.append(key + "=" + value);
                    count++;
                }
            }
            String params = paramsBuffer.toString();
            if (params != null && !"".equals(params.trim())) {
                if (httpUrl.indexOf("?") == -1) {
                    httpUrl = httpUrl + "?" + params;
                } else if (httpUrl.indexOf("?") == httpUrl.length() - 1) {
                    httpUrl = httpUrl + params;
                } else {
                    httpUrl = httpUrl + "&" + params;
                }
            }
            URL url = new URL(httpUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            this.onPreHandle(conn);
            conn.connect();
            if (conn.getResponseCode() == 200) {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String strRead = null;
                int row = 0;
                while ((strRead = reader.readLine()) != null) {
                    //多行数据，且当前行读取到数据，则在上一行之后添加换行符
                    if(row > 0 && strRead != null && strRead.length() > 0){
                        result.append("\r\n");
                    }
                    result.append(strRead);
                    row ++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if(reader != null){
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        return result.toString();
    }
}
