package com.zfsoft.ms.sms.http;

import com.zfsoft.ms.sms.http.handler.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;


/**
 * 
 * @className	�� HttpConnectionUtils
 * @description	��ʹ��java����HttpURLConnection�������get,post��ʽ������
 * @date		�� 2015-6-24 ����05:08:14
 * <pre>
 * �ܽ᣺
 * a:) HttpURLConnection��connect()������ʵ����ֻ�ǽ�����һ�����������tcp���ӣ���û��ʵ�ʷ���http����
 * 	        ������post����get��http����ʵ����ֱ��HttpURLConnection��getInputStream()��������������ʽ���ͳ�ȥ��
	       b:) ����POST��ʽ����URL����ʱ��URL����������趨˳��������֮�أ�
	    ��connection�����һ�����ã���һ��set������
	    ������Ҫ��connect()����ִ��֮ǰ��ɡ�����outputStream��д�������ֱ���Ҫ��inputStream�Ķ�����֮ǰ��
	    ��Щ˳��ʵ��������http����ĸ�ʽ�����ġ�
	    ���inputStream��������outputStream��д����֮ǰ�����׳����⣺
	    java.net.ProtocolException: Cannot write output after reading input.......
	      
	       c:) http����ʵ��������������ɣ�
	    һ����httpͷ�����й��ڴ˴�http��������ö���httpͷ���涨�壬
	           һ��������content��
	    connect()���������HttpURLConnection���������ֵ����httpͷ����Ϣ������ڵ���connect����֮ǰ��
	    �ͱ�������е�����׼���á�
	       d:) ��httpͷ��������ŵ���http��������ģ����ĵ�������ͨ��outputStream��д��ģ�
	    ʵ����outputStream����һ�����������������Ǹ��ַ�������������д��Ķ��������������͵����磬
	    ���Ǵ������ڴ滺�����У���outputStream���ر�ʱ�������������������http���ġ�
	    ���ˣ�http����Ķ����Ѿ�ȫ��׼����������getInputStream()�������õ�ʱ�򣬾ͻ��׼���õ�http����
	    ��ʽ���͵��������ˣ�Ȼ�󷵻�һ�������������ڶ�ȡ���������ڴ˴�http����ķ�����Ϣ������http
	    ������getInputStream��ʱ���Ѿ����ͳ�ȥ�ˣ�����httpͷ�����ģ��������getInputStream()����
	    ֮���connection����������ã���httpͷ����Ϣ�����޸ģ�����д��outputStream�������Ľ����޸ģ�
	    ����û��������ˣ�ִ����Щ�����ᵼ���쳣�ķ����� 
 * </pre>
 */
public class HttpConnectionUtils {
	
	protected static Logger LOG = LoggerFactory.getLogger(HttpConnectionUtils.class);
	
	//���ӳ�ʱ ��λ����
	public static int connectTimeout = 10000;
	//��ȡ��ʱ ��λ����
	public static int readTimeout = 3000;
	
	protected static String sessionID = "";
	
	public static String buildURL(String baseURL, Map<String,Object> paramsMap){
		if(paramsMap == null){
			return baseURL;
		}
		StringBuilder builder = new StringBuilder(baseURL);
		for (String key : paramsMap.keySet()) {
			builder.append(builder.indexOf("?") > 0 ? "&" : "?").append(key).append("=").append(String.valueOf(paramsMap.get(key)));
		}
		return builder.toString();
	}
	

