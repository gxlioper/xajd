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
  <!-- 打印控件begin -->
<object id="WebBrowser" width=0 height=0
	classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<style media='print'>
		.noPrin{display:none;}
	</style>
<!-- end -->
  <body>
    <html:form action="/dxjxjsp">
      <div align="center" style="font-size:18px;font:'黑体' "><bean:write name='rs' property="title"/></div>
<br/>
<div>


&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<bean:write name='rs' property="xn"/>&nbsp;学年  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xymc"/> &nbsp;&nbsp;${rs.bjmc }&nbsp;&nbsp;  <bean:write name='rs' property="xq"/></div>
<table width="100%" class="printstyle">
  <tr>
    <th height="30" colspan="2" width="14%" scope="col">姓   &nbsp;&nbsp;&nbsp; 名</th>
    <td height="30" colspan="2" style="width:20%" scope="col" align="center">&nbsp;<bean:write name='rs' property="xm"/></td>
    <th height="30" width="11%" scope="col">性  别</th>
    <td height="30" colspan="2" scope="col" style="width:15%" align="center">&nbsp;<bean:write name='rs' property="xb"/></td>
    <th height="30" width="10%" scope="col">职  务</th>
    <td height="30" colspan="2" scope="col" align="center">&nbsp;<bean:write name='rs' property="drzw"/></td>
  </tr>
  <tr>
    <th height="30" colspan="2" scope="row">政治面貌</th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="zzmm"/></td>
    <th><div align="center">民  族</div></th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="mz"/></td>
    <th colspan="2"><div align="center">综合评分名次/总分成绩名次</div></th>
    <td colspan="" width="13%" align="center">&nbsp;<bean:write name='rs' property="zhpfmc"/>/<bean:write name='rs' property="cjmc"/></td>
  </tr>
  <tr>
    <th height="30" colspan="2" scope="row">家庭详细地址</th>
    <td colspan="5" align="center">&nbsp;<bean:write name='rs' property="jtdz"/></td>
    
    <th><div align="center">电  话</div></th>
    <td colspan="2" align="center">&nbsp;<bean:write name='rs' property="sjhm"/></td>
  </tr>
  
  <tr>
    <th scope="row" style="width:6%">

    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>主</p>
    <p>要</p>
    <p>事</p>
    <p>迹</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>

    </th>
    <th colspan="9" scope="row"><p  align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="zysj"/></p>
   </th>
  </tr>
  <tr>
  	<th scope="row" colspan="5">
  		<p align="left">&nbsp;&nbsp; 班主任意见：</p>
  	
  		
  		<p align="left"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="fdyyj"/> </p>
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<p align="center">签名：<bean:write name="rs" property="fdyqm"/></p>
  		<p align="right"><bean:write name="rs" property="fdyshyear"/> 年 <bean:write name="rs" property="fdyshmon"/> 月 <bean:write name="rs" property="fdyshdate"/> 日 &nbsp;&nbsp;&nbsp;</p>
  	</th>
  	<th scope="row" colspan="5">
  		<p align="left">&nbsp;&nbsp; （系）部意见：</p>
  
  		
  		<p align="left"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xyyj"/> </p>
  		
  		<p>&nbsp;</p>
  		<p>&nbsp;</p>
  		<p align="center">签名：<bean:write name="rs" property="xyqm"/></p>
  		<p align="right"><bean:write name="rs" property="xyshyear"/> 年 <bean:write name="rs" property="xyshmon"/> 月 <bean:write name="rs" property="xyshdate"/> 日 &nbsp;&nbsp;&nbsp;</p>
  	</th>
  </tr>
  <tr>
  	<th scope="row" colspan="10">
  		<p align="left">&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />意见：</p>
  		
  		<p align="left"><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="fkyj"/> </p>
  		
  		<p>&nbsp;</p>
  		<p align="right">盖章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		</p>
  		<p align="right"><bean:write name="rs" property="xxshyear"/> 年 <bean:write name="rs" property="xxshmon"/> 月 <bean:write name="rs" property="xxshdate"/> 日 &nbsp;&nbsp;&nbsp;</p>
  	</th>
  </tr>
</table>
<div align="center" class='noPrin'>
			<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				直接打印
			</button>
		</div>
<!-- 注：此表一式两份，系（院）、院学生处各一份 -->
    </html:form>
  </body>
</html:html>
