<%@ page contentType="image/jpeg"
	import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*,xgxt.DAO.*,
	com.sun.image.codec.jpeg.JPEGImageEncoder"%>
<%@ page contentType="image/jpeg" import="java.io.OutputStream"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	response.reset();
	Object userNameObj = session.getAttribute("userName");
	Object userTypeObj = session.getAttribute("userType");
	Object userOnLineObj = session.getAttribute("userOnLine");
	String xh = request.getParameter("xh");
	String jyweb = (String)session.getAttribute("jyweb");
	DAO dao = DAO.getInstance();
	if(session != null 
	     && (userTypeObj != null 
	           && userTypeObj.toString().equalsIgnoreCase("stu") 
	           && userNameObj.equals(xh)) 
	     && userNameObj != null ){
		dao.getPic(xh,request, response);
	} else if(session != null 
	     && (userOnLineObj != null 
	           && userOnLineObj.toString().equalsIgnoreCase("teacher")) 
	     && userNameObj != null ){
	   dao.getPic(xh,request, response);
	} else if("yes".equals(jyweb)){
		dao.getPic(xh,request, response);
	}
	out.clear();
	out=pageContext.pushBody();
%>
