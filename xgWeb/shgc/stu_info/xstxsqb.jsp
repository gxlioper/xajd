<%@ page language="java" pageEncoding="GB2312" contentType="text/html;charset=GBK"%>

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
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<style media='print'>.noPrin{display:none}
	</style>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main2.css" type="text/css" media="all" />
	<object id="WebBrowser" name="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commFunction.js"></script>
	</head>
<style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
  <body> 
    <html:form action="specialprise.do" method="post" >
<center>
<h3>�Ϻ����̼�����ѧ������ѧ��</h3>
<table width="100%" class="tbstyle" id="rsTable">
	<tr height="45">
    <td align="center" >����������</td>
    <td width="180">${rs.xm}</td>
    <td align="center">��������</td>
    <td>${rs.sqrq}</td>      
  </tr>
  <tr height="45">
    <td align="center">��   ��</td>
    <td >${rs.xb}</td>
    <td align="center">���֤����</td>
    <td>${rs.sfzh}</td>      
  </tr>
 <tr height="45">
 	<td align="center">ѧ   Ժ</td>
 	<td>${rs.xymc}</td>   
 	<td align="center">ר    ҵ</td>
 	<td>${rs.zymc}</td>   	
 </tr>
 <tr height="45">
 	<td align="center">��   ��</td>
 	<td>${rs.bjmc}</td>   
 	<td align="center">ѧ    ��</td>
 	<td>${rs.xh}</td>   	
 </tr>
 <tr height="45">
 <td align="center">��ϵ�绰</td> 
 <td colspan="3"><p>�ֻ����룺&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.sjhm}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �����绰��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.qtdh}  </p></td>
 </tr>
 
 <tr>      
  <td height="80" align="center">
 ��<br/>
 ��<br/>
 ��<br/>
 ��<br/>
 </td>
 <td colspan="3">
 <p style="height:85">${rs.sqly}</p>
 <p align="right"> ѧ �� �� �� ǩ �� _____________<br/><br/>
  �ꡡ���¡�����
 </p>
 </td>    
 </tr>
 
  <tr>      
  <td height="80" align="center">
 ��<br/>
 ��<br/>
 ��<br/>
 ��<br/>
 </td>
 <td colspan="3">
 <br/><br/><br/><br/>
 <p align="right"> �� �� ǩ �� _____________<br/><br/>
 �ꡡ���¡�����
 </p>
 </td>    
 </tr>
 
  <tr>      
  <td height="80" align="center">
 ѧ<br/>
 Ժ<br/>
 ��<br/>
 ��<br/>
 </td>
 <td colspan="3">
 <p style="height:85">${rs.xyyj}</p>
 <p align="right"> ǩ �� _____________<br/><br/>
 �ꡡ���¡�����
 </p>
 </td>    
 </tr>
 
  <tr>      
  <td height="80" align="center">
 ��<br/>
 ��<br/>
 ��<br/>
 ��<br/>
 ��<br/>
 ��<br/>
 </td>
 <td colspan="3">
 <p style="height:85">${rs.xxyj}</p>
 <p align="right"> ǩ �� _____________<br/><br/>
 ��&nbsp;&nbsp;�ꡡ���¡�����
 </p>
 </td>    
 </tr>
</table>
</center>
</html:form>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='ҳ������' onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��' onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ' onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>	
</body>
</html:html>
