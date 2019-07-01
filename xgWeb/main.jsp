<%@ page language="java" contentType="text/html; charset=GBK"%>
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
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
<head>
<title><bean:message key="lable.title" /></title>
<meta http-equiv="Pragma" http-equiv="no-cache" />
<meta http-equiv="Cache-Control" http-equiv="no-cache" />
<meta http-equiv="Expires" http-equiv="0" />
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="正方软件 zfsoft" />
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
</head>
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
<script language=javascript>
window.moveTo(0,0);
var sx=screen.availWidth;
var sy=screen.availHeight;
window.resizeTo(sx,sy);
document.write(""+"<frameset rows=75,*,24 frameborder=NO border=0 framespacing=0 cols=*  ordercolor=#6699cc>");
document.write(""+"<frame name=topFrame scrolling=NO noresize src='initMenu.do?act=top'>");

if(document.all) document.write(""+"<frameset cols=180,* frameborder=NO border=0 framespacing=0 name=forum  rows=*>"+"<frame name=leftFrame frameborder=no src=left_js.htm scrolling=no noresize marginwidth=0 marginheight=0>");
else document.write(""+"<frameset cols=180,* frameborder=NO border=0 framespacing=0 name=forum rows=*>"+"<frame name=midFrame frameborder=no src=initMenu.do?act=left scrolling=no noresize marginwidth=0 marginheight=0>");

    document.write(""+"<frame name=mainFrame src='about:blank' scrolling=auto>");
    document.write(""+"</frameset>");
    document.write(""+"<frame name=bottomFrame scrolling=NO noresize src=bottom.jsp>");
 document.write(""+"</frameset>");
</script>
<noframes>
<body onBlur="this.focus()"> 
<p>&nbsp; </p> 
<p>&nbsp; </p> 
<p>&nbsp; </p> 
<center>
   您的是浏览器不支持框架，禁止访问！
</center> 
</body>
</noframes>
</html>
