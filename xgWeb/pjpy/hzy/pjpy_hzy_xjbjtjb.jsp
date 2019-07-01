<%@ page language="java" contentType="text/html; charset=GBK" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
  	
    <title><bean:message key="lable.title"/></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	

	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  
  <body>
    <html:form action="/">
       <table width="100%"  border="1">
		  <tr>
		    <th height="33" colspan="2" scope="col">班级</th>
		    <th width="25%" scope="col">&nbsp;</th>
		    <th width="22%" scope="col">班长</th>
		    <th width="34%" scope="col">&nbsp;</th>
		  </tr>
		  <tr>
		    <th height="34" colspan="2" scope="row">学生人数</th>
		    <td>&nbsp;</td>
		    <td><div align="center">班主任</div></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <th width="8%" height="129" scope="row">
		    <p>主</p>
		    <p>要</p>
		    <p>事</p>
		    <p>迹</p></th>
		    <th colspan="4" scope="row">&nbsp;</th>
		  </tr>
		  <tr>
		    <th height="76" scope="row">
		    <p>系</p>
		    <p>（院）</p>
		    <p>意</p>
		    <p>见</p></th>
		    <th height="76" colspan="4" scope="row">&nbsp;</th>
		  </tr>
		  <tr>
		    <th height="65" scope="row">
		    <p>学</p>
		    <p>校</p>
		    <p>意</p>
		    <p>见</p></th>
		    <th height="65" colspan="4" scope="row">&nbsp;</th>
		  </tr>
	</table>
    </html:form>
  </body>
</html:html>
