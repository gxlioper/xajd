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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	</head>
<style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
  <body> 
    <html:form action="xsgbForNblg.do" method="post" >
<center>
<h3>学生组织申报表</h3>
<table width="90%" class="tbstyle">  
  <tr>
    <td width="110" align="center" height="35">学年</td>
    <td width="110" align="center"><bean:write name="rs" property="xn"/></td>
    <td width="50" align="center">学期</td>
    <td width="110" align="center" ><bean:write name="rs" property="xq"/></td>
    <td width="50" align="center">组织名称</td>
    <td colspan="3" align="center"><bean:write name="rs" property="zzmc"/></td>
  </tr>
  <tr>
    <td align="center" height="35">主要负责人</td>
    <td align="center"><bean:write name="rs" property="zyfzr"/></td>
    <td width="50" align="center">联系方式</td>
    <td ><bean:write name="rs" property="fzrlxfs"/></td>
    <td width="50" align="center">指导教师</td>
    <td ><bean:write name="rs" property="zdls"/></td>
    <td width="50" align="center">联系方式</td>
    <td ><bean:write name="rs" property="lslxfs"/></td>
  </tr>
  <tr>
    <td align="center" height="70">项目级别</td>
    <td ><bean:write name="rs" property="xmjb"/></td>
    <td align="center" colspan="2">所属科目</td>
    <td colspan="4"><bean:write name="rs" property="xmmc"/></td>
  </tr>
  <tr>
    <td align="center" height="140">组织成员数</td>
    <td ><bean:write name="rs" property="zzcys"/></td>
    <td align="center" colspan="2">所属部门</td>
    <td colspan="4"><bean:write name="rs" property="bmmc"/></td>
  </tr>
  <tr>
    <td height="200" height="140">学生干部类别</td>
    <td colspan="7">&nbsp;&nbsp;&nbsp;&nbsp;
    <logic:iterate id="bgbzl" name="xsgbzlList" offset="0">
									<logic:equal name = "bgbzl" property="checks" value ="checked" > 
									<bean:write name = "bgbzl" property="bgbmc"/>
									</logic:equal>					
	</logic:iterate>
	</td>
  </tr>
</table>
</center>
</html:form>
</body>
</html:html>
