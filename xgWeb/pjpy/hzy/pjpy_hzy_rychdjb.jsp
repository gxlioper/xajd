<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    <html:base />
    
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
      <div align="center" style="font-family:����;font-size:20px ">У���������ǼǱ�</div>
		<br>
		_____________ѧ��                        ________________�༶
		
		__________ϵ��Ժ��
		<table width="90%"  border="1">
		  <tr>
		    <th colspan="2" scope="col">����</th>
		    <th width="15%" scope="col">&nbsp;</th>
		    <th width="7%" scope="col">�Ա�</th>
		    <th width="17%" scope="col">&nbsp;</th>
		    <th width="16%" scope="col">ְ��</th>
		    <th colspan="2" scope="col">&nbsp;</th>
		  </tr>
		  <tr>
		    <th colspan="2" scope="row">������ò</th>
		    <td>&nbsp;</td>
		    <td><div align="center">����</div></td>
		    <td>&nbsp;</td>
		    <td colspan="2"><div align="center">�ۺ���������/�ֳܷɼ�����</div></td>
		    <td width="12%">&nbsp;</td>
		  </tr>
		  <tr>
		    <th colspan="2" scope="row">��ͥ��ϸ��ַ</th>
		    <td colspan="4">&nbsp;</td>
		    <td width="22%"><div align="center">�绰</div></td>
		    <td>&nbsp;</td>
		  </tr>
		  <tr>
		    <th width="5%" scope="row"><p>��</p>
		    <p>Ҫ</p>
		    <p>��</p>
		    <p>��</p></th>
		    <th colspan="7" scope="row">&nbsp;</th>
		  </tr>
		  <tr>
		    <th height="114" colspan="5" scope="row">
		      <div style="left:2px;top:1px;position:inherit">
		        <p>�����������</p>
		        <p>ǩ����</p>
		        <p>�� �� �� </p>
		    </div>	</th>
		    <td colspan="3"><blockquote>
		      <blockquote>
		        <p>ϵ��Ժ�������</p>
		        <p>ǩ����</p>
		        <p>�� �� �� </p>
		      </blockquote>
		    </blockquote></td>
		  </tr>
		  <tr>
		    <th height="127" colspan="8" scope="row"><p><bean:message key="lable.xsgzyxpzxy" />�����</p>
		    <p>���£�</p>
		    <p>�� �� �� </p></th>
		  </tr>
		</table>
    </html:form>
  </body>
</html:html>
