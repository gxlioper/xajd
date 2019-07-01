<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<style media='print'>.noPrin{display:none}
	</style>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main2.css" type="text/css" media="all" />
	<object id="WebBrowser" name="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commFunction.js"></script>
	</head>
<style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
  <body> 
    <html:form action="specialprise.do" method="post" >
<center>
<h3>上海工程技术大学申请退学表</h3>
<table width="100%" class="tbstyle" id="rsTable">
	<tr height="45">
    <td align="center" >申请人姓名</td>
    <td width="180">${rs.xm}</td>
    <td align="center">申请日期</td>
    <td>${rs.sqrq}</td>      
  </tr>
  <tr height="45">
    <td align="center">性   别</td>
    <td >${rs.xb}</td>
    <td align="center">身份证号码</td>
    <td>${rs.sfzh}</td>      
  </tr>
 <tr height="45">
 	<td align="center">学   院</td>
 	<td>${rs.xymc}</td>   
 	<td align="center">专    业</td>
 	<td>${rs.zymc}</td>   	
 </tr>
 <tr height="45">
 	<td align="center">班   级</td>
 	<td>${rs.bjmc}</td>   
 	<td align="center">学    号</td>
 	<td>${rs.xh}</td>   	
 </tr>
 <tr height="45">
 <td align="center">联系电话</td> 
 <td colspan="3"><p>手机号码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.sjhm}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 其他电话：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.qtdh}  </p></td>
 </tr>
 
 <tr>      
  <td height="80" align="center">
 申<br/>
 请<br/>
 理<br/>
 由<br/>
 </td>
 <td colspan="3">
 <p style="height:85">${rs.sqly}</p>
 <p align="right"> 学 生 本 人 签 名 _____________<br/><br/>
  年　　月　　日
 </p>
 </td>    
 </tr>
 
  <tr>      
  <td height="80" align="center">
 家<br/>
 长<br/>
 意<br/>
 见<br/>
 </td>
 <td colspan="3">
 <br/><br/><br/><br/>
 <p align="right"> 家 长 签 名 _____________<br/><br/>
 年　　月　　日
 </p>
 </td>    
 </tr>
 
  <tr>      
  <td height="80" align="center">
 学<br/>
 院<br/>
 意<br/>
 见<br/>
 </td>
 <td colspan="3">
 <p style="height:85">${rs.xyyj}</p>
 <p align="right"> 签 章 _____________<br/><br/>
 年　　月　　日
 </p>
 </td>    
 </tr>
 
  <tr>      
  <td height="80" align="center">
 经<br/>
 办<br/>
 部<br/>
 门<br/>
 意<br/>
 见<br/>
 </td>
 <td colspan="3">
 <p style="height:85">${rs.xxyj}</p>
 <p align="right"> 签 章 _____________<br/><br/>
 　&nbsp;&nbsp;年　　月　　日
 </p>
 </td>    
 </tr>
</table>
</center>
</html:form>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='页面设置' onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览' onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印' onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>	
</body>
</html:html>