	public static HttpURLConnection getPreparedHttpURLConnection(HttpURLConnection httpConn, String method, Map<String, String> headers, String contentType, String charset) throws IOException {
			    
        // �趨����ķ���Ϊָ��������Ĭ����GET 
	    httpConn.setRequestMethod(method);  
        // ��ʾ�ӷ�������ȡ����
		httpConn.setDoInput(true);
		// �����Ƿ��������д����  ;�����post���󣬲���Ҫ���� http�����ڣ������Ҫ��Ϊtrue, Ĭ���������false;  
		httpConn.setDoOutput("POST".equalsIgnoreCase(method) ? true : false ); 
		// Post ������ʹ�û���
		httpConn.setUseCaches(false);  
		 
	    // This method takes effects to
        // every instances of this class.
        // URLConnection.setFollowRedirects��static���������������е�URLConnection����
        // connection.setFollowRedirects(true);
      
        // This methods only
        // takes effacts to this
        // instance.
        // URLConnection.setInstanceFollowRedirects�ǳ�Ա�������������ڵ�ǰ����
	    httpConn.setInstanceFollowRedirects(false);
       
	    //����Session ID ����������ͬ�Ự����
	    httpConn.setRequestProperty("Cookie", sessionID);
	    
	    // ����ͨ�õ��������� (ģ�����������ͷ) 
	    httpConn.setRequestProperty(HttpHeaders.ACCEPT, "*/*");  
	    httpConn.setRequestProperty(HttpHeaders.CONNECTION, "Keep-Alive");  
		httpConn.setRequestProperty(HttpHeaders.USER_AGENT, "Mozilla/4.0 (compatible; MSIE 6.0; Windows 2000)");
		
		/*����headers*/
		if(headers != null) {
			Set<String> keys = headers.keySet();
			for (String key : keys) {
				httpConn.setRequestProperty(key, headers.get(key));
			}
		}
		
		/* 
		 * ���ñ������ӵ�Content-type������Ϊapplication/x-www-form-urlencoded��
		 * ��˼��������urlencoded�������form�������������ǿ��Կ������Ƕ���������ʹ��URLEncoder.encode���б���
		 */	
		if (contentType != null) {
			httpConn.setRequestProperty(HttpHeaders.CONTENT_TYPE, contentType);
		} else {
			httpConn.setRequestProperty(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_FORM_URLENCODED + ";charset=" + charset);
		}
		
		/*
		
		HttpURLConnection�ǻ���HTTPЭ��ģ���ײ�ͨ��socketͨ��ʵ�֡���������ó�ʱ��timeout�����������쳣������£����ܻᵼ�³�����������������ִ�С�����ͨ���������������������Ӧ�ĳ�ʱ��
		System.setProperty("sun.net.client.defaultConnectTimeout", ��ʱ�������ַ���);
		System.setProperty("sun.net.client.defaultReadTimeout", ��ʱ�������ַ���);
		
		���У� sun.net.client.defaultConnectTimeout�����������ĳ�ʱʱ�䣨��λ�����룩
		sun.net.client.defaultReadTimeout����������ȡ���ݵĳ�ʱʱ�䣨��λ�����룩
		
		���磺
		System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		System.setProperty("sun.net.client.defaultReadTimeout", "30000");
		
		JDK 1.5��ǰ�İ汾��ֻ��ͨ������������ϵͳ�������������糬ʱ����1.5�У�������ʹ��HttpURLConnection�ĸ���URLConnection����������������
		setConnectTimeout����������������ʱ����λ�����룩
		setReadTimeout�����ô�������ȡ���ݳ�ʱ����λ�����룩 

		*/
		System.setProperty("sun.net.client.defaultConnectTimeout", connectTimeout+"");
		System.setProperty("sun.net.client.defaultReadTimeout", readTimeout+"");
		httpConn.setConnectTimeout(connectTimeout);
		httpConn.setReadTimeout(readTimeout);
		
		return httpConn;
	}
	
	public static HttpURLConnection getHttpURLConnectionWithGet(String baseURL, Map<String,Object> paramsMap, Map<String, String> headers, String charset) throws IOException {
		URL urlGet = new URL(buildURL(baseURL,paramsMap));
		// �˴���urlConnection����ʵ�����Ǹ���URL�� ����Э��(�˴���http)���ɵ�URLConnection�� ������HttpURLConnection,�ʴ˴���ý���ת�� ΪHttpURLConnection���͵Ķ���,�Ա��õ�HttpURLConnection�����API.����: 
		HttpURLConnection httpConn = (HttpURLConnection) urlGet.openConnection();
						  httpConn = getPreparedHttpURLConnection( httpConn , "GET", headers, ContentType.TEXT_PLAIN, charset);
	    httpConn.connect();
		return httpConn;
	}
	
