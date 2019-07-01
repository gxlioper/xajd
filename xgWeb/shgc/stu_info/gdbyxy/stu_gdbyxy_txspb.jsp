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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style media='print'>.noPrin{display:none}
	</style>
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<object id="WebBrowser" name="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
  	<script language="javascript" src="js/function.js"></script>
<body>
		<html:form action="/leaveSchool.do">
		<center>
  <p><strong>学 生 退 学 审 批 表</strong></p>
</center>
<table width="80%" id="rsT" class="tbstyle" align="center">
  <tr>
    <td width="45" height="24"> <strong>姓 名 </strong> </td>
    <td width="59">${rs.xm}</td>
    <td width="34"> <strong>性别 </strong> </td>
    <td width="64">${rs.xb}</td>
    <td width="56"> <strong>年 龄 </strong> </td>
    <td width="85">${rs.nl}</td>
    <td width="56"> <strong>班 级 </strong> </td>
    <td width="225">${rs.bjmc}</td>
  </tr>
  <tr>
    <td height="26" colspan="5"> <strong>籍 贯 </strong> </td>
    <td>${rs.jg}</td>
    <td> <strong>通讯地址 </strong> </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="27" colspan="3"> <strong>家长签名 </strong> </td>
    <td>&nbsp;</td>
    <td> <strong>联系电话 </strong> </td>
    <td>${rs.lxdh}</td>
    <td> <strong>宿舍号 </strong> </td>
    <td>${rs.ssbh}</td>
  </tr>
  <tr>
    <td height="29" colspan="1"><p align="justify"><strong>退学<br/>
        原因 </strong></p></td>
    <td colspan="7"><p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p> <strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		（辅导员、班主任填写）签名： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日 </strong> </p></td>
  </tr>
  <tr>
    <td colspan="1"> <strong>系学生管理副主任意见 </strong> </td>
    <td colspan="7"><p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <strong> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日 </strong> </td>
  </tr>
  <tr>
    <td colspan="1"> <strong>系主任意见 </strong> </td>
    <td colspan="7"><p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p> <strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 年 &nbsp;&nbsp;&nbsp;月 &nbsp;&nbsp;&nbsp;日 </strong> </p></td>
  </tr>
  <tr>
    <td colspan="1"> <strong>学生处备案审签 </strong> </td>
    <td colspan="7"><p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p> <strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签名： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年 &nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp; 日 </strong> </p></td>
  </tr>
  <tr>
    <td colspan="1"> <strong>备注 </strong> </td>
    <td colspan="7"><p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p></td>
  </tr>
</table>
</html:form>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='页面设置' onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览' onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印' onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>	
	</body>
</html:html>
