<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

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
    
    <title><bean:message key="lable.title" /></title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <% 
	response.setHeader("Pragma","no-cache");
	response.setHeader("Cache-Control","no-cache");	
	response.setHeader("Expires","0");	
	%>	
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
  </head>
  
  <body>
    <html:form action="/xpjjdksqb" method="post">
		<p align="center" style="font-size:24px" ><b>�㽭��ѧ��ƽ����������ѧ�����������</b> </p>
		<p align="center">&nbsp; </p>
		<input type="hidden" id="xb" value="<bean:write name="rs" property="xb" />">
		<table id="theTable" width="100%" class="tbstyle">
				  <tr>
				    <td height="31" colspan="2"><p>���������&nbsp;&nbsp;<bean:write name="rs" property="xm"/></p></td>
				    <td height="31" colspan="4">
				   	  <logic:equal value="��" name="xb">
				   	     <p align="center">�Ա�&nbsp;&nbsp;&nbsp;&nbsp;[��] �� [ ] Ů</p>
				   	  </logic:equal>
				      <logic:equal value="Ů" name="xb">
				         <p align="center">�Ա�&nbsp;&nbsp;&nbsp;&nbsp;[ ] �� [��] Ů</p>
				      </logic:equal>
				    </td>
				    <td height="31">��������&nbsp;&nbsp;<bean:write name="rs" property="csrq"/></td>
				  </tr>
				  <tr>
				    <td height="31" colspan="2"><p>�Ͷ�ѧУ���㽭��ѧ </p></td>
				    <td height="31" colspan="4"><p>���֤����&nbsp;&nbsp;<bean:write name="rs" property="sfzh"/></p></td>
				    <td width="183" height="31"><p>�꼶&nbsp;&nbsp;<bean:write name="rs" property="nj" /></p></td>
				  </tr>
				  <tr>
				    <td width="161" height="31"><p><bean:message key="lable.xsgzyxpzxy" />&nbsp;&nbsp;<bean:write name="rs" property="xy"/></p></td>
				    <td height="31" colspan="4">רҵ&nbsp;&nbsp;<bean:write name="rs" property="zymc"/>&nbsp;&nbsp;</td>
				    <td width="158" height="31">����绰&nbsp;&nbsp;<bean:write name="rs" property="qsdh" /></td>
				    <td width="183" height="31"><p>ѧ��&nbsp;&nbsp;<bean:write name="rs" property="xh"/></p></td>
				  </tr>
				  <tr>
				    <td width="161" height="31" align="left"><p>ѧ��</p></td>
				    <td height="31" colspan="6"><p>���� ��&nbsp;<bean:write name="rs" property="xz"/>&nbsp;���� </p></td>
				  </tr>
				  <tr>
				    <td width="161" height="31"><p>��������� </p></td>
				    <td height="31" colspan="6"><p>�ܶ� &nbsp;<bean:write name="rs" property="sqdkje" />&nbsp;Ԫ </p></td>
				  </tr>
				  <tr>
				    <td width="161" height="31"><p>�������� </p></td>
				    <td height="31" colspan="6"><p>�������� &nbsp;<bean:write name="rs" property="dkqx" />&nbsp;���� </p></td>
				  </tr>
				  <tr>
				    <td colspan="3" rowspan="3" valign="top"><p>��ͥ��ϸסַ�� </p>
				        <p>&nbsp;<bean:write name="rs" property="szzq"/> ʡ���������� </p>
				        <p>&nbsp;<bean:write name="rs" property="sxq"/>�У��ء����� </p>
				        <p>�ʱࣺ&nbsp;<bean:write name="rs" property="yzbm"/>&nbsp; ��ͥ�绰��&nbsp;<bean:write name="rs" property="jtdh"/>&nbsp; </p>
				        <p>�ҳ��򷨶��໤������ ��&nbsp;<bean:write name="rs" property="jhr1_xm"/>&nbsp;</p>
				        <p>ְҵ��&nbsp;<bean:write name="rs" property="jhr1_zy"/>&nbsp; </p>
				        <p>�������֤���룺&nbsp;<bean:write name="rs" property="jhr1_sfzh"/>&nbsp;</p>
				        <p>������λ���ƣ�&nbsp;<bean:write name="rs" property="jhr1_gzdw"/>&nbsp;</p>
				        <p>�ҳ��򷨶��໤������ ��&nbsp;<bean:write name="rs" property="jhr2_xm"/>&nbsp;</p>
				        <p>ְҵ��&nbsp;<bean:write name="rs" property="jhr2_zy"/>&nbsp;</p>
				        <p>�������֤���룺&nbsp;<bean:write name="rs" property="jhr2_sfzh"/>&nbsp;</p>
				        <p>������λ���ƣ�&nbsp;<bean:write name="rs" property="jhr2_gzdw"/>&nbsp;</p></td>
				    <td colspan="4" valign="top"><p>&nbsp;</p>
				      <p><strong>���˱�֤������д������ʵ����</strong><strong>�� </strong></p>
				      <p>&nbsp;</p>
				      <p>��������ˣ�ǩ�֣��� </p>
				      <p align="right">�� ���� ����</p></td>
				  </tr>
				  <tr>
				    <td colspan="4" valign="top"><p>�ҳ������ </p>
				        <p>ͬ�� ���� <strong>��ƽ����������ѧ��� </strong><strong></strong></p>
				        <p><strong>�ҳ���ǩ�֣��� </strong><strong></strong></p>
				        <p><strong></strong> �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� �� ���� ���� ���� <strong></strong></p></td>
				  </tr>
				  <tr>
				    <td colspan="4" valign="top"><p><bean:message key="lable.xsgzyxpzxy" />����� </p>
				        <p><strong>���������ϵ��Ժ�Ͷ�ѧ�� </strong><strong>, </strong><strong>��������������ʵ���ҷ�����ƽ����������ѧ�������������� </strong></p>
				        <p>&nbsp; </p>
				        <p>�����ˣ� <bean:message key="lable.xsgzyxpzxy" /> ( ǩ�� ): </p>
				        <p align="right">�ꡡ �¡� �� </p></td>
				  </tr>
				  <tr>
				    <td colspan="7"><p>�㽭��ѧѧ������������������� </p>
				      <p>&nbsp;</p>      
				      <p> ( ǩ�� ) ������������������������ǩ�֣��� ������������������������ �� �� �� �� �� �� �� �� �� �� ���� ���� ���� </p></td>
				  </tr>
				  <tr>
				    <td colspan="7"><p>�㽭��ѧ��ƽ����������ѧ�������ίԱ������� </p>
				      <p>&nbsp;</p>      <p>�����ˣ�ǩ�֣������������������������������������������������� �� �� �� �� �� �� �� �� �� �� �� �� �� ���� ���� </p></td>
				  </tr>
				  <tr>
				    <td colspan="7"><p>&nbsp; ��ע�� </p>
				      <p>&nbsp;</p></td>
				  </tr>
		</table>
		<p align="right">&nbsp; ����Ҽʽ���ݣ�����������<bean:message key="lable.xsgzyxpzxy" />��Уѧ������������������ἰ�����߱��˸�Ҽ�ݡ� </p>
    </html:form>
    <div align=center>
    	<input type=button value=��ӡ onclick="expTab('theTable','�㽭��ѧ��ƽ����������ѧ�����������');" />
    </div>
  </body>
</html:html>
