<%@ page contentType="image/jpg" import="java.io.*"%>
<jsp:directive.page import="com.sun.image.codec.jpeg.JPEGImageDecoder"/>
<jsp:directive.page import="com.sun.image.codec.jpeg.JPEGImageEncoder"/>
<jsp:directive.page import="com.sun.image.codec.jpeg.JPEGCodec"/>
<jsp:directive.page import="java.awt.image.BufferedImage"/>

<%
	response.setHeader("Pragma", "No-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control","max-age=100000");
	response.reset();
	String path = request.getParameter("filePath");
	File file = new File(path);

	if (file.exists()) {
		try {
			InputStream is = new FileInputStream(file);
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(is);
			BufferedImage image = decoder.decodeAsBufferedImage();
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
			encoder.encode(image);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
%>

<%--<%
	response.setHeader("Pragma", "No-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control","max-age=100000");
	
	String path = request.getParameter("filePath");
	File file = new File(path);
	
	ServletOutputStream os =  response.getOutputStream();
	BufferedInputStream imageStream = new BufferedInputStream(new FileInputStream(file));
	
	try{
		BufferedImage image = ImageIO.read(imageStream);
	    JPEGImageEncoder encoder =  JPEGCodec.createJPEGEncoder(os);
	    encoder.encode(image);
	    imageStream.close();
	    
	    os.flush();
		os.close();
		os=null;
		out.clear();
		out=pageContext.pushBody();
	    
    } catch(IOException e){
    	e.printStackTrace();
    }
%>
--%>