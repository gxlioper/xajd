<%@ page language="java" contentType="text/html; charset=GBk"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <base target="_self" />
    
    <title>hzjy_bb_xqtjbg.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script type="text/javascript" src="js/function.js"></script>
  </head>
  
  <body>
    <p align="center"><strong ><bean:write name="nd"/>&nbsp;&nbsp;<font size="4">年度</font>&nbsp;&nbsp;<bean:write name="xymc"/>
    &nbsp;&nbsp;<font size="4"><bean:message key="lable.xsgzyxpzxy" />合作教育工作学期统计报告</font> </strong></p>
<table width="60%" align="center">
	<tr><td>
	<p>校合作教育指导中心：</p>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现将年度我院参加合作教育的学生统计如下： <br/>
	</td></tr>
</table>
<table width="60%" class="tbstyle" align="center">
  <tr>
    <td width="55" align="center"><p>序号 </p></td>
    <td width="134" align="center"><p>专业 </p></td>
    <td width="140" align="center"><p>班级 </p></td>
    <td width="60" align="center"><p>学生数 </p></td>
    <td width="80" align="center"><p>起止时间 </p></td>
    <td width="80" align="center"><p>总周数 </p></td>
  </tr>
  <logic:iterate id="v" name="rs">
  <tr>
    <td width="55"><p align="center"><bean:write name="v" property="xh"/> </p></td>
    <td width="134"><bean:write name="v" property="zymc"/></td>
    <td width="140"><bean:write name="v" property="bjmc"/></td>
    <td width="60" align="center"><bean:write name="v" property="xss"/></td>
    <td width="80"><bean:write name="v" property="qzsj"/></td>
    <td width="80"><bean:write name="v" property="zzs"/></td>
  </tr>
  </logic:iterate>
  <tr>
    <td width="55"><p align="center">合计 </p></td>
    <td width="134">&nbsp;</td>
    <td width="140">&nbsp;</td>
    <td width="60">&nbsp;</td>
    <td width="80">&nbsp;</td>
    <td width="80">&nbsp;</td>
  </tr>
  <tr>
    <td width="55">&nbsp;</td>
    <td width="134"><p align="center">协调员总数 </p></td>
    <td width="360" colspan="4">&nbsp;</td>
  </tr>
</table>
<p></p><p></p>
<table align="center" width="60%">
<tr>
<td align="right"><bean:message key="lable.xsgzyxpzxy" /></td>
</tr>
<tr>
<td align="right">	年 月 日 </td>
</tr>
</table>
  </body>
</html:html>