	public static HttpURLConnection getHttpURLConnectionWithPost(String baseURL, Map<String,Object> paramsMap, Map<String, String> headers, String contentType , String charset) throws IOException {
		URL urlPost = new URL(baseURL);
		// �˴���urlConnection����ʵ�����Ǹ���URL�� ����Э��(�˴���http)���ɵ�URLConnection�� ������HttpURLConnection,�ʴ˴���ý���ת�� ΪHttpURLConnection���͵Ķ���,�Ա��õ�HttpURLConnection�����API.����: 
		HttpURLConnection httpConn = (HttpURLConnection) urlPost.openConnection();
						  httpConn = getPreparedHttpURLConnection( httpConn , "POST", headers, contentType , charset);
		/* 
	        Post�����OutputStreamʵ���ϲ���������������д���ڴ棬�� getInputStream�в�������д���������������Ϊ���������֮ǰ���������ɵ�http requestͷ�ϲ���������http request�����ڴ�ʱ����������������͡�
	        HttpURLConnection.setChunkedStreamingMode�� �����Ըı����ģʽ��������ChunkedStreamingMode��
			���ٵȴ�OutputStream�رպ�����������http requestһ�ι����ͣ������ȷ���http requestͷ�� ��������������·���ķ�ʽʵʱ���͵���������
	                       ʵ�����ǲ����߷�����http���ĵĳ��ȣ�����ģʽ����������������ͽϴ�Ļ����ǲ����� ��ȡ���ȵ����ݣ����ļ��ϴ���
	                       ��readContentFromPost()���Ĳ�ͬ�������˿��СΪ5�ֽ� 
	    */
	    httpConn.setChunkedStreamingMode(5);  
	    /* 
	     * ע�⣬�����getOutputStream����������ʽ����readContentFromPost()����Ĳ�ͬ ����readContentFromPost()����ú�������׼��http request��û��������������κ����� 
	     * ������������������ChunkedStreamingMode��getOutputStream���������connect֮ǰ������  ����http requestͷ���ȷ��͵��������� 
	     */  
	    if(ContentType.APPLICATION_JAVA_SERIALIZED_OBJECT.equalsIgnoreCase(contentType)){
	    	// ���ӣ���urlPost.openConnection()���˵����ñ���Ҫ��connect֮ǰ��ɣ�Ҫע�����connection.getOutputStream�������Ľ���connect(������ͬ���������connect()������  �����ڿ����в�����������connect()Ҳ����)��  
	        httpConn.connect();
	    	//  ����ͨ����������󹹽����������������ʵ����������л��Ķ��� 
	    	ObjectOutputStream output  =  new ObjectOutputStream(httpConn.getOutputStream());
	    	//  ����������д�����ݣ���Щ���ݽ��浽�ڴ滺������ 
	    	output.writeObject(paramsMap);
	    	//  ˢ�¶�������������κ��ֽڶ�д��Ǳ�ڵ����У�Щ��ΪObjectOutputStream�� 
	    	output.flush(); 
	    	//  �ر������󡣴�ʱ������������������д���κ����ݣ���ǰд������ݴ������ڴ滺������, �ڵ����±ߵ�getInputStream()����ʱ�Ű�׼���õ�http������ʽ���͵������� 
	    	output.close(); 
	    }else {
	    	//��֯��������
			StringBuilder buffer = new StringBuilder();
			if(paramsMap != null && paramsMap.size() > 0 ){
				for (String key : paramsMap.keySet()) {
					buffer.append("&").append(key).append("=").append(URLEncoder.encode(String.valueOf(paramsMap.get(key)), charset));
				}
				buffer.deleteCharAt(0);
			}
			// ����ϴ���Ϣ���ֽڴ�С�Լ�����  
            //byte[] postdata = buffer.toString().getBytes();  
	        httpConn.setRequestProperty(HttpHeaders.CONTENT_LENGTH, String.valueOf(buffer.length()));
	        // ���ӣ���urlPost.openConnection()���˵����ñ���Ҫ��connect֮ǰ��ɣ�Ҫע�����connection.getOutputStream�������Ľ���connect(������ͬ���������connect()������  �����ڿ����в�����������connect()Ҳ����)�� 
	        httpConn.connect();
	    	//��������������ָ���URL�������
		    DataOutputStream output = new DataOutputStream(httpConn.getOutputStream());
		    // DataOutputStream.writeBytes���ַ����е�16λ��unicode�ַ���8λ���ַ���ʽд��������
		    output.writeBytes(buffer.toString());
		    //output.write(postdata , 0 , postdata.length );
		    // flush������Ļ���  
		    output.flush();
		    // ����ʱ�������Ѿ��յ���������http request�ˣ�����readContentFromPost()�����Ҫ�ȵ���һ������������յ�http����
		    output.close(); 
		}
		return httpConn;
	}
	
