<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	

	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  
  <body>
    <html:form action="">
     <div align="center" style="font-size:18px; font-weight: bold;">����ְҵ����<bean:message key="lable.xsgzyxpzxy" /><br>ѧ���ر��������</div>
<div>__________ϵ��Ժ��</div>
<table width="100%"  border="1">
  <tr>
    <th colspan="2" scope="col">�༶</th>
    <th colspan="2" scope="col">&nbsp;</th>
    <th width="11%" scope="col">����</th>
    <th width="12%" scope="col">&nbsp;</th>
    <th width="8%" scope="col">�Ա�</th>
    <th width="8%" scope="col">&nbsp;</th>
    <th width="8%" scope="col">ְ��</th>
    <th width="17%" scope="col">&nbsp;</th>
  </tr>
  <tr>
    <th colspan="3" scope="row">�����Ƽ�����</th>
    <td colspan="2">&nbsp;</td>
    <td colspan="2">������</td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <th width="7%" scope="row"><p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p></th>
    <th colspan="9" scope="row">�� �� �� </th>
  </tr>
  <tr>
    <th scope="row"><p>ϵ</p>
    <p>��Ժ��</p>
    <p>��</p>
    <p>��</p></th>
    <th colspan="9" scope="row"><p>ǩ������</p>
    <p>�� �� �� </p></th>
  </tr>
  <tr>
    <th scope="row"><p>ѧ</p>
    <p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p></th>
    <th colspan="9" scope="row"><p>ǩ������</p>
    <p>�� �� �� </p></th>
  </tr>
</table>
ע��1.���񽱲��� 2.��ϵ��Ժ���£��쵼ǩ�ַ�����Ч
    
    </html:form>
  </body>
</html:html>
