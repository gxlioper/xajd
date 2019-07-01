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

  </head>
  <%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
  <body>
  	<div id="rsT">
    <p align="center">ѧ����λ����� </p>
	<table class="tbstyle" id="tsT" align="center" width="70%">
  		<tr>
    		<td width="19%"><p align="right">* ѧ�ţ� </p></td>
    		<td width="17%">&nbsp;&nbsp;</td>
    		<td width="29%"><p align="right">* ��λ���ƣ� </p></td>
    		<td width="33%">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="19%"><p align="right">������ </p></td>
    		<td width="17%">&nbsp;</td>
    		<td width="29%"><p align="right">��ȣ� </p></td>
    		<td width="33%">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="19%"><p align="right">�Ա� </p></td>
    		<td width="17%">&nbsp;</td>
    		<td width="29%"><p align="right">ѧ�꣺ </p></td>
    		<td width="33%">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="19%"><p align="right">�꼶�� </p></td>
    		<td width="17%">&nbsp;</td>
    		<td width="29%"><p align="right">ѧ�ڣ� </p></td>
    		<td width="33%">&nbsp;</td>
  		</tr>
  		<tr>
   			<td width="19%"><p align="right"><bean:message key="lable.xsgzyxpzxy" />�� </p></td>
    		<td width="17%">&nbsp;</td>
    		<td width="29%"><p align="right">��ϵ�绰�� </p></td>
    		<td width="33%">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="19%"><p align="right">רҵ�� </p></td>
    		<td width="17%">&nbsp;</td>
    		<td width="29%"><p align="right">�Ƿ��������� </p></td>
    		<td width="33%"><p align="center">��������ƶ�����������룩 </p></td>
  		</tr>
  		<tr>
    		<td width="19%"><p align="right">�༶�� </p></td>
    		<td width="17%">&nbsp;</td>
    		<td width="29%">&nbsp;</td>
    		<td width="33%">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="19%"><p align="right">�������ɣ� </p></td>
    		<td width="80%" colspan="3">&nbsp;</td>
  		</tr>
  		<tr>
    		<td width="19%"><p align="right">��ע�� </p></td>
    		<td width="80%" colspan="3">&nbsp;</td>
  		</tr>
	</table>
	</div>
	<div class="buttontool" align="center">
		<button type="button" class="button2" onclick="expAppTab('rsT','','')">
				�� ӡ �� ��
		</button>
	</div>
  </body>
</html:html>