	public static <T> T httpRequestWithGet(String baseURL, ResponseHandler<T> handler) throws IOException {
		return httpRequestWithGet(baseURL, null, handler);
	}

	public static <T> T httpRequestWithGet(String baseURL,
                                           Map<String, Object> paramsMap, ResponseHandler<T> handler)
			throws IOException {
		return httpRequestWithGet(baseURL, paramsMap, ContentType.UTF_8, handler);
	}

	public static <T> T httpRequestWithGet(String baseURL,
                                           Map<String, Object> paramsMap, String charset,
                                           ResponseHandler<T> handler) throws IOException {
		return httpRequestWithGet(baseURL, paramsMap, charset, null, handler);
	}

	public static <T> T httpRequestWithGet(String baseURL,
                                           Map<String, Object> paramsMap, String charset,
                                           Map<String, String> headers, ResponseHandler<T> handler)
			throws IOException {
		// ����Ĭ�ϵ�HttpURLConnectionʵ��.
		HttpURLConnection httpConn = getHttpURLConnectionWithGet(baseURL, paramsMap, headers, charset);
		try {
			// ��HttpURLConnection����Ԥ����
			handler.preHandle(httpConn);
			// �������յ���Ӧ���
			return handler.handleResponse(httpConn, charset);
		} finally {
			//��һ�������ͺ��ȡSessionID
			sessionID = getSessionID(httpConn);
			//�ͷ�����
			HttpIOUtils.closeConnect(httpConn);
		}
	}

	public static <T> T httpRequestWithPost(String baseURL,
                                            Map<String, Object> paramsMap, ResponseHandler<T> handler)
			throws IOException {
		return httpRequestWithPost(baseURL, paramsMap, ContentType.UTF_8, handler);
	}

	/**
	 * ����post��ʽ������Content-Type Ϊ application/x-www-form-urlencoded
	 */
	public static <T> T httpRequestWithPost(String baseURL,
                                            Map<String, Object> paramsMap, String charset,
                                            ResponseHandler<T> handler) throws IOException {
		return httpRequestWithPost(baseURL, paramsMap, charset, ContentType.APPLICATION_FORM_URLENCODED + "; charset=" + charset, handler);
	}

	public static <T> T httpRequestWithPost(String baseURL, Map<String, Object> paramsMap, final String charset, String contentType, ResponseHandler<T> handler) throws IOException {
		return httpRequestWithPost(baseURL, paramsMap, charset, contentType, null, handler);
	}

	public static <T> T httpRequestWithPost(String baseURL,
                                            Map<String, Object> paramsMap, String charset, String contentType,
                                            Map<String, String> headers, ResponseHandler<T> handler) throws IOException {
		// ����Ĭ�ϵ�HttpURLConnectionʵ��.
		HttpURLConnection httpConn = getHttpURLConnectionWithPost(baseURL, paramsMap, headers, contentType, charset);
		try {
			// ��HttpURLConnection����Ԥ����
			handler.preHandle(httpConn);
			// �������յ���Ӧ���
			return handler.handleResponse(httpConn, charset);
		} finally {
			//��һ�������ͺ��ȡSessionID
			sessionID = getSessionID(httpConn);
			//�ͷ�����
			HttpIOUtils.closeConnect(httpConn);
		}
	}
	
	/**
	 * 
	 * ******************************************************************
	 * @description	�� ��һ�������ͺ��ȡSessionID
	 * @author 		�� <a href="mailto:hnxyhcwdl1003@163.com">vindell</a>
	 * @date 		��Dec 15, 2016 2:53:57 PM
	 * @param httpConn
	 * @return
	 * ******************************************************************
	 */
	public static String getSessionID(HttpURLConnection httpConn){
		// Get Session ID
		String key = "";
		String sessionId = "";
		if (httpConn != null) {
			for (int i = 1; (key = httpConn.getHeaderFieldKey(i)) != null; i++) {
				if (key.equalsIgnoreCase("set-cookie")) {
					sessionId = httpConn.getHeaderField(key);
					sessionId = sessionId.substring(0, sessionId.indexOf(";"));
				}
			}
		}
		return sessionId;
	}
	
}
