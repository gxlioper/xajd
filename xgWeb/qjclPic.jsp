<%@ page contentType="image/jpeg" import="xgxt.DAO.PicDAO"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.reset();
request.setAttribute("gzip", "false"); 
String id = request.getParameter("id");
//ImageIO.write(image, "JPEG", response.getOutputStream());
PicDAO picDAO = new PicDAO();
picDAO.getQjclPic(id,response,request);
//
pageContext.pushBody();  
//return;  
%>
