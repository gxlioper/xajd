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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/pjpyjgsdxwh" method="post">
		
			<p align="center"> <font size="4"><b>井冈山大学文明班级报批表</b></font></p>
			&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xymc"/>&nbsp;院（系）&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="nj"/>&nbsp;级&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="bjmc"/>&nbsp;班&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年  &nbsp;&nbsp; 月 &nbsp;&nbsp; 日
			<table align="center" width="98%" id="rsTable" class="tbstyle">
				<tr style="height: 35px;">
				    <td style="width:15%">&nbsp;&nbsp;班级名称</td>
				    <td>&nbsp;
				    	&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="bjmc"/>
				    </td>
				  </tr>
				  <tr style="height:350px">
				    <td><p align="left">&nbsp;&nbsp;先进 </p>
				    &nbsp;&nbsp;事迹 </td>
				    <td>&nbsp;
				    	&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="zysj"/>
				    </td>
				  </tr>
				  <tr style="height:160px">
				    <td> &nbsp;&nbsp;班主任意见 </td>
				    <td >&nbsp;
				    	&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="fdyyj"/>
				    </td>
				  </tr>
				  <tr style="height:160px">
				    <td><p align="left">&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" /> </p>
				    &nbsp;&nbsp;意见 </td>
				    <td>&nbsp;
				    	&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xyyj"/>
				    </td>
				  </tr>
				  <tr style="height:160px">
				    <td><p align="left">&nbsp;&nbsp;学校</p>
				    &nbsp;&nbsp;意见 </td>
				    <td>&nbsp;
				    	&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xxyj"/>
				    </td>
				  </tr>
			</table>
						<%--<div class="buttontool" id="btn" align="center"
							style="" width="100%">
							<button class="button2" id="btn_print"
								onclick="expTab('rsTable','文明班级报批表','')"
								style="width:80px">
								打印/预览
							</button>
						</div>
			
		--%></html:form>
	</body>
</html>

