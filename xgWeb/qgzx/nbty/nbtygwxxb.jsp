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
<jsp:directive.page import="xgxt.action.Base"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<style media='print'>
		.noPrin{
		display:none;}
	</style>
	<base target="_self" />
	<script language="javascript" src="js/function.js"></script>	
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>	
	<body>
		<p align="center"><h3 align="center">������һְҵ����<bean:message key="lable.xsgzyxpzxy" /> </h3></p>
		<p align="center"><h3 align="center"><%= Base.currXn%>ѧ��ѧ���ڹ���ѧ��λ��˱� </h3></p>
		<div align="center">
		  <table class="tbstyle">
		    <tr>
		      <td width="115"><p align="center">�ù����� </p></td>
		      <td width="169" colspan="2" valign="top"><p><strong>&nbsp; </strong></p></td>
		      <td width="122" colspan="2"><p align="center">�ù���λ <strong></strong></p></td>
		      <td width="177" valign="top"><p><strong>&nbsp; </strong></p></td>
		    </tr>
		    <tr>
		      <td width="115"><p align="center">��λ���� </p></td>
		      <td width="169" colspan="2"><p align="center">A. �̶� B. ��ʱ <strong></strong></p></td>
		      <td width="122" colspan="2"><p align="center">����ʱ�� </p></td>
		      <td nowrap="nowrap"><p align="center"> 20&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;�� �� 20&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;�� </p></td>
		    </tr>
		    <tr>
		      <td width="115"><p align="center">��λ���� </p></td>
		      <td width="169" colspan="2"><p align="center">&nbsp; </p></td>
		      <td width="122" colspan="2"><p align="center">�������� </p></td>
		      <td width="177"><p align="center">&nbsp; </p></td>
		    </tr>
		    <tr>
		      <td width="115"><p align="center">�ܹ���ʱ�� </p></td>
		      <td width="169" colspan="2"><p align="center">( Сʱ ) <strong></strong></p></td>
		      <td width="122" colspan="2"><p align="center">�����ص� </p></td>
		      <td width="177" valign="top"><p><strong>&nbsp; </strong></p></td>
		    </tr>
		    <tr>
		      <td width="115"><p align="center">�¹���ʱ�� </p></td>
		      <td width="169" colspan="2"><p align="center">( Сʱ ) <strong></strong></p></td>
		      <td width="122" colspan="2"><p>ѧ���ܹ���ʱ�� </p></td>
		      <td width="177"><p align="center">( Сʱ ) <strong></strong></p></td>
		    </tr>
		    <tr>
		      <td width="115"><p align="center">��λ������ </p></td>
		      <td width="169" colspan="2"><p align="center">&nbsp; </p></td>
		      <td width="122" colspan="2"><p align="center">��ϵ�绰 </p></td>
		      <td width="177" valign="top"><p><strong>&nbsp; </strong></p></td>
		    </tr>
		    <tr>
		      <td width="115" rowspan="2"><p align="center">�� λ Ҫ �� </p></td>
		      <td width="36"><p>רҵ </p></td>
		      <td width="133"><p>&nbsp; </p></td>
		      <td width="36" align="center">�س�</td>
		      <td width="223" colspan="2" valign="top"><p><strong>&nbsp; </strong></p></td>
		    </tr>
		    <tr>
		      <td width="36"><p>ʱ�� <strong></strong></p></td>
		      <td width="133"><p><strong>&nbsp; </strong></p></td>
		      <td width="36" align="center">����</td>
		      <td width="223" colspan="2" valign="top"><p><strong>&nbsp; </strong></p></td>
		    </tr>
		    <tr>
		      <td width="583" colspan="6"><p>&nbsp; </p>
		          <p>&nbsp; </p>
		          <p>&nbsp; </p>
		           <span style="float:right">�ù����ҡ�Ժϵ��������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</span></td>
		    </tr>
		    <tr>
		      <td width="115"><p align="center">ѧ����������<br>�������</p></td>
		      <td width="468" colspan="5" valign="top"><p>&nbsp; </p>
		          <p>&nbsp; </p>
		          <p>&nbsp; </p>
		          <p>&nbsp; </p>
		          <p>&nbsp; </p>
		          <span style="float:right">ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;�� </span></td>
		    </tr>
		    <tr>
		      <td width="115"><p align="center">ѧ����������<br>�쵼С������</p></td>
		      <td width="468" colspan="5" valign="top"><p>&nbsp; </p>
		          <p>&nbsp; </p>
		          <p>&nbsp; </p>
		          <p>&nbsp; </p>
		          <p>&nbsp; </p>
		         <span style="float:right">ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;�� </span></td>
		    </tr>
		    <tr>
		      <td width="115"><p align="center">��ע </p></td>
		      <td width="468" colspan="5" valign="top"><p>&nbsp; </p>
		          <p>&nbsp; </p>
		          <p>&nbsp; </p>
		          <p>&nbsp; </p>
		         </td>
		    </tr>
		  </table>
		</div>
		<div align=center class='noPrin'>
				<input type='button' class='button2' value='ҳ������'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
					onclick="WebBrowser.ExecWB(6,6);return false;">
		</div>
	</body>
</html>
