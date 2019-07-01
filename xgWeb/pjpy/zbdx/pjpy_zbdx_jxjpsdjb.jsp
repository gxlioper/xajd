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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	</head>
<style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
  <body> 
    <html:form action="specialprise.do" method="post" >
<center>
<h3><bean:write name="map" property="xxmc"/> <bean:write name="map" property="nd"/>年度  <bean:write name="map" property="jxjmc"/> 奖学金评审登记表</h3>
<table width="90%" class="tbstyle">
  <tr>
    <td width="110" align="center" height="35">学号</td>
    <td align="center"><bean:write name="map" property="xh"/></td>
    <td width="50" align="center">姓名</td>
    <td width="110" align="center" ><bean:write name="map" property="xm"/></td>
    <td width="50" align="center">性别</td>
    <td width="70" align="center"><bean:write name="map" property="xb"/></td>
    <td width="70" align="center">民族</td>
    <td align="center"><bean:write name="map" property="mzmc"/></td>
    <td width="70" align="center">党团员</td>
    <td width="70" align="center"><bean:write name="map" property="zzmmmc"/></td>
  </tr>
  <tr>
    <td align="center" height="35"><bean:message key="lable.xsgzyxpzxy" />、系<br>
    专业、班级</td>
    <td colspan="5"><bean:write name="map" property="xymc"/><bean:write name="map" property="zymc"/><br><bean:write name="map" property="bjmc"/></td>
    <td align="center">外语水平</td>
    <td colspan="3"><bean:write name="map" property="wysp"/></td>
  </tr>
  <tr>
    <td align="center" height="35">宿舍电话</td>
    <td colspan="5"></td>
    <td align="center">手机</td>
    <td colspan="3"><bean:write name="map" property="sjhm"/></td>
  </tr>
  <tr>
    <td height="35" align="right">社会工作情况<br>(现任职务):</td>
    <td colspan="2"><bean:write name="map" property="drzw"/></td>
    <td colspan="2">专业年级总人数：<br><bean:write name='map' property="zyrsNum" /> 人</td>
    <td colspan="2" height="35" align="right">当年所获奖学金:</td>
    <td colspan="3"><bean:write name="map" property="dnshjxj"/></td>
  </tr>
  <tr>
    <td height="200">个人学习简历</td>
    <td colspan="9">&nbsp;<bean:write name="map" property="xxjl"/></td>
  </tr>
  <tr>
    <td height="200">参加科研情况</td>
    <td colspan="9">&nbsp;<bean:write name="map" property="kycg"/></td>
  </tr>
  <tr>
    <td height="200">申请理由</td>
    <td colspan="9">&nbsp;<bean:write name="map" property="sqly"/></td>
  </tr>
</table>
<div class="brk"></div>
<table width="90%" class="tbstyle">
       <tr>
         <td width="3%" height="156"><bean:message key="lable.xsgzyxpzxy" />通过理由</td>
         <td width="731">&nbsp;<bean:write name="map" property="xyshyj"/></td>
       </tr >
       <tr>
         <td height="157">班主任、辅导员或导师推荐意见</td>
         <td height="157" align="right" valign="bottom"><bean:write name="map" property="fdyyj"/><p>签名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <p>年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</p></td>
       </tr>
       <tr>
         <td height="315"><bean:message key="lable.xsgzyxpzxy" />奖学金评审委员会意见</td>
         <td height="315" align="right" valign="bottom"><bean:write name="map" property="xypswyhyj"/><p>评审委员会主任签名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <p>年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</p></td>
       </tr >
       <tr>
         <td height="315">学校意见</td>
         <td height="315" align="right" valign="bottom"><bean:write name="map" property="xxshyj"/><p>学校盖章&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <p>年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</p></td>
       </tr >
</table>
</center>
</html:form>
</body>
</html:html>
