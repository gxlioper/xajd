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
    <p align="center"><strong ><bean:write name="nd"/>&nbsp;&nbsp;<font size="4">���</font>&nbsp;&nbsp;<bean:write name="xymc"/>
    &nbsp;&nbsp;<font size="4"><bean:message key="lable.xsgzyxpzxy" />������������ѧ��ͳ�Ʊ���</font> </strong></p>
<table width="60%" align="center">
	<tr><td>
	<p>У��������ָ�����ģ�</p>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ֽ������Ժ�μӺ���������ѧ��ͳ�����£� <br/>
	</td></tr>
</table>
<table width="60%" class="tbstyle" align="center">
  <tr>
    <td width="55" align="center"><p>��� </p></td>
    <td width="134" align="center"><p>רҵ </p></td>
    <td width="140" align="center"><p>�༶ </p></td>
    <td width="60" align="center"><p>ѧ���� </p></td>
    <td width="80" align="center"><p>��ֹʱ�� </p></td>
    <td width="80" align="center"><p>������ </p></td>
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
    <td width="55"><p align="center">�ϼ� </p></td>
    <td width="134">&nbsp;</td>
    <td width="140">&nbsp;</td>
    <td width="60">&nbsp;</td>
    <td width="80">&nbsp;</td>
    <td width="80">&nbsp;</td>
  </tr>
  <tr>
    <td width="55">&nbsp;</td>
    <td width="134"><p align="center">Э��Ա���� </p></td>
    <td width="360" colspan="4">&nbsp;</td>
  </tr>
</table>
<p></p><p></p>
<table align="center" width="60%">
<tr>
<td align="right"><bean:message key="lable.xsgzyxpzxy" /></td>
</tr>
<tr>
<td align="right">	�� �� �� </td>
</tr>
</table>
  </body>
</html:html>
