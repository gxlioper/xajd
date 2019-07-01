<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	</head>

<html:html>
  <style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
  <body> 
    <html:form action="szdwfzjy.do" method="post" >
<center>
<h3> �������۱�</h3>
<table width="90%" class="tbstyle">  
  <tr>
    <td width="15%" height="35" align="center">����Ա����</td>
    <td width="35%"><bean:write name='rs' property="xm"/><br></td>
    <td width="15%" align="center">����<bean:message key="lable.xsgzyxpzxy" /></td>
    <td ><bean:write  name='rs' property="bmmc"/></td>
  </tr>
  <tr>
    <td align="center" height="35">������</td>
    <td ><bean:write name='rs' property="fzgz" /></td>
    <td align="center">��������</td>
    <td><bean:write  name='rs' property="fzrs"/></td>
  </tr>

  <tr>
    <td height="337" colspan="4">�������С�᣺ <br>
	<bean:write name = "rs" property="grndxj" /></td>
  </tr>
  <tr>
    <td height="85" colspan="4">�ӷ֣��ṩ˵������<bean:write  name = "rs" property="delfsm" /></td>
  </tr>
  <tr>
    <td height="96" colspan="4">���֣��ṩ˵������ <bean:write  name = "rs" property="addfsm" /></td>
  </tr>
  <tr>
    <td height="104" colspan="4">��ɫ������ <bean:write name = "rs" property="tsgz"  /></td>
  </tr>
</table>
<table width="90%" class="tbstyle">
    <tr>
    		<td width="20%"  rowspan="2"><bean:message key="lable.xsgzyxpzxy" />ͨ������</td>
    		<td width="20%" height="18" >
    			<p align="center">������</p>	  </td>
    		<td width="20%" >
    			<p align="center">�ӷ�</p>
    		</td>
    		<td width="20%" >
    			<p align="center">����</p>
    		</td>
    		<td width="20%" >
    			<p align="center">�ܷ�</p> 
    		</td>
   	</tr>
    	<tr>
    		<td >&nbsp;</td>
    		<td >&nbsp;</td>
    		<td >&nbsp;</td>
    		<td >&nbsp; <bean:write  name = "rs" property="fz" /></td>
    	</tr>
</table>
</center>
</html:form>
</body>
</html:html>
