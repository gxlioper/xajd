<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<html:html>
<base target="_self">
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
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<body>
	<html:form action="lyjszxjsqb.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />

		<table width="100%" id="theTable">
		<tr>
		<td>
		<p align="center" style="font-size:20px"><strong>���������ѧ&nbsp;</strong><strong><bean:write name="rs" property="nj" /></strong><strong>&nbsp;����ɫͨ������������ </strong>
</p>
<p align="center">
<strong>
������&nbsp;
</strong>
<strong>
<bean:write name="rs" property="xm" />&nbsp;&nbsp;&nbsp;&nbsp;
</strong>
<strong>
�Ա�&nbsp;
</strong>
<strong>
<bean:write name="rs" property="xb" />&nbsp;&nbsp;&nbsp;&nbsp;
</strong>
<strong>
<bean:message key="lable.xsgzyxpzxy" />��&nbsp;
</strong>
<strong>
<bean:write name="rs" property="xymc" />&nbsp;&nbsp;&nbsp;&nbsp;
</strong>
<strong>
רҵ��&nbsp;
</strong>
<strong>
<bean:write name="rs" property="xmc" />&nbsp;&nbsp;&nbsp;&nbsp;
</strong>
<strong>
���壺&nbsp;
</strong>
<strong>
<bean:write name="rs" property="mzmc" />&nbsp;&nbsp;&nbsp;&nbsp;
</strong>
<br>
</p>
<p align="left"><strong>�밴������Ŀ���� </strong><strong></strong></p>
<table width="100%" class="tbstyle">
  <tr>
    <td scope="col" width="30%" height="30"><p align="center"><strong>��λ</strong></p></td>
    <td scope="col" width="40%"><p align="center"> <strong>�� Ŀ</strong></p></td>
    <td scope="col" width="30%"><p align="center"> <strong>ǩ ��</strong></p></td>
  </tr>
  <tr>
    <td scope="row" width="30%" height="30"><div align="center"> <strong>ѧ��������������</strong></div></td>
    <td> �͹ذ�����������ѯ��Э�����������ѧ����������Ᵽ�ա�ҽ��סԺ����</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row" height="30"><div align="center"> <strong>������</strong></div></td>
    <td> ������������ </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row" height="30"><div align="center"> <strong>�����������</strong></div></td>
    <td> ����һ��ͨ���տ���ֵ������ </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row" height="30"><div align="center"> <strong>������</strong></div></td>
    <td> �����˵���������������Դ�������ѧ���� </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row" height="30"><div align="center"> <strong>������ҵ��</strong></div></td>
    <td> ����ѧ��Ϣ�ɼ��� </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row" height="30"><div align="center"> <strong>������</strong></div></td>
    <td> ��������������ȡ��ѵ��װ�� </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row" height="30"><div align="center"> <strong>УҽԺ</strong></div></td>
    <td> ��죨������У�������� </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td scope="row" height="30"><div align="center"> <strong>�Ʒ���</strong></div></td>
    <td> ����Ԥ�����֣�������У�������� </td>
    <td>&nbsp;</td>
  </tr>
</table>
<p align="left"><strong>ע��������Ŀ��Ϻ��뽫�˵�����ѧ������������������ȡѧ���ֲᡢУ�ռ���ѧ���֪ͨ���� </strong></p>
		</td>
		</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable',' ')" />
	</div>
</body>
</html:html>
