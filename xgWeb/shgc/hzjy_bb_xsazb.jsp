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
    <base target="_self" />
    
    <title>hzjy_bb_xsazb.jsp</title>

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
    <p><strong><font size="4" face="������κ">��д˵���� </font></strong></p>
<p><strong><font size="4" face="������κ">1�����༶,����ѧ����ѧ��˳����д����ѧ�����������ñ��� </font></strong></p>
<p><strong><font size="4" face="������κ">2�����ձ��Ҫ����д���е����ݣ����ݱ����꾡��ʵ; </font></strong></p>
<p><strong><font size="4" face="������κ">3������еġ�������ָ�Ϻ��е������������硰��������Ӧ��д�����������������������д����ڡ��� </font></strong></p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p align="center"><strong><font size="5">�Ϻ����̼�����ѧ</font> <bean:write name="xymc"/> <font size="4"><bean:message key="lable.xsgzyxpzxy" />��ѧ�����������ñ�</font> </strong></p>
<p align="center"><strong>�� �� �� ��--- �� �գ� </strong><strong></strong></p>
<p><strong>�༶�� <bean:write name="rs" property="bjmc"/></strong>&nbsp;&nbsp;&nbsp;<strong>�༶������<bean:write name="rs" property="bjrs"/> </strong><strong>�� </strong><strong>�೤�� </strong><strong>�����Σ� </strong><strong></strong></p>
<table width="100%" class="tbstyle" align="center">
  <tr>
    <td width="33"><p align="center">��� </p></td>
    <td width="53"><p align="center">�� �� </p></td>
    <td width="66"><p align="center">ѧ �� </p></td>
    <td width="55"><p align="center">��ͥ�绰 </p></td>
    <td width="69"><p align="center">�ֻ����� </p></td>
    <td width="147"><p align="center">������λȫ�� </p></td>
    <td width="169"><p align="center">������λ��ַ </p></td>
    <td width="35"><p align="center">���� </p></td>
    <td width="45"><p align="center">�� �� </p></td>
    <td width="48"><p align="center">��ϵ�� </p></td>
    <td width="45"><p align="center">�� �� </p></td>
    <td width="57"><p align="center">��ϵ�绰 </p></td>
    <td width="69"><p align="center">�ֻ����� </p></td>
    <td width="45"><p align="center">Э��Ա </p></td>
    <td width="71"><p align="center">�ֻ����� </p></td>
  </tr>
  <logic:iterate id="v" name="rs1">
  <tr>
    <td width="33"><bean:write name="v" property="bh"/></td>
    <td width="53"><bean:write name="v" property="xm"/></td>
    <td width="71"><bean:write name="v" property="xh"/></td>
    <td width="66"><bean:write name="v" property="jtdh"/></td>
    <td width="55"><bean:write name="v" property="sjh"/></td>
    <td width="69"><bean:write name="v" property="gzdwqc"/></td>
    <td width="147"><bean:write name="v" property="gzdwdz"/></td>
    <td width="169"><bean:write name="v" property="qy"/></td>
    <td width="35"><bean:write name="v" property="yzbm"/></td>
    <td width="45"><bean:write name="v" property="lxr"/></td>
    <td width="48"><bean:write name="v" property="bm"/></td>
    <td width="45"><bean:write name="v" property="lxdh"/></td>
    <td width="57"><bean:write name="v" property="lxrsjh"/></td>
    <td width="69"><bean:write name="v" property="xty"/></td>
    <td width="45"><bean:write name="v" property="xtysjh"/></td>
  </tr>
  </logic:iterate>
</table>
  </body>
</html:html>
