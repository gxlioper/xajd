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

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
	function jfjs(){//经费计算
	var jfyt1 = parseInt(document.getElementById("jfyt1").value);
	var jfyt2 = parseInt(document.getElementById("jfyt2").value);
	var jfyt3 = parseInt(document.getElementById("jfyt3").value);
	var jfyt4 = parseInt(document.getElementById("jfyt4").value);
	var jfyt5 = parseInt(document.getElementById("jfyt5").value);
	var jfyt6 = parseInt(document.getElementById("jfyt6").value);
	if(document.getElementById("jfyt1").value==""){
		jfyt1=0;
	}
	if(document.getElementById("jfyt2").value==""){
		jfyt2=0;
	}
	if(document.getElementById("jfyt3").value==""){
		jfyt3=0;
	}
	if(document.getElementById("jfyt4").value==""){
		jfyt4=0;
	}
	if(document.getElementById("jfyt5").value==""){
		jfyt5=0;
	}
	if(document.getElementById("jfyt6").value==""){
		jfyt6=0;
	}
	document.getElementById("zje").innerText = jfyt1+jfyt2+jfyt3+jfyt4+jfyt5+jfyt6;
   }
</script  >
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
</script>
<body onload ="jfjs()">
<html:form action="/stgl" method="post">
 <input type="hidden" id="jfyt1" name="jfyt1" value="<bean:write  name="rs" property="je1" />" />
 <input type="hidden" id="jfyt2" name="jfyt2" value="<bean:write  name="rs" property="je2" />" />
 <input type="hidden" id="jfyt3" name="jfyt3" value="<bean:write  name="rs" property="je3" />" />
 <input type="hidden" id="jfyt4" name="jfyt4" value="<bean:write  name="rs" property="je4" />" />
 <input type="hidden" id="jfyt5" name="jfyt5" value="<bean:write  name="rs" property="je5" />" />
 <input type="hidden" id="jfyt6" name="jfyt6" value="<bean:write  name="rs" property="je6" />" />
<p align="center"><strong>广东女子职业技术<bean:message key="lable.xsgzyxpzxy" />学生活动经费申请表 </strong><strong></strong></p>
<p>填表日期： <bean:write name="rs" property="lrsj"/> </p>
<table  border="1" cellpadding="0" cellspacing="0" class="MsoNormalTable" width="100%">
  <tr>
    <td  width="60px" height="35"><p>&nbsp;&nbsp;组织名称 </p></td>
    <td colspan="4"><p>&nbsp;<bean:write name="rs" property="stmc"/> </p></td>
  </tr>
  <tr>
    <td  width="60px" height="35"><p>&nbsp;&nbsp;负责人 </p></td>
    <td colspan="4"><p>&nbsp;<bean:write name="rs" property="xm"/> </p></td>
  </tr>
  <tr>
    <td  width="60px" height="35"><p>&nbsp;&nbsp;活动项目 </p></td>
    <td colspan="4"><p>&nbsp;<bean:write name="rs" property="hdmc"/> </p></td>
  </tr>
  <tr align="center">
    <td colspan="3" height="35"><p>&nbsp;&nbsp;经费用途 </p></td>
    <td width="275"><p>金额（元） </p></td>
  </tr>
  <tr>
    <td colspan="3" height="35"><p>1.<bean:write name="rs" property="jfyt1"/> </p></td>
    <td width="275"><p>&nbsp;<bean:write name="rs" property="je1"/> </p></td>
  </tr>
  <tr>
    <td colspan="3" height="35"><p>2.<bean:write name="rs" property="jfyt2"/> </p></td>
    <td width="275"><p>&nbsp;<bean:write name="rs" property="je2"/> </p></td>
  </tr>
  <tr>
    <td colspan="3" height="35"><p>3.<bean:write name="rs" property="jfyt3"/> </p></td>
    <td width="275"><p>&nbsp;<bean:write name="rs" property="je3"/> </p></td>
  </tr>
  <tr>
    <td colspan="3" height="35"><p>4.<bean:write name="rs" property="jfyt4"/> </p></td>
    <td width="275"><p>&nbsp;<bean:write name="rs" property="je4"/> </p></td>
  </tr>
  <tr>
    <td colspan="3" height="35"><p>5.<bean:write name="rs" property="jfyt5"/> </p></td>
    <td width="275"><p>&nbsp;<bean:write name="rs" property="je5"/> </p></td>
  </tr>
  <tr>
    <td colspan="3" height="35"><p>6.<bean:write name="rs" property="jfyt6"/> </p></td>
    <td width="275"><p>&nbsp;<bean:write name="rs" property="je6"/> </p></td>
  </tr>
  <tr>
    <td colspan="4" height="35">总计：<p id = "zje"> </p></td>
  </tr>
  <tr>
    <td width="52"><p>&nbsp; </p>
        <p>&nbsp;&nbsp; </p>
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;院 </p>
        <p>&nbsp;&nbsp; </p>
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;团 </p>
        <p>&nbsp;&nbsp; </p>
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;委 </p>
        <p>&nbsp;&nbsp; </p>
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;意 </p>
        <p>&nbsp;&nbsp; </p>
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;见 </p>
        <p>&nbsp; </p>
        <p>&nbsp; </p></td>
    <td colspan="11""><p><bean:write name="rs" property="ytwyj"/> </p>
        <p align="center">同意，已付：<bean:write name="rs" property="yfje"/> 元 </p>
        <p align="center">&nbsp; </p>
        <p align="center">经手人：<bean:write name="rs" property="jsr"/> . </p>
        <p align="center">&nbsp; </p>
        <p align="center">报销情况：<bean:write name="rs" property="bxqk"/> . </p>
        <p>&nbsp; </p></td>
  </tr>
</table>
</html:form>
</body>
</html>
