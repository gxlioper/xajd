<%@ page language="java" contentType="text/html; charset=GBK"%>

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
	<style type="text/css">
		.tbstyle {
					border-collapse: collapse;
				}
		.tbstyle td {
	border: 1px #97B7DB solid;
	padding: 3px;
}		
	</style>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
  <body>
   		 <center><h2>违纪学生教育跟踪表</h2></center>
	<p>&nbsp;<bean:write name="rs"  property="xymc"/>&nbsp;</p>
	<table width="100%" class="tbstyle" border="1">
  		<tr>
  			<td>姓&nbsp;名</td>
  			<td align="center" style="width:15%">&nbsp;<bean:write name="rs" property="xm"/>&nbsp;</td>
  			<td>专&nbsp;业</td>
  			<td colspan="2" align="center" style="width:21%">&nbsp;<bean:write name="rs" property="zymc"/>&nbsp;</td>
  			<td>班&nbsp;级</td>
  			<td align="center" style="width:21%">&nbsp;<bean:write name="rs" property="bjmc"/>&nbsp;</td>
  			<td>性&nbsp;别</td>
  			<td align="center" style="width:10%">&nbsp;<bean:write name="rs" property="xb"/>&nbsp;</td>
  			<td>年&nbsp;龄</td>
  			<td align="center">&nbsp;&nbsp;<bean:write name="rs" property="nn"/>&nbsp;&nbsp; </td>
  		</tr>
  		<tr>
  			<td colspan="2">处分类别</td>
  			<td colspan="3" align="center">&nbsp;<bean:write name="rs" property="cflbmc"/></td>
  			<td>处分时间</td>
  			<td colspan="2" align="center">&nbsp;<bean:write name="rs" property="cfsj"/> </td>
  			<td>考查年月</td>
  			<td colspan="2" >&nbsp; </td>
  		</tr>
  		<tr>
    		<td colspan="11" align="center">
    			教&nbsp;&nbsp;育&nbsp;&nbsp;跟&nbsp;&nbsp;踪&nbsp;&nbsp;记&nbsp;&nbsp;录
    		</td>
  		</tr>
  		<tr>
  			<td width="10" align="center">
  				<p >&nbsp;</p>
  				<p >&nbsp;</p>
  				第<br>
  				一<br>
  				季<br>
  				度<br>
  				教<br>
  				育<br>
  				情<br>
  				况<br>
  				记<br>
  				录<br>
  				<p >&nbsp;</p>
  			
  			</td>
  			<td colspan="11" valign="bottom" align="right">
  				<p >联系人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</p>
  				<p >学生签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</p>
  			</td>
  		</tr>
  		<tr>
  			<td width="10" align="center">
  				<p >&nbsp;</p>
  				<p >&nbsp;</p>
  				第<br>
  				二<br>
  				季<br>
  				度<br>
  				教<br>
  				育<br>
  				情<br>
  				况<br>
  				记<br>
  				录<br>
  				<p >&nbsp;</p>
  				
  			</td>
  			<td colspan="11" valign="bottom" align="right">
  				<p >联系人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</p>
  				<p >学生签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</p>
  			</td>
  		</tr>
  		</table>
  		<p >&nbsp;</p><p >&nbsp;</p><p >&nbsp;</p><p >&nbsp;</p>
  		<table width="100%" class="tbstyle" border="1">
  		<tr>
  			<td width="10" align="center">
  				<p >&nbsp;</p><p >&nbsp;</p>
  				第&nbsp;&nbsp;&nbsp;<br>
  				三<br>
  				季<br>
  				度<br>
  				教<br>
  				育<br>
  				情<br>
  				况<br>
  				记<br>
  				录<br>
  				<p >&nbsp;</p>
  			</td>
  			<td colspan="11" valign="bottom" align="right">
  				<p >联系人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</p>
  				<p >学生签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</p>
  			</td>
  		</tr>
  		<tr>
  			<td width="10" align="center">
  				<p >&nbsp;</p><p >&nbsp;</p>
  				第<br>
  				四<br>
  				季<br>
  				度<br>
  				教<br>
  				育<br>
  				情<br>
  				况<br>
  				记<br>
  				录<br>
  				<p >&nbsp;</p>
  			</td>
  			<td colspan="11" valign="bottom" align="right">
  				<p >联系人签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</p>
  				<p >学生签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</p>
  			</td>
  		</tr>
  		<tr>
  			<td width="10" align="center">
  				
  				学<br>
  				院<br>
  				意<br>
  				见<br>
  			
  			</td>
  			<td colspan="11" valign="bottom" align="right">
  				院领导签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
  			</td>
  		</tr>
	</table>
  </body>
</html:html>
