package com.zfsoft.ms.sms.http.handler;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.ms.sms.http.HttpIOUtils;
import com.zfsoft.ms.sms.http.HttpStatus;
import com.zfsoft.ms.sms.http.exception.HttpResponseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


/**
 * 
 * @className	： JSONResponseHandler
 * @description	： http请求响应处理：返回JSONObject对象
 * @date		： 2017年6月13日 下午9:19:46
 * @version 	V1.0
 */
public class JSONResponseHandler implements ResponseHandler<JSONObject> {

	@Override
	public void preHandle(HttpURLConnection httpConn) {
		
	}
	
	@Override
	public JSONObject handleResponse(HttpURLConnection httpConn, String charset) throws IOException {
		int status = httpConn.getResponseCode();
		if (status >= HttpURLConnection.HTTP_OK && status < HttpURLConnection.HTTP_MULT_CHOICE) {
			 InputStream input = null;
			 InputStreamReader reader = null;
			 try {
				 // 从request中取得输入流
				 input = httpConn.getInputStream(); 
				 reader = new InputStreamReader(input, charset);
				 return JSONObject.parseObject(HttpIOUtils.toString(reader));
			} finally{
				// 释放资源
				HttpIOUtils.closeQuietly(input);
				HttpIOUtils.closeQuietly(reader);
			}
		} else {
			String error = HttpIOUtils.toInputText(httpConn.getErrorStream(), charset);
			if(error != null && error.trim().length() > 0) {
				throw new HttpResponseException(status, error);
			}
			throw new HttpResponseException(status, HttpStatus.getStatusText(status));
		}
	}
	
	 
}
