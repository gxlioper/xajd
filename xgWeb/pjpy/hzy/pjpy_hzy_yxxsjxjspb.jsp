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
    <html:form action="" method="post" focus="login">
      <div align="center" style="font:'����';font-size:18px;">����ѧ����ѧ��������</div>
<br>
<div>_______ϵ��Ժ��______________ѧ���_____ѧ��</div>
<table width="100%"  border="1">
  <tr>
    <th colspan="2" scope="col">�༶</th>
    <th width="25%" colspan="2" scope="col">&nbsp;</th>
    <th width="11%" scope="col">����</th>
    <th colspan="2" scope="col">&nbsp;</th>
    <th width="11%" scope="col">ְ��</th>
    <th colspan="2" scope="col">&nbsp;</th>
  </tr>
  <tr>
    <th colspan="2" scope="row">�ɼ�����</th>
    <td colspan="2">&nbsp;</td>
    <td><div align="center">�ۺ���������</div></td>
    <td colspan="2">&nbsp;</td>
    <td><div align="center">��ѧ��ȼ�</div></td>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <th width="6%" height="121" rowspan="9" scope="row">
	<p>��</p>
    <p>ѧ</p>
    <p>��</p>
    <p>��</p>
    <p>ѧ</p>
    <p>��</p>
    <p>��</p>
    <p>��</p></th>
    <th colspan="3" scope="row">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</th>
    <th colspan="2" scope="row">�� ��</th>
    <th colspan="3" scope="row">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��</th>
    <th width="12%" scope="row">�� ��</th>
  </tr>
  <tr>
    <th height="29" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th height="28" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th height="35" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th height="33" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th height="44" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th height="32" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th height="22" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th height="28" colspan="3" scope="row">&nbsp;</th>
    <th colspan="2" scope="row">&nbsp;</th>
    <th colspan="3" scope="row">&nbsp;</th>
    <th scope="row">&nbsp;</th>
  </tr>
  <tr>
    <th scope="row"><p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p></th>
    <th colspan="9" scope="row"> �� �� �� </th>
  </tr>
  <tr>
    <th scope="row"><p>ϵ</p>
    <p>��Ժ��</p>
    <p>��</p>
    <p>��</p></th>
    <th colspan="9" scope="row"><p>ǩ��</p>
    <p>�� �� ��</p></th>
  </tr>
  <tr>
    <th scope="row"><p>ѧ</p>
    <p>��</p>
    <p>��</p>
    <p>��</p>
    <p>��</p></th>
    <th colspan="9" scope="row">�� �� �� </th>
  </tr>
</table>
ע���˱�һʽ���ݣ�ϵ��Ժ����Ժѧ������һ��
<table width="100%"  border="1">
  <tr>
    <th scope="col">&nbsp;</th>
    <th scope="col">&nbsp;</th>
    <th scope="col">&nbsp;</th>
    <th scope="col">&nbsp;</th>
  </tr>
  <tr>
    <th scope="row">&nbsp;</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">&nbsp;</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">&nbsp;</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <th scope="row">&nbsp;</th>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
    </html:form>
  </body>
</html:html>
