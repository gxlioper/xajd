<%@ page contentType="image/jpeg"
	import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*,com.sun.image.codec.jpeg.JPEGCodec,com.sun.image.codec.jpeg.JPEGImageEncoder"%>
<%@ page contentType="image/jpeg"
	import="java.io.OutputStream,xgxt.DAO.PicDAO"%>
<jsp:directive.page import="xgxt.action.Base" />
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	request.setAttribute("gzip", "false");
	String xh = request.getParameter("xh");
	String lx = request.getParameter("lx");
	if (Base.isNull(lx)) {
		lx = "stu";
	}
	//ImageIO.write(image, "JPEG", response.getOutputStream());
	PicDAO picDAO = new PicDAO();
	picDAO.getPic2(xh, response,request, lx);
	out.clear();
	out=pageContext.pushBody();
	//
	//out = pageContext.pushBody();  
	//return;
%>
