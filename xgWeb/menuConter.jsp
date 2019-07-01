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
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="Content-Language" content="GBK" />
<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<body style=" border:#ECF6F5 solid 4px;padding:8px" onselectstart="return false" ondragstart="return false" onbeforecopy="return false" oncopy="document.selection.empty()" onselect="document.selection.empty()"> 
<html:form action="/initMenu"> 
<input type="hidden" value="<bean:write name="menuList"/>" name="menuTop" /> </html:form> 
<script language="javascript">
				document.body.style.backgroundColor = "#FBFDFF";
		    	var menuText = document.forms[0].menuTop.value;
				var i = 1;
				var j = 1;
				var gnmk = new Array();
				var printOut = "<div style='height:5px'>&nbsp;</div>";
				gnmk = menuText.split(splitSignOne);
				for (i = 1; i < gnmk.length; i++) {
					gnmkcf = gnmk[i].split(splitSignTwo);
					printOut += "<div style='height:20px'><a href='initMenu.do?act=left&menuTop=";
					printOut += gnmkcf[1];
					printOut += "' target='midFrame' title='";
					printOut += gnmkcf[2];
					printOut += "'>";
					printOut += gnmkcf[2];
					printOut += "</a></div>";
				}
				document.write(printOut);
				var h = (i*20+40) + "px";
				parent.menuCont.style.height = h;
		  </script> 
</body>
</html>
