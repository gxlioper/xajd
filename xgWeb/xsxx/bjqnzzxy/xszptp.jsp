<%@ page contentType="image/jpeg"
	import="xgxt.comm.*,xgxt.base.*,java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*,xgxt.DAO.*,
	com.sun.image.codec.jpeg.JPEGImageEncoder"%>
<%@ page contentType="image/jpeg" import="java.io.OutputStream"%>
<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	String tableName = "xsxx_xjxxdjb";
	String pkKey = "xh";
	String pkValue = request.getParameter("pkValue");
	String filePath = request.getParameter("filePath");
	String fileName = request.getParameter("fileName");
	
	FileManage.downLoadFile(filePath, fileName, response);
%>
