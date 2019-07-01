<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.Map.Entry" %>
<%@ page import="org.xml.sax.*" %>
<%@ page import="org.xml.sax.helpers.*" %>

<%@ include file="config.jsp" %>

<%
	response.setHeader("P3P","CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
%>

<%!

public static class Base64 {
	/**
	 * encode characters table.
	 */
	private static final byte[] ENC_TAB = { (byte)'A', (byte)'B', (byte)'C', (byte)'D', (byte)'E', (byte)'F', (byte)'G', (byte)'H', (byte)'I', (byte)'J', (byte)'K', (byte)'L', (byte)'M', (byte)'N', (byte)'O', (byte)'P', (byte)'Q', (byte)'R', (byte)'S', (byte)'T', (byte)'U', (byte)'V', (byte)'W', (byte)'X', (byte)'Y', (byte)'Z', (byte)'a', (byte)'b', (byte)'c', (byte)'d', (byte)'e', (byte)'f', (byte)'g', (byte)'h', (byte)'i', (byte)'j', (byte)'k', (byte)'l', (byte)'m', (byte)'n', (byte)'o', (byte)'p', (byte)'q', (byte)'r', (byte)'s', (byte)'t', (byte)'u', (byte)'v', (byte)'w', (byte)'x', (byte)'y', (byte)'z', (byte)'0', (byte)'1', (byte)'2', (byte)'3', (byte)'4', (byte)'5', (byte)'6', (byte)'7', (byte)'8', (byte)'9', (byte)'+', (byte)'/' };
	
	/**
	 * decode characters table.
	 */
	private static final byte[] DEC_TAB;
	static {
	    DEC_TAB = new byte[128];
	    for (int i = 0; i < 128; i++)
	    {
	        DEC_TAB[i] = (byte)-1;
	    }
	    for (int i = 'A'; i <= 'Z'; i++)
	    {
	        DEC_TAB[i] = (byte)(i - 'A');
	    }
	    for (int i = 'a'; i <= 'z'; i++)
	    {
	        DEC_TAB[i] = (byte)(i - 'a' + 26);
	    }
	    for (int i = '0'; i <= '9'; i++)
	    {
	        DEC_TAB[i] = (byte)(i - '0' + 52);
	    }
	    DEC_TAB['+'] = 62;
	    DEC_TAB['/'] = 63;
	}
	
	/**
	 * encode byte array data to base 64 hex string data.
	 * @param data byte array data.
	 * @return base 64 hex string data. use new String("UTF-8") to get the String format.
	 */
	public static byte[] encode(byte[] data)
	{
	    byte[] bytes;
	    int modulus = data.length % 3;
	    if (modulus == 0)
	    {
	        bytes = new byte[(4 * data.length) / 3];
	    }
	    else
	    {
	        bytes = new byte[4 * ((data.length / 3) + 1)];
	    }
	    int dataLength = (data.length - modulus);
	    int a1;
	    int a2;
	    int a3;
	    for (int i = 0, j = 0; i < dataLength; i += 3, j += 4)
	    {
	        a1 = data[i] & 0xff;
	        a2 = data[i + 1] & 0xff;
	        a3 = data[i + 2] & 0xff;
	        bytes[j] = ENC_TAB[(a1 >>> 2) & 0x3f];
	        bytes[j + 1] = ENC_TAB[((a1 << 4) | (a2 >>> 4)) & 0x3f];
	        bytes[j + 2] = ENC_TAB[((a2 << 2) | (a3 >>> 6)) & 0x3f];
	        bytes[j + 3] = ENC_TAB[a3 & 0x3f];
	    }
	    int b1;
	    int b2;
	    int b3;
	    int d1;
	    int d2;
	    switch (modulus)
	    {
	    case 0: /* nothing left to do */
	        break;
	    case 1:
	        d1 = data[data.length - 1] & 0xff;
	        b1 = (d1 >>> 2) & 0x3f;
	        b2 = (d1 << 4) & 0x3f;
	        bytes[bytes.length - 4] = ENC_TAB[b1];
	        bytes[bytes.length - 3] = ENC_TAB[b2];
	        bytes[bytes.length - 2] = (byte)'=';
	        bytes[bytes.length - 1] = (byte)'=';
	        break;
	    case 2:
	        d1 = data[data.length - 2] & 0xff;
	        d2 = data[data.length - 1] & 0xff;
	        b1 = (d1 >>> 2) & 0x3f;
	        b2 = ((d1 << 4) | (d2 >>> 4)) & 0x3f;
	        b3 = (d2 << 2) & 0x3f;
	        bytes[bytes.length - 4] = ENC_TAB[b1];
	        bytes[bytes.length - 3] = ENC_TAB[b2];
	        bytes[bytes.length - 2] = ENC_TAB[b3];
	        bytes[bytes.length - 1] = (byte)'=';
	        break;
	    }
	    return bytes;
	}
	
	/**
	 * decode base 64 string data to byte data array.
	 * @param data base 64 string data.
	 * @return byte data array.
	 */
	public static byte[] decode(byte[] data)
	{
	    byte[] bytes;
	    byte b1;
	    byte b2;
	    byte b3;
	    byte b4;
	    data = discardNonBase64Bytes(data);
	    if (data[data.length - 2] == '=')
	    {
	        bytes = new byte[(((data.length / 4) - 1) * 3) + 1];
	    }
	    else if (data[data.length - 1] == '=')
	    {
	        bytes = new byte[(((data.length / 4) - 1) * 3) + 2];
	    }
	    else
	    {
	        bytes = new byte[((data.length / 4) * 3)];
	    }
	    for (int i = 0, j = 0; i < (data.length - 4); i += 4, j += 3)
	    {
	        b1 = DEC_TAB[data[i]];
	        b2 = DEC_TAB[data[i + 1]];
	        b3 = DEC_TAB[data[i + 2]];
	        b4 = DEC_TAB[data[i + 3]];
	        bytes[j] = (byte)((b1 << 2) | (b2 >> 4));
	        bytes[j + 1] = (byte)((b2 << 4) | (b3 >> 2));
	        bytes[j + 2] = (byte)((b3 << 6) | b4);
	    }
	    if (data[data.length - 2] == '=')
	    {
	        b1 = DEC_TAB[data[data.length - 4]];
	        b2 = DEC_TAB[data[data.length - 3]];
	        bytes[bytes.length - 1] = (byte)((b1 << 2) | (b2 >> 4));
	    }
	    else if (data[data.length - 1] == '=')
	    {
	        b1 = DEC_TAB[data[data.length - 4]];
	        b2 = DEC_TAB[data[data.length - 3]];
	        b3 = DEC_TAB[data[data.length - 2]];
	        bytes[bytes.length - 2] = (byte)((b1 << 2) | (b2 >> 4));
	        bytes[bytes.length - 1] = (byte)((b2 << 4) | (b3 >> 2));
	    }
	    else
	    {
	        b1 = DEC_TAB[data[data.length - 4]];
	        b2 = DEC_TAB[data[data.length - 3]];
	        b3 = DEC_TAB[data[data.length - 2]];
	        b4 = DEC_TAB[data[data.length - 1]];
	        bytes[bytes.length - 3] = (byte)((b1 << 2) | (b2 >> 4));
	        bytes[bytes.length - 2] = (byte)((b2 << 4) | (b3 >> 2));
	        bytes[bytes.length - 1] = (byte)((b3 << 6) | b4);
	    }
	    return bytes;
	}
	
	/**
	 * decode base 64 string to byte array data.
	 * @param data base 64 string.
	 * @return byte array data.
	 */
	public static byte[] decode(String data)
	{
	    byte[] bytes;
	    byte b1;
	    byte b2;
	    byte b3;
	    byte b4;
	    data = discardNonBase64Chars(data);
	    if (data.charAt(data.length() - 2) == '=')
	    {
	        bytes = new byte[(((data.length() / 4) - 1) * 3) + 1];
	    }
	    else if (data.charAt(data.length() - 1) == '=')
	    {
	        bytes = new byte[(((data.length() / 4) - 1) * 3) + 2];
	    }
	    else
	    {
	        bytes = new byte[((data.length() / 4) * 3)];
	    }
	    for (int i = 0, j = 0; i < (data.length() - 4); i += 4, j += 3)
	    {
	        b1 = DEC_TAB[data.charAt(i)];
	        b2 = DEC_TAB[data.charAt(i + 1)];
	        b3 = DEC_TAB[data.charAt(i + 2)];
	        b4 = DEC_TAB[data.charAt(i + 3)];
	        bytes[j] = (byte)((b1 << 2) | (b2 >> 4));
	        bytes[j + 1] = (byte)((b2 << 4) | (b3 >> 2));
	        bytes[j + 2] = (byte)((b3 << 6) | b4);
	    }
	    if (data.charAt(data.length() - 2) == '=')
	    {
	        b1 = DEC_TAB[data.charAt(data.length() - 4)];
	        b2 = DEC_TAB[data.charAt(data.length() - 3)];
	        bytes[bytes.length - 1] = (byte)((b1 << 2) | (b2 >> 4));
	    }
	    else if (data.charAt(data.length() - 1) == '=')
	    {
	        b1 = DEC_TAB[data.charAt(data.length() - 4)];
	        b2 = DEC_TAB[data.charAt(data.length() - 3)];
	        b3 = DEC_TAB[data.charAt(data.length() - 2)];
	        bytes[bytes.length - 2] = (byte)((b1 << 2) | (b2 >> 4));
	        bytes[bytes.length - 1] = (byte)((b2 << 4) | (b3 >> 2));
	    }
	    else
	    {
	        b1 = DEC_TAB[data.charAt(data.length() - 4)];
	        b2 = DEC_TAB[data.charAt(data.length() - 3)];
	        b3 = DEC_TAB[data.charAt(data.length() - 2)];
	        b4 = DEC_TAB[data.charAt(data.length() - 1)];
	        bytes[bytes.length - 3] = (byte)((b1 << 2) | (b2 >> 4));
	        bytes[bytes.length - 2] = (byte)((b2 << 4) | (b3 >> 2));
	        bytes[bytes.length - 1] = (byte)((b3 << 6) | b4);
	    }
	    return bytes;
	}
	
	private static byte[] discardNonBase64Bytes(byte[] data)
	{
	    byte[] temp = new byte[data.length];
	    int bytesCopied = 0;
	    for (int i = 0; i < data.length; i++)
	    {
	        if (isValidBase64Byte(data[i]))
	        {
	            temp[bytesCopied++] = data[i];
	        }
	    }
	    byte[] newData = new byte[bytesCopied];
	    System.arraycopy(temp, 0, newData, 0, bytesCopied);
	    return newData;
	}
	
	private static String discardNonBase64Chars(String data)
	{
	    StringBuffer sb = new StringBuffer();
	    int length = data.length();
	    for (int i = 0; i < length; i++)
	    {
	        if (isValidBase64Byte((byte)(data.charAt(i))))
	        {
	            sb.append(data.charAt(i));
	        }
	    }
	    return sb.toString();
	}
	
	private static boolean isValidBase64Byte(byte b)
	{
	    if (b == '=')
	    {
	        return true;
	    }
	    else if ((b < 0) || (b >= 128))
	    {
	        return false;
	    }
	    else if (DEC_TAB[b] == -1)
	    {
	        return false;
	    }
	    return true;
	}
}

public static class CommonUtil {
	public static boolean isBlank(String str) {
		return str==null || str.equals("");
	}
	public static String encodeServiceURI(String serviceURI){
		if (isBlank(serviceURI)) return "";
		
		byte[] result = Base64.encode(serviceURI.getBytes());
		String encodedServiceURI = new String(result);
		return encodedServiceURI;
	}
	
	public static String decodeServiceURI(String encodedServiceURI){
		if (isBlank(encodedServiceURI)) return "";
		
		byte[] result = Base64.decode(encodedServiceURI);
		String serviceURI = new String(result);
		return serviceURI;
	}
	
	public static boolean isCasAlive(String url, int timeout)
    {
		if (CommonUtil.isBlank(url))
			return true;
		
        String response = PerformHttpGet(url, true, timeout);

        if (response != null
            && response.indexOf("<input id=\"username\" name=\"username\"") > 0
            && response.indexOf("<input name =\"password\"") > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
	
	public static String validateTicket(String url){
		if (CommonUtil.isBlank(url))
			return null;
		
        String response = PerformHttpGet(url, true, 0);
        
        return response;
	}
	
	public static String PerformHttpGet(String url, boolean requireHttp200, int timeout) {
		String responseBody = null;
		
		HttpURLConnection conn = null;
		try {
			URL connUrl = new URL(url);
			
            conn = (HttpURLConnection) connUrl.openConnection();
            
            if (timeout > 0)
            	conn.setConnectTimeout(timeout);
            
            final BufferedReader in = 
            	new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line;
            final StringBuilder stringBuffer = new StringBuilder();

            while ((line = in.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            responseBody = stringBuffer.toString();
        } catch (final MalformedURLException e) {
            
        } catch (final Exception e) {

        } finally {
            if (conn != null && conn instanceof HttpURLConnection) {
                ((HttpURLConnection)conn).disconnect();
            }
        }
		
		return responseBody;
	}
	public static String PerformHttpPost(String url, Map<String, String> data, String encoding){ 
		String responseBody = null;
		
		HttpURLConnection conn = null;
		try {
			
			StringBuffer params = new StringBuffer();
			for (String key : data.keySet()) {
				String value = data.get(key);
				params.append(key);
				params.append("=");
				params.append(URLEncoder.encode(value, encoding));
				params.append("&");
			}

			if (params.length() > 0) {
				params = params.deleteCharAt(params.length() - 1);
			}
			
			URL connUrl = new URL(url);
			conn = (HttpURLConnection) connUrl.openConnection();
			
			//conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			//conn.setRequestProperty("Charsert", "UTF-8");
			conn.setDoOutput(true);  
			//conn.setDoInput(true);  
			conn.setUseCaches(false);  
			conn.setRequestMethod("POST");
			
			byte[] b = params.toString().getBytes();
			conn.getOutputStream().write(b, 0, b.length);
			conn.getOutputStream().flush();
			conn.getOutputStream().close();
            
			InputStream in = conn.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, encoding));
			String tempLine = rd.readLine();
			StringBuffer tempStr = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				tempStr.append(tempLine);
				tempStr.append(crlf);
				tempLine = rd.readLine();
			}
			responseBody = tempStr.toString();
			rd.close();
			in.close();
		} catch (final MalformedURLException e) {
            //e.printStackTrace();
        } catch (final Exception e) {
        	//e.printStackTrace();
        } finally {
            if (conn != null && conn instanceof HttpURLConnection) {
                ((HttpURLConnection)conn).disconnect();
            }
        }
		
		return responseBody;
	}
	
	
}

public static class XmlUtils {
    /**
     * Get an instance of an XML reader from the XMLReaderFactory.
     *
     * @return the XMLReader.
     */
    public static XMLReader getXmlReader() {
        try {
            return XMLReaderFactory.createXMLReader();
        } catch (final SAXException e) {
            throw new RuntimeException("Unable to create XMLReader", e);
        }
    }

    /**
     * Retrieve the text for a group of elements. Each text element is an entry
     * in a list.
     * <p>This method is currently optimized for the use case of two elements in a list.
     *
     * @param xmlAsString the xml response
     * @param element     the element to look for
     * @return the list of text from the elements.
     */
    public static List<String> getTextForElements(final String xmlAsString,
                                          final String element) {
        final List<String> elements = new ArrayList<String>(2);
        final XMLReader reader = getXmlReader();

        final DefaultHandler handler = new DefaultHandler() {

            private boolean foundElement = false;

            private StringBuilder buffer = new StringBuilder();

            public void startElement(final String uri, final String localName,
                                     final String qName, final Attributes attributes)
                    throws SAXException {
                if (localName.equals(element)) {
                    this.foundElement = true;
                }
            }

            public void endElement(final String uri, final String localName,
                                   final String qName) throws SAXException {
                if (localName.equals(element)) {
                    this.foundElement = false;
                    elements.add(this.buffer.toString());
                    this.buffer = new StringBuilder();
                }
            }

            public void characters(char[] ch, int start, int length)
                    throws SAXException {
                if (this.foundElement) {
                    this.buffer.append(ch, start, length);
                }
            }
        };

        reader.setContentHandler(handler);
        reader.setErrorHandler(handler);

        try {
            reader.parse(new InputSource(new StringReader(xmlAsString)));
        } catch (final Exception e) {
            //LOG.error(e, e);
            return null;
        }

        return elements;
    }

    /**
     * Retrieve the text for a specific element (when we know there is only
     * one).
     *
     * @param xmlAsString the xml response
     * @param element     the element to look for
     * @return the text value of the element.
     */
    public static String getTextForElement(final String xmlAsString,
                                           final String element) {
        final XMLReader reader = getXmlReader();
        final StringBuffer buffer = new StringBuffer();

        final DefaultHandler handler = new DefaultHandler() {

            private boolean foundElement = false;

            public void startElement(final String uri, final String localName,
                                     final String qName, final Attributes attributes)
                    throws SAXException {
                if (localName.equals(element)) {
                    this.foundElement = true;
                }
            }

            public void endElement(final String uri, final String localName,
                                   final String qName) throws SAXException {
                if (localName.equals(element)) {
                    this.foundElement = false;
                }
            }

            public void characters(char[] ch, int start, int length)
                    throws SAXException {
                if (this.foundElement) {
                    buffer.append(ch, start, length);
                }
            }
        };

        reader.setContentHandler(handler);
        reader.setErrorHandler(handler);

        try {
            reader.parse(new InputSource(new StringReader(xmlAsString)));
        } catch (final Exception e) {
            //LOG.error(e, e);
            return null;
        }

        return buffer.toString();
    }
    
	public static Map<String,Object> extractCustomAttributes(final String xml) {
    	final int pos1 = xml.indexOf("<cas:attributes>");
    	final int pos2 = xml.indexOf("</cas:attributes>");
    	
    	if (pos1 == -1) {
    		return Collections.emptyMap();
    	}
    	
    	final String attributesText = xml.substring(pos1+16, pos2);
    	
    	final Map<String,Object> attributes = new HashMap<String,Object>();
    	final BufferedReader br = new BufferedReader(new StringReader(attributesText));
    	
    	String line;
    	final List<String> attributeNames = new ArrayList<String>();
    	try {
	    	while ((line = br.readLine()) != null) {
	    		final String trimmedLine = line.trim();
	    		if (trimmedLine.length() > 0) {
		    		final int leftPos = trimmedLine.indexOf(":");
		    		final int rightPos = trimmedLine.indexOf(">");
		    		attributeNames.add(trimmedLine.substring(leftPos+1, rightPos));
	    		}
	    	}
	    	br.close();
    	} catch (final IOException e) {
    		//ignore
    	}

        for (final String name : attributeNames) {
            final List<String> values = XmlUtils.getTextForElements(xml, name);

            if (values.size() == 1) {
                attributes.put(name, values.get(0));
            } else {
                attributes.put(name, values);
            }
    	}
    	
    	return attributes;
    }
    
}
%>