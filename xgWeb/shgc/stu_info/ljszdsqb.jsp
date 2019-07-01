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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commFunction.js"></script>
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
<style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
  <body> 
    <html:form action="specialprise.do" method="post" >
<center>
<h3>�����ҵ������ת�������</h3>
<table width="100%" class="tbstyle" id="rsTable">
	<tr height="45">
    <td align="center">Ժ��ϵ</td>
    <td width="180">&nbsp;${rs.xymc}&nbsp;${rs.zymc}</td>
    <td align="center" width="80">�༶��ѧ��</td>
    <td colspan="3">&nbsp;${rs.bjmc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xh}</td>      
  </tr>
  <tr height="45">
  <td align="center">����</td>
    <td >&nbsp;${rs.xm}</td>
    <td align="center">�Ա�</td>
    <td >&nbsp;${rs.xb}</td>
    <td align="center">��������</td>
    <td width="120">&nbsp;${rs.sqrq}</td>      
  </tr>
 <tr height="45">
 	<td align="center">�绰��������ϵ��ʽ</td>
 	<td colspan="5">&nbsp;${rs.lxfs}</td>    	   	
 </tr>
 <tr height="45">
 	<td align="center">������������</td>
 	<td colspan="2">&nbsp;${rs.hkssqx}</td>   
 	<td align="center">�����ֵ�</td>
 	<td colspan="2">&nbsp;${rs.hkssjd}</td>   	
 </tr>
 <tr height="45">
 	<td align="center">����ת���صĵ�λ���ƣ�ȫ�ƣ�</td>
 	<td colspan="5">&nbsp;${rs.zddwmc}</td>    	   	
 </tr>
 <tr height="45">
 <td align="center">��ϸ��ַ</td> 
 <td colspan="2">&nbsp;${rs.zddwdz}</td>
 <td >�ʱ�</td>
 <td colspan="2">&nbsp;${rs.zddwyb}</td>
 </tr>
 <tr><td colspan="6" height="200px">
 <p style="height:200px">
  �����������ɣ�<br/>${rs.sqly}
  <div align="right">ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><p/>
 </td></tr>
 <tr>
 <td height="40">����ת��ʱ��</td>
 <td colspan="5">&nbsp;</td>
 </tr>
</table>
</center>
</html:form>
</body>
<div class='noPrin' align="center">
	<input type='button' class='button2' value='ҳ������'
		onclick="WebBrowser.ExecWB(8,1);return false;">
	<input type='button' class='button2' value='��ӡԤ��'
		onclick="WebBrowser.ExecWB(7,1);return false;">
	<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
		onclick="WebBrowser.ExecWB(6,6);return false;">
</div>
</html:html>
