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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="../../skin1/style/main.css"
		type="text/css" media="all" />
	<base target="_self">
<%--	<script language="javascript" src="js/function.js"></script>--%>
<%--<script language="javascript" src="js/commanFunction.js"></script>--%>
	<body>
	<html:form action="/xsqgzx.do" method="post">		
		<p align="center"><strong>${xxmc}�ڹ���ѧ��λ��������� </strong><strong></strong></p>
<p align="center"><strong>&nbsp; </strong></p>
<table cellspacing="0" cellpadding="0" align="center" class="tbstyle">
  <tr>
    <td width="55"><p align="center">�ù����� </p></td>
    <td width="169"><p align="center">&nbsp; </p></td>
    <td width="95"><p align="center">��λָ����ʦ </p></td>
    <td width="84"><p>&nbsp; </p></td>
    <td width="60"><p align="center">��ϵ�绰 </p></td>
    <td width="120"><p>&nbsp; </p></td>
  </tr>
  <tr>
    <td width="55" rowspan="7"><p align="center">��λ���ð������ </p></td>
    <td width="264" colspan="2"><p align="center">��λ���� </p></td>
    <td width="264" colspan="3"><p align="center">��λ���� </p></td>
  </tr>
  <tr>
    <td width="264" colspan="2"><p align="center">&nbsp; </p></td>
    <td width="264" colspan="3"><p align="center">&nbsp; </p></td>
  </tr>
  <tr>
    <td width="264" colspan="2"><p align="center">&nbsp; </p></td>
    <td width="264" colspan="3"><p align="center">&nbsp; </p></td>
  </tr>
  <tr>
    <td width="264" colspan="2"><p align="center">&nbsp; </p></td>
    <td width="264" colspan="3"><p align="center">&nbsp; </p></td>
  </tr>
  <tr>
    <td width="264" colspan="2"><p align="center">&nbsp; </p></td>
    <td width="264" colspan="3"><p align="center">&nbsp; </p></td>
  </tr>
  <tr>
    <td width="264" colspan="2"><p align="center">&nbsp; </p></td>
    <td width="264" colspan="3"><p align="center">&nbsp; </p></td>
  </tr>
  <tr>
    <td width="264" colspan="2"><p align="center">&nbsp; </p></td>
    <td width="264" colspan="3"><p align="center">&nbsp; </p></td>
  </tr>
  <tr>
    <td width="55"><p align="center">����Ҫ�� </p>
        <p align="center">��Ƹ���� </p></td>
    <td width="528" colspan="5" valign="top"><p>1 �� </p>
        <p>&nbsp; </p>
        <p>2 �� </p>
        <p>&nbsp; </p>
        <p>3 �� </p>
        <p>&nbsp; </p>
        <p>4 �� </p>
        <p>&nbsp; </p>
        <p>5 �� </p>
        <p>&nbsp; </p></td>
  </tr>
  <tr>
    <td width="583" colspan="6" valign="top"><p>���˲��Ÿ���������� </p>        
        <p >&nbsp; </p>
        <p>&nbsp; </p>
        <p align="right">ǩ�������£� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
        <p align="right">�� �� �� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td>
  </tr>
  <tr>
    <td width="583" colspan="6" valign="top"><p>ѧ������У������ѧ����������ĸ���������� </p>        
        <p>&nbsp; </p>
        <p>&nbsp; </p>
        <p align="right">ǩ�������£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>
        <p align="right">�� �� �� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p></td>
  </tr>
</table>
<p>&nbsp; </p>
		</html:form>
	</body>
</html>
