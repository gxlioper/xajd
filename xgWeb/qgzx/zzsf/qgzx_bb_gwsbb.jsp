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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
  <head>
    
    <title>MyJsp.jsp</title>

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
  	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
  <body>
    <html:form action="/qgzx_bb_gwsbb">
    <div id="rsT">
    <table align="center">
    	<tr>
    		<td align="center"><p align="center"><strong><bean:write name="xxmc" scope="request"/>У���ڹ���ѧ����걨�� </strong></p></td>
    	</tr>
    </table>
    <br/>
	<table class="tbstyle" align="center" width="70%">
  		<tr>
    		<td width="144"><p align="center">�ù����뵥λ </p></td>
    		<td width="180" colspan="2"><bean:write name="rs" property="yrdwmc"/></td>
    		<td width="96" colspan="3" nowrap="nowrap"><p align="center">�칫�ص� </p></td>
    		<td width="276"></td>
  		</tr>
  		<tr>
    		<td width="144"><p align="center">������ </p></td>
    		<td width="180" colspan="2"><bean:write name="rs" property="fzr"/></td>
    		<td width="96" colspan="3" nowrap="nowrap"><p align="center">��ϵ�绰 </p></td>
    		<td width="276"><bean:write name="rs" property="lxdh"/></td>
  		</tr>
  		<tr>
    		<td width="144"><p align="center">����������� </p></td>
    		<td width="552" colspan="6"><p>&nbsp;&nbsp;�̶���λ���� &radic; �� &piv; ��ʱ��λ���� &radic; �� &piv; </p></td>
  		</tr>
  		<tr>
    		<td width="144" align="center">
    			<p>&nbsp;</p>
    			<p>&nbsp;</p>
    			<p>&nbsp;</p>
    			��λ��������
    			<p>&nbsp;</p>
    			<p>&nbsp;</p>
    			<p>&nbsp;</p>
    		</td>
    		<td width="552" colspan="6" valign="top"><bean:write name="rs" property="gznr"/></td>
  		</tr>
  		<tr>
   			<td width="144"><p align="center">��λ�� </p></td>
    		<td width="96"><bean:write name="rs" property="xyrs"/></td>
    		<td width="144" colspan="3"><p align="center">�ù�����Ҫ�� </p></td>
    		<td width="312" colspan="2"></td>
  		</tr>
  		<tr>
    		<td width="144"><p align="center">����Ҫ�� </p></td>
    		<td width="552" colspan="6"></td>
  		</tr>
  		<tr>
    		<td width="325" colspan="4" valign="top">
    			<br/>
    			<p align="center">�ù����뵥λ������ <br/>
        		��������ǩ�ֲ��Ӹǹ��£�
        		<p>&nbsp;</p>
        		<p align="right">�� �� �� </p></td>
    		<td width="371" colspan="3" valign="top">
    			<br/>
    			<p align="center">������������� </p>
    			<p>&nbsp;</p>
        		<p align="right">�����ţ����£� </p>
        	<p align="right">�� �� �� </p></td>
  		</tr>
	</table>
    </div>
    <div class="buttontool" align="center">
		<button type="button" class="button2" onclick="expAppTab('rsT','<bean:write name="xxmc" scope="request"/>У���ڹ���ѧ����걨��','')">
				�� ӡ �� ��
		</button>
	</div>
	</html:form>
  </body>
</html:html>
