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
    
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<style media='print'>.noPrin{display:none}
	</style>
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="/xgxt/skin1/style/main.css"
		type="text/css" media="all" />
	<object id="WebBrowser" name="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
  	<script language="javascript" src="js/function.js"></script>
<body>
		<html:form action="/leaveSchool.do">
		<center>
 <h1 align="center"><strong>ѧ �� �� �� �� �� ��</strong></h1>
</center>
<table class="tbstyle" width="100%">
  <tr>
    <td width="45" height="145" rowspan="5">&nbsp;</td>
    <td width="" height="40">����</td>
    <td width="">&nbsp;</td>
    <td width="">�Ա�</td>
    <td width="">&nbsp;</td>
    <td width="">��������</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="22" height="45">��ͥ��ַ</td>
    <td  colspan="5" height="45">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" height="45">ԭ��ѧУ</td>
    <td >&nbsp;</td>
    <td height="45">ԭ��רҵ</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td height="25">���й�����<br/>ʵϰ��ȡ��<br/>���ּ���֤��</td>
    <td  colspan="5">&nbsp;</td>
  </tr>
  <tr>
    <td height="15" colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���˾������濼��,���üҳ�ͬ��,������ԭ����ѧУתѧ�����ݰ��ƹ��̸߼�����
      <p>ѧУ _______________________________רҵ(�߼����м�)ѧϰ����ͬ���Ƴ�һ���ȡ��ҵ֤�顣</p>
    <p> �ֻ�/��ͥ�绰��</p>
    <p>�ҳ�ǩ����__________ ѧ��ǩ����___________</p>
    <p> �� �� �� </p></td>
  </tr>
  <tr>
    <td ><p>ϵѧ</p>
    <p>����</p>
    <p>��</p>
    <p>����</p>
    <p>���</p></td>
    <td width="409" colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td><p>ϵ��</p>
    <p>����</p>
    <p>��</p></td>
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td><p>ѧ��</p>
    <p>����</p>
    <p>����</p>
    <p>ǩ</p></td>
    <td colspan="6">&nbsp;</td>
  </tr>
  <tr>
    <td>��ע</td>
    <td  colspan="6">&nbsp;</td>
  </tr>
</table>
</html:form>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='ҳ������' onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��' onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>	
	</body>
</html:html>
