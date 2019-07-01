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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/commFunction.js"></script>
	<object id="WebBrowser" name="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
<style media="print" type="text/css">
.brk{
	page-break-after:always;
}
</style>
  <body> 
    <html:form action="specialprise.do" method="post" >
<center>
<h3>上海工程技术大学学生自费出国留学（旅游）审批表</h3>
<table width="100%" class="tbstyle" id="rsTable">
	<tr>
    <td align="left" height="35" width="80px"><bean:message key="lable.xsgzyxpzxy" />、系</td>
    <td colspan="2">${rs.xymc }</td>
    <td align="center" height="35">专业</td>
    <td>${rs.zymc }</td>
    <td align="center" height="35" >班级</td>
    <td colspan="2">${rs.bjmc }</td>   
  </tr>
  <tr>
    <td align="left" height="35">学号</td>
    <td align="center" width="80">${rs.xh }</td>
    <td align="center">姓名</td>
    <td align="center" width="50">${rs.xm }</td>
    <td align="center">性别</td>
    <td align="center">${rs.xb }</td>    
    <td align="center" >政治面貌</td>
    <td align="center" >${rs.zzmm }</td>    
  </tr>
 <tr>
 	<td align="left" height="35">家庭住址</td>
 	<td colspan="3">${rs.jtdz }</td>   
 	<td align="center">电话</td>
 	<td colspan="3">${rs.lxdh }</td>   	
 </tr>
 <tr>
 	<td align="left" height="35">出国原因</td>
 	<td colspan="3">${rs.cgyy }</td>   
 	<td align="center">去何国家（学校）</td>
 	<td colspan="3">${rs.qhgj }</td>   	
 </tr>
 <tr height="35">
 	<td align="left" width="80px"> 经济担保<br/>情况（提<br/>供经济担<br/>保人关系）
	</td>
 	<td colspan="7" height="45">${rs.jjdbqk }</td> 
 </tr>
 <tr> 
         <td height="157" width="40%" align="left" valign="bottom" colspan="4"><bean:message key="lable.xsgzyxpzxy" />意见：（一） <p style="height:65">${rs.xyyj}</p>&nbsp;<p align="right">　盖　章　&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <p align="left">签名＿＿＿＿&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p align="right">年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</p></td>
         <td height="157" width="40%" align="left" valign="bottom" colspan="4">学生处意见：（二） <p style="height:65">${rs.xxyj}</p>&nbsp;<p align="right">　盖　章　&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <p align="left">签名＿＿＿＿&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p><p align="right">年&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;日</p></td>
       
 </tr>
 <tr>
 	<td height="157" align="left" valign="bottom" colspan="8">校领导意见：（三）<br/><br/><br/><br/><br/>
         <p align="left">&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>&nbsp;</p><p align="right"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p></td>
 </tr>
 <tr>
 <td colspan="8" align="left" >
 	<p align="left">备注：*审批程序按序号办理</p>
 </td>
 <tr>
	 <td colspan="8" align="left" >
	 	<p align="left"><b>说明：</b>1、出国旅游者，应按学校规定在节假日后返校，否则按旷课处理。<br/>
&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;2、出国留学者，应按学校规定办理离校手续，否则按擅自离校处理。
<br/>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;3，提交此表时，请附上本人签证复印件及国外大学录取通知书复印件。
</p>

	 </td>
 </tr>

</table>

</center>
</html:form>
			<div class='noPrin' align="center">
				<input type='button' class='button2' value='页面设置' onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览' onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印' onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>	
</body>
</html:html>
