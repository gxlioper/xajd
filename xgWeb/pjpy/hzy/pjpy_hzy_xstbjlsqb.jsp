<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
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
    <html:form action="">
     <div align="center" style="font-size:18px; font-weight: bold;">杭州职业技术<bean:message key="lable.xsgzyxpzxy" /><br>学生特别奖励申请表</div>
<div>__________系（院）</div>
<table width="100%"  border="1">
  <tr>
    <th colspan="2" scope="col">班级</th>
    <th colspan="2" scope="col">&nbsp;</th>
    <th width="11%" scope="col">姓名</th>
    <th width="12%" scope="col">&nbsp;</th>
    <th width="8%" scope="col">性别</th>
    <th width="8%" scope="col">&nbsp;</th>
    <th width="8%" scope="col">职务</th>
    <th width="17%" scope="col">&nbsp;</th>
  </tr>
  <tr>
    <th colspan="3" scope="row">获奖名称及级别</th>
    <td colspan="2">&nbsp;</td>
    <td colspan="2">获奖日期</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <th width="7%" scope="row"><p>申</p>
    <p>请</p>
    <p>理</p>
    <p>由</p></th>
    <th colspan="9" scope="row">年 月 日 </th>
  </tr>
  <tr>
    <th scope="row"><p>系</p>
    <p>（院）</p>
    <p>意</p>
    <p>见</p></th>
    <th colspan="9" scope="row"><p>签名盖章</p>
    <p>年 月 日 </p></th>
  </tr>
  <tr>
    <th scope="row"><p>学</p>
    <p>生</p>
    <p>处</p>
    <p>意</p>
    <p>见</p></th>
    <th colspan="9" scope="row"><p>签名盖章</p>
    <p>年 月 日 </p></th>
  </tr>
</table>
注：1.附获奖材料 2.经系、院盖章，领导签字方可生效
    
    </html:form>
  </body>
</html:html>
