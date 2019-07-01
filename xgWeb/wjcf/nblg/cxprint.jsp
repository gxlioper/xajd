<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
  <head>
    <title><bean:message key="lable.title" /></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<style media='print'>
		.noPrin{display:none;}
	</style>
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>	

	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
  </head>
  <body>
    <html:form action="/dxjxjsp">
      <div align="center" style="font-size:22px;font:'黑体' "><b>学生处分撤消呈报审批表</b></div>
<br/>
<div>
<table width="100%" class="printstyle">
  <tr>
    <th height="30" colspan="2" width="14%" scope="col">姓&nbsp;&nbsp;&nbsp;名</th>
    <td height="30" colspan="2" style="width:20%" scope="col" align="center">&nbsp;<bean:write name='rs' property="xm"/></td>
    <th height="30" width="11%" scope="col">性&nbsp;&nbsp;&nbsp;别</th>
    <td height="30" colspan="2" scope="col" style="width:15%" align="center">&nbsp;<bean:write name='rs' property="xb"/></td>
    <th height="30" width="10%" scope="col">出生年月</th>
    <td height="30" colspan="2" scope="col" align="center">&nbsp;<bean:write name='rs' property="csrq"/></td>
  </tr>
  <tr>
    <th height="30" colspan="2" scope="row">籍&nbsp;&nbsp;&nbsp;贯</th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="jg"/></td>
    <th><div align="center">班&nbsp;&nbsp;&nbsp;级</div></th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="bjmc"/></td>
    <th colspan=""><div align="center">学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</div></th>
    <td colspan="2" width="13%" align="center">&nbsp;<bean:write name='rs' property="xh"/></td>
  </tr>
  <tr>
    <th height="30" colspan="2" scope="row">所受处分</th>
    <td colspan="5" align="center">&nbsp;<bean:write name='rs' property="cflbmc"/></td>
    <th colspan=""><div align="center">处分时间</div></th>
    <td colspan="2" width="13%" align="center">&nbsp;<bean:write name='rs' property="cfsj"/></td>
  </tr>
  <tr>
    <th scope="row" style="width:6%">
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>现</p>
    <p>实</p>
    <p>表</p>
    <p>现</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    </th>
    <th colspan="9" scope="row"><p  align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="bz"/></p>
   </th>
  </tr>
  <tr>
  	<th scope="row" colspan="10">
  		<p align="left">&nbsp;&nbsp;所在分院意见：</p>
  		<p align="left"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="fkyj"/> </p>
  		<p>&nbsp;</p>
  		<p align="right">负责人签名(公章)：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		</p>
  		<p align="right"><bean:write name="rs" property="xxshyear"/> 
  		年&nbsp;&nbsp;&nbsp; <bean:write name="rs" property="xxshmon"/> 
  		月&nbsp;&nbsp;&nbsp; <bean:write name="rs" property="xxshdate"/> 
  		日&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  	</th>
  </tr>
  <tr>
  	<th scope="row" colspan="5">
  		<p align="left">&nbsp;&nbsp; 学生处意见：</p>
  		<p align="left"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="fdyyj"/> </p>
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<p align="center">签名：</p>
		<p align="right"><bean:write name="rs" property="xxshyear"/> 
  		年&nbsp;&nbsp;&nbsp; <bean:write name="rs" property="xxshmon"/> 
  		月&nbsp;&nbsp;&nbsp; <bean:write name="rs" property="xxshdate"/> 
  		日&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>  		
  	</th>
  	<th scope="row" colspan="5">
  		<p align="left">&nbsp;&nbsp; 分管院领导意见：</p>		
  		<p align="left"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xyyj"/> </p>
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<p align="center">签名：</p>
  		<p align="right"><bean:write name="rs" property="xxshyear"/> 
  		年&nbsp;&nbsp;&nbsp; <bean:write name="rs" property="xxshmon"/> 
  		月&nbsp;&nbsp;&nbsp; <bean:write name="rs" property="xxshdate"/> 
  		日&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
  	</th>
  </tr>
</table>
	<div style="font-size:15px;font:'黑体' ">
		<b>&nbsp;&nbsp;本呈报表需附事实材料。</b>
	</div>
	</div>
    </html:form>
    <br/>
    <div align="center" class='noPrin'>
	<button type="button" class='button2'  onclick="WebBrowser.ExecWB(8,1);return false;">页面设置</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(7,1);return false;">打印预览</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(6,6);return false;">直接打印</button> 
    </div>
  </body>
</html:html>
