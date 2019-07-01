<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
    <html:form action="specialprise.do" method="post" >
<center>
<h3>毕业生档案转移申请表</h3>
 <table width="100%" id="rsT" class="printstyle"> 
	<tr height="45">
	  <td height="38" align="center">入校时间</td>
	  <td>&nbsp;&nbsp;&nbsp;${rs.rxsj}</td>
	  <td align="center">毕业时间</td>
	  <td colspan="3">&nbsp;&nbsp;&nbsp;${rs.bysj}</td>
    </tr>
	<tr height="45">
    <td width="158" height="38" align="center">院、系</td>
    <td width="125">&nbsp;&nbsp;&nbsp;${rs.xymc}&nbsp;${rs.zymc}</td>
    <td align="center" width="81">班级、学号</td>
    <td colspan="3">${rs.bjmc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xh}</td>      
  </tr>
  <tr height="45">
  <td height="36" align="center">姓名</td>
    <td >&nbsp;&nbsp;&nbsp;${rs.xm}</td>
    <td align="center">性别</td>
    <td width="55" >${rs.xb}</td>
    <td width="45" align="center">申请日期</td>
    <td width="119">&nbsp;&nbsp;&nbsp;${rs.sqrq}</td>      
  </tr>
 <tr height="45">
 	<td height="36" align="center">电话或其他联系方式</td>
 	<td colspan="5">&nbsp;&nbsp;&nbsp;${rs.lxfs}</td>    	   	
 </tr>
 <tr height="45">
 	<td height="34" align="center">户口所属区县</td>
 	<td colspan="2">&nbsp;&nbsp;&nbsp;${rs.hkssqx}</td>   
 	<td align="center">所属街道</td>
 	<td colspan="2">&nbsp;&nbsp;&nbsp;${rs.hkssjd}</td>   	
 </tr>
 <tr height="45">
 	<td height="47" align="center">户口详细地址</td>
 	<td colspan="5">&nbsp;&nbsp;&nbsp;${rs.hkxxdz}</td>    	   	
 </tr>
 <tr>
 	<td colspan="6" height="47">
 	<center><b>学生档案转往学生户籍所在地区职业介绍所</b></center>
 	</td>
 </tr>
 <tr><td colspan="6">
 <p style="height:65px">
  本人申请理由：<br/>
  &nbsp;&nbsp;&nbsp;${rs.sqly}
  <div align="right">签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div><p/>
 </td></tr>
 <tr>
   <td height="40">就业办审核</td>
   <td colspan="5">&nbsp;</td>
 </tr>
 <tr>
   <td height="40">档案转往地的单位名称全称</td>
   <td colspan="5">&nbsp;</td>
 </tr>
 <tr>
   <td height="40">详细地址</td>
   <td colspan="3">&nbsp;</td>
   <td>邮编</td>
   <td>&nbsp;</td>
 </tr>
 <tr>
 <td height="40">档案转出时间</td>
 <td colspan="5">&nbsp;</td>
 </tr>
</table>
</center>
</html:form>
</body>
<div class='noPrin' align="center">
	<input type='button' class='button2' value='页面设置'
		onclick="WebBrowser.ExecWB(8,1);return false;">
	<input type='button' class='button2' value='打印预览'
		onclick="WebBrowser.ExecWB(7,1);return false;">
	<input type='button' class='button2' value='直接打印'
		onclick="WebBrowser.ExecWB(6,6);return false;">
</div>
</html>
