package com.zfsoft.ms.sms.http.handler;

import com.zfsoft.ms.sms.http.HttpIOUtils;
import com.zfsoft.ms.sms.http.HttpStatus;
import com.zfsoft.ms.sms.http.exception.HttpResponseException;

import java.io.IOException;
import java.net.HttpURLConnection;

public class PlainTextResponseHandler implements ResponseHandler<String> {

	@Override
	public void preHandle(HttpURLConnection httpConn) {
		httpConn.setConnectTimeout(5000);
		httpConn.setReadTimeout(30000);
	}

	@Override
	public String handleResponse(HttpURLConnection httpConn, String charset) throws IOException {
		int status = httpConn.getResponseCode();
		if (status >= HttpURLConnection.HTTP_OK && status < HttpURLConnection.HTTP_MULT_CHOICE) {
			return HttpIOUtils.toInputText(httpConn.getInputStream(), charset);
		} else {
			String error = HttpIOUtils.toInputText(httpConn.getErrorStream(), charset);
			if(error != null && error.trim().length() > 0) {
				throw new HttpResponseException(status, error);
			}
			throw new HttpResponseException(status, HttpStatus.getStatusText(status));
		}
	}

}
