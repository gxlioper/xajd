<%@ page language="java"%>
<%@ page import=" javax.mail.*"%>
<%@ page import=" javax.mail.internet.*"%>
<%@ page import=" java.util.*"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>sendMail.jsp</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<% 

try{ 

Properties props = new Properties(); 

Session sendMailSession; 

//Store store; 
@SuppressWarnings("unused")
Transport transport; 

sendMailSession = Session.getInstance(props, null); 

props.put("mail.smtp.host", "smtp.163.com"); 

Message newMessage = new MimeMessage(sendMailSession); 

newMessage.setFrom(new InternetAddress(request.getParameter("from"))); 

newMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(request.getParameter("to"))); 

newMessage.setSubject(request.getParameter("subject")); 

newMessage.setSentDate(new Date()); 

newMessage.setText(request.getParameter("text")); 

transport = sendMailSession.getTransport("smtp"); 

Transport.send(newMessage); 

%>
<body>
 This a struts page. <br> 
<% 

} 

catch(MessagingException m) 

{ 

out.println(m.toString()); 

} 

%> 
</body>
</html>
