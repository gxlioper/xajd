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
    <base target="_self" />
    
    <title>hzjy_bb_xsazb.jsp</title>

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
    <p><strong><font size="4" face="华文新魏">填写说明： </font></strong></p>
<p><strong><font size="4" face="华文新魏">1、按班级,根据学生的学号顺序填写《产学合作教育安置表》； </font></strong></p>
<p><strong><font size="4" face="华文新魏">2、按照表格要求填写所有的内容，内容必须详尽真实; </font></strong></p>
<p><strong><font size="4" face="华文新魏">3、表格中的“区域”是指上海市的行政区域，例如“长宁区”应填写“长宁”，“虹口区”则填写“虹口”。 </font></strong></p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p align="center"><strong><font size="5">上海工程技术大学</font> <bean:write name="xymc"/> <font size="4"><bean:message key="lable.xsgzyxpzxy" />产学合作教育安置表</font> </strong></p>
<p align="center"><strong>（ 年 月 日--- 月 日） </strong><strong></strong></p>
<p><strong>班级： <bean:write name="rs" property="bjmc"/></strong>&nbsp;&nbsp;&nbsp;<strong>班级人数：<bean:write name="rs" property="bjrs"/> </strong><strong>人 </strong><strong>班长： </strong><strong>班主任： </strong><strong></strong></p>
<table width="100%" class="tbstyle" align="center">
  <tr>
    <td width="33"><p align="center">编号 </p></td>
    <td width="53"><p align="center">姓 名 </p></td>
    <td width="66"><p align="center">学 号 </p></td>
    <td width="55"><p align="center">家庭电话 </p></td>
    <td width="69"><p align="center">手机号码 </p></td>
    <td width="147"><p align="center">雇主单位全称 </p></td>
    <td width="169"><p align="center">雇主单位地址 </p></td>
    <td width="35"><p align="center">区域 </p></td>
    <td width="45"><p align="center">邮 编 </p></td>
    <td width="48"><p align="center">联系人 </p></td>
    <td width="45"><p align="center">部 门 </p></td>
    <td width="57"><p align="center">联系电话 </p></td>
    <td width="69"><p align="center">手机号码 </p></td>
    <td width="45"><p align="center">协调员 </p></td>
    <td width="71"><p align="center">手机号码 </p></td>
  </tr>
  <logic:iterate id="v" name="rs1">
  <tr>
    <td width="33"><bean:write name="v" property="bh"/></td>
    <td width="53"><bean:write name="v" property="xm"/></td>
    <td width="71"><bean:write name="v" property="xh"/></td>
    <td width="66"><bean:write name="v" property="jtdh"/></td>
    <td width="55"><bean:write name="v" property="sjh"/></td>
    <td width="69"><bean:write name="v" property="gzdwqc"/></td>
    <td width="147"><bean:write name="v" property="gzdwdz"/></td>
    <td width="169"><bean:write name="v" property="qy"/></td>
    <td width="35"><bean:write name="v" property="yzbm"/></td>
    <td width="45"><bean:write name="v" property="lxr"/></td>
    <td width="48"><bean:write name="v" property="bm"/></td>
    <td width="45"><bean:write name="v" property="lxdh"/></td>
    <td width="57"><bean:write name="v" property="lxrsjh"/></td>
    <td width="69"><bean:write name="v" property="xty"/></td>
    <td width="45"><bean:write name="v" property="xtysjh"/></td>
  </tr>
  </logic:iterate>
</table>
  </body>
</html:html>
