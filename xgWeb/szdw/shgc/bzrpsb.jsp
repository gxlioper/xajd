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
		<meta name="Copyright" content="������� zfsoft" />
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
	<body>
	<html:form action="/fdypyforShgc" method="post">
<h3 align="center"><span style="font-family:Times New Roman;font-size:16.0pt;"><span style="font-family:��������;">�Ϻ����̼�����ѧ��������Σ���ʦ������� </span></span></h3>
<table border="1" cellpadding="0" cellspacing="0" class="MsoNormalTable">
  <tr>
    <td width="624" height="30" colspan="2"><p class="MsoNormal"><strong><span style="font-family:����_GB2312;font-size:12.0pt;">������ <bean:write name = "rs" property="xm"/>  �Ա� <bean:write name = "rs" property="xb"/>  �������£� <bean:write name = "rs" property="csrq"/>  ������ò�� <bean:write name = "rs" property="zzmm"/></span></strong><span style="font-size:12.0pt;"></span></p></td>
  </tr>
  <tr>
    <td width="624" height="30" colspan="2"><p class="MsoNormal"><strong><span style="font-family:����_GB2312;font-size:12.0pt;">�����༶��<bean:write name = "rs" property="sdbj"/>    ѧ�������� <bean:write name = "rs" property="xsrs"/>    ��ְ���ޣ�<bean:write name = "rs" property="rzsj"/>    ְ �ƣ� <bean:write name = "rs" property="zwmc"/></span></strong></p></td>
  </tr>
  <tr>
    <td width="624" height="500" colspan="2" valign="top"><p class="MsoNormal"><strong><span style="font-family:����_GB2312;font-size:12.0pt;">��Ҫ�¼��������༶�������:  </span> </strong><bean:write name = "rs" property="zysj"/></p></td>
  </tr>
  <tr>
    <td width="624" height="130" colspan="2" valign="top"><p class="MsoNormal"><strong><span style="font-family:����_GB2312;font-size:12.0pt;"><bean:message key="lable.xsgzyxpzxy" />����� </span></strong><bean:write name = "rs" property="xyyj"/> </p></td>
  </tr>
  <tr>
    <td width="311" height="130" valign="top"><p class="MsoNormal"><strong><span style="font-family:����_GB2312;font-size:12.0pt;">ѧ��������� </span></strong><bean:write name = "rs" property="xxyj"/></p></td>
    <td width="313" valign="top"><p class="MsoNormal"><strong><span style="font-family:����_GB2312;font-size:12.0pt;">ѧУ���� </span></strong><bean:write name = "rs" property="xxsh"/></p></td>
  </tr>
</table>
<p class="MsoNormal"><span style="font-family:����_GB2312;">ѧ���������������Ʊ� </span></p>
<p class="MsoNormal" align="right"><span style="font-family:����ϸ��;font-size:12.0pt;">������ڣ�  <bean:write name = "rs" property="lrrq"/></span></p>
</html:form>
</body>
</html>