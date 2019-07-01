<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    
    <title><bean:message key="lable.title" /></title>

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
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
  <body>
  	<div id="rsT">
    <p align="center">学生岗位申请表 </p>
	<table class="tbstyle" id="tsT" align="center" width="70%">
  		<tr>
    		<td width="19%"><p align="right">* 学号： </p></td>
    		<td width="17%">&nbsp;&nbsp;</td>
    		<td width="29%"><p align="right">* 岗位名称： </p></td>
    		<td width="33%">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="19%"><p align="right">姓名： </p></td>
    		<td width="17%">&nbsp;</td>
    		<td width="29%"><p align="right">年度： </p></td>
    		<td width="33%">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="19%"><p align="right">性别： </p></td>
    		<td width="17%">&nbsp;</td>
    		<td width="29%"><p align="right">学年： </p></td>
    		<td width="33%">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="19%"><p align="right">年级： </p></td>
    		<td width="17%">&nbsp;</td>
    		<td width="29%"><p align="right">学期： </p></td>
    		<td width="33%">&nbsp;</td>
  		</tr>
  		<tr>
   			<td width="19%"><p align="right"><bean:message key="lable.xsgzyxpzxy" />： </p></td>
    		<td width="17%">&nbsp;</td>
    		<td width="29%"><p align="right">联系电话： </p></td>
    		<td width="33%">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="19%"><p align="right">专业： </p></td>
    		<td width="17%">&nbsp;</td>
    		<td width="29%"><p align="right">是否困难生： </p></td>
    		<td width="33%"><p align="center">（必须是贫困生才能申请） </p></td>
  		</tr>
  		<tr>
    		<td width="19%"><p align="right">班级： </p></td>
    		<td width="17%">&nbsp;</td>
    		<td width="29%">&nbsp;</td>
    		<td width="33%">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="19%"><p align="right">申请理由： </p></td>
    		<td width="80%" colspan="3">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="19%"><p align="right">备注： </p></td>
    		<td width="80%" colspan="3">&nbsp;</td>
  		</tr>
	</table>
	</div>
	<div class="buttontool" align="center">
		<button type="button" class="button2" onclick="expAppTab('rsT','','')">
				打 印 报 表
		</button>
	</div>
  </body>
</html:html>
