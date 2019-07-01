<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    
    
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	
  </head>
  	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
  <script language="javascript" src="js/function.js"></script>
  <body>
    <html:form action="/pjpy_apply_print">
       <div class="title">
          <div class="title_img" id="title_m">
             ��ǰ����λ�ã��������� - ��ѧ������ - ��д�����
          </div>
       </div>
       <p align="center"><strong>�Ϻ����̼�����ѧ����ѧ����ѧ����ǿ��ѧ�������</strong></p>
<p align="right">������ڣ� �� �� �� </p>
<table  class="tbstyle" style="width:100%">
  <tr>
    <td width="35" rowspan="3"><p align="center">�� </p>
        <p align="center">�� </p>
        <p align="center">�� </p>
        <p align="center">�� </p></td>
    <td width="58" height="30"><p align="center">���� </p></td>
    <td colspan="2"><html:text name="rs" property="xh" /></td>
    <td><div align="center">�Ա�</div></td>
    <td colspan="2"><html:text name="rs" property="xb" /></td>
    <td><div align="center">����������</div></td>
    <td colspan="4"><html:text name="rs" property="csrq"/></td>
    <td width="22"><p align="center">���� </p></td>
    <td width="48"><html:text name="rs" property="mz" /></td>
  </tr>
  <tr>
    <td width="58" height="36"><p align="center">ѧ�� </p></td>
    <td colspan="4">&nbsp;</td>
    <td colspan="5"><p align="center">��ѧʱ�� </p></td>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="13"><p>��ѧ <bean:message key="lable.xsgzyxpzxy" /> ϵ �� </p></td>
  </tr>
  <tr>
    <td width="35"><p align="center">�ɼ�����</p></td>
    <td colspan="13"><p>�����񴦽�ѧ����ϵͳ�����Ƴɼ���������˼��Ʒ�������ɼ��������ɼ� </p></td>
  </tr>
  <tr>
    <td width="35" rowspan="5"><p align="center">����ȼ�</p></td>
    <td height="29" colspan="8"><p align="center">����ѧ����ѧ�� </p></td>
    <td height="29" colspan="5"><p align="center">�� ǿ </p></td>
  </tr>
  <tr>
    <td width="58" height="29" valign="top"><p align="center">�صȽ� </p></td>
    <td width="73" height="29" valign="top"><p align="center">һ�Ƚ� </p></td>
    <td width="69" height="29" valign="top"><p align="center">���Ƚ� </p></td>
    <td height="29" colspan="2" valign="top"><p align="center">���Ƚ� </p></td>
    <td height="29" colspan="2" valign="top"><p align="center">���¼� </p></td>
    <td width="93" height="29" valign="top"><p align="center">������ </p></td>
    <td height="29" colspan="2" valign="top"><p align="center">�� </p></td>
    <td height="29" colspan="3" valign="top"><p align="center">�� </p></td>
  </tr>
  <tr>
    <td width="58" height="32" valign="top">&nbsp;</td>
    <td width="73" height="32" valign="top">&nbsp;</td>
    <td width="69" height="32" valign="top">&nbsp;</td>
    <td height="32" colspan="2" valign="top">&nbsp;</td>
    <td height="32" colspan="2" valign="top">&nbsp;</td>
    <td width="93" height="32" valign="top">&nbsp;</td>
    <td height="32" colspan="2" valign="top">&nbsp;</td>
    <td height="32" colspan="3" valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td width="58" height="31" valign="top">&nbsp;</td>
    <td width="73" height="31" valign="top">&nbsp;</td>
    <td width="69" height="31" valign="top">&nbsp;</td>
    <td height="31" colspan="2" valign="top">&nbsp;</td>
    <td height="31" colspan="2" valign="top">&nbsp;</td>
    <td width="93" height="31" valign="top">&nbsp;</td>
    <td height="31" colspan="2" valign="top">&nbsp;</td>
    <td height="31" colspan="3" valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td width="58" height="34" valign="top">&nbsp;</td>
    <td width="73" height="34" valign="top">&nbsp;</td>
    <td width="69" height="34" valign="top">&nbsp;</td>
    <td height="34" colspan="2" valign="top">&nbsp;</td>
    <td height="34" colspan="2" valign="top">&nbsp;</td>
    <td width="93" height="34" valign="top">&nbsp;</td>
    <td height="34" colspan="2" valign="top">&nbsp;</td>
    <td height="34" colspan="3" valign="top">&nbsp;</td>
  </tr>
  <tr>
    <td height="123" colspan="14" valign="top"><p><bean:message key="lable.xsgzyxpzxy" />�������� </p></td>
  </tr>
  <tr>
    <td height="216" colspan="14" valign="top"><p>ѧУ�������� </p></td>
  </tr>
</table>
    </html:form>
  </body>
</html:html>
