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

</script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
</script>
    <body>
    <html:form action="/stgl" method="post">
<p class="MsoNormal" align="center"><strong><span style="font-family:宋体;font-size:22.0pt;">社团活动申请表 </span></strong><strong><span style="font-size:22.0pt;"></span></strong></p><br/>
<p class="MsoNormal" align="left"><span style="font-size:12.0pt;">填表日期：&nbsp;<bean:write name="rs" property="lrsj"/></span></p>
<table border="1" cellpadding="0" cellspacing="0" class="MsoNormalTable">
  <tr>
    <td height="30" colspan="2"><p class="MsoNormal"><span style="font-family:宋体;font-size:12.0pt;">组织名称 </span><span style="font-size:12.0pt;"></span></p></td>
    <td colspan="6"><p class="MsoNormal">&nbsp;<bean:write name="rs" property="stmc"/></p></td>
  </tr>
  <tr>
    <td height="29" colspan="2"><p class="MsoNormal"><span style="font-family:宋体;font-size:12.0pt;">负责人姓名</span></p></td>
    <td width="109"><p class="MsoNormal">&nbsp; <bean:write name="rs" property="xm"/></p></td>
    <td width="71"><span style="font-family:宋体;font-size:12.0pt;">&nbsp;&nbsp;院 系 </span></td>
    <td width="104">&nbsp;<bean:write name="rs" property="xymc"/></td>
    <td width="89" ><span style="font-family:宋体;font-size:12.0pt;">&nbsp;&nbsp;联系方式 </span></td>
	 <td width="115" colspan="2">&nbsp;<bean:write name="rs" property="lxfs"/></td>
  </tr>
  <tr>
    <td height="29" colspan="2"><p class="MsoNormal"><span style="font-family:宋体;font-size:12.0pt;">活动项目名称 </span><span style="font-size:12.0pt;"></span></p></td>
    <td colspan="6"><p class="MsoNormal">&nbsp; <bean:write name="rs" property="hdmc"/></p></td>
  </tr>
  <tr>
    <td height="30" colspan="2"><p class="MsoNormal"><span style="font-family:宋体;font-size:12.0pt;">活动时间 </span><span style="font-size:12.0pt;"></span></p></td>
    <td colspan="2"><p class="MsoNormal">&nbsp; <bean:write name="rs" property="hdsj"/></p></td>
    <td height="30" colspan="1"><p class="MsoNormal"><span style="font-family:宋体;font-size:12.0pt;">活动地点 </span><span style="font-size:12.0pt;"></span></p></td>
    <td colspan="3">&nbsp;<bean:write name="rs" property="hddd"/></td>
  </tr>
  <tr>
    <td height="36" colspan="2"><p class="MsoNormal"><span style="font-family:宋体;font-size:12.0pt;">参加人员　 </span><span style="font-size:12.0pt;">( </span><span style="font-family:宋体;font-size:12.0pt;">人数 </span><span style="font-size:12.0pt;">) </span></p></td>
    <td colspan="6"><p class="MsoNormal">&nbsp; <bean:write name="rs" property="cjrs"/></p></td>
  </tr>
  <tr>
    <td colspan="2" height="211"><p class="MsoNormal"><span style="font-family:宋体;font-size:12.0pt;">活动内容 </span><span style="font-size:12.0pt;"></span></p></td>
    <td colspan="6"><p class="MsoNormal">&nbsp;<bean:write name="rs" property="hdnr"/></p></td>
  </tr>
  <tr>
    <td colspan="2" height="119"><p class="MsoNormal"><span style="font-family:宋体;font-size:12.0pt;">活动经费预算 </span><span style="font-size:12.0pt;"></span></p></td>
    <td colspan="6"><p class="MsoNormal">&nbsp; <bean:write name="rs" property="hdjfys"/></p></td>
  </tr>
  <tr>
    <td colspan="2" height="154"><p class="MsoNormal"><span style="font-family:宋体;font-size:12.0pt;">指导老师意见 </span><span style="font-size:12.0pt;"></span></p></td>
    <td colspan="2" ><p class="MsoNormal" align="center">&nbsp;&nbsp;<bean:write name="rs" property="ddlsyj"/></p>
        <p class="MsoNormal" align="right"><span style="font-family:宋体;font-size:12.0pt;">签名：<bean:write name="rs" property="lsxm"/> </span></p></td>
    <td  ><p class="MsoNormal"><span style="font-family:宋体;font-size:12.0pt;">系团总支意见 </span><span style="font-size:12.0pt;"></span></p></td>
    <td colspan="3" ><p class="MsoNormal" align="center">&nbsp;&nbsp;<bean:write name="rs" property="xtzjyj"/></p>
        <p class="MsoNormal" align="right"><span style="font-family:宋体;font-size:12.0pt;">（盖章） </span><span style="font-size:12.0pt;"></span></p></td>
  </tr>
  <tr>
    <td colspan="2" height="154"><p class="MsoNormal"><span style="font-family:宋体;font-size:12.0pt;">院团委意见 </span><span style="font-size:12.0pt;"></span></p></td>
    <td colspan="6" rowspan="5"><p class="MsoNormal" align="center">&nbsp;&nbsp;<bean:write name="rs" property="shzt"/></p>
       
        <p class="MsoNormal" align="right"><span style="font-family:宋体;font-size:12.0pt;">（盖章） </span><span style="font-size:12.0pt;"></span></p></td>
  </tr>
</table>
<p class="MsoNormal" align="center">&nbsp; </p>
</html:form>
</body>
</html>
