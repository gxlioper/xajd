package com.zfsoft.ms.sms.http.exception;

import java.io.IOException;

/**
 * @className	�� HttpResponseException
 * @description	�� TODO(��������������)
 * @date		�� 2017��6��13�� ����9:20:17
 * @version 	V1.0
 */
@SuppressWarnings("serial")
public class HttpResponseException extends IOException {
	
	private int statusCode = 200;

	public HttpResponseException(String message) {
		super(message);
	}
	
	public HttpResponseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public HttpResponseException(final int statusCode, final String s) {
		super(s);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return this.statusCode;
	}

}
