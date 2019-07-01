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

		<html:form action="/stu_info_add" method="post">
		<p align="center"><strong style="font-size:24px;font-family:黑体"><font><B>${xxmc }三好学生登记表</B></font></strong></p>
		<p align="right">
			<strong>
				<font size="2">（学年：${rs.pjxn } ）
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</font>
			</strong></p>
		<table width="100%" id="rsT" class="printstyle">
			<tr>
			    <th width="12%" colspan="3" height="80px"><p align="center">姓 名 </p></th>
			    <th width="12%" colspan="2" valign="center" ><p align="center">${rs.xm }&nbsp; </p></th>
			    <th width="12%" ><p align="center">性别 </p></th>
			    <th width="12%" valign="center" ><p align="center">${rs.xb }&nbsp; </p></th>
			    <th width="12%" ><p align="center">年龄 </p></th>
			    <th width="12%" colspan="2" valign="center" ><p align="center">${rs.年龄 }&nbsp; </p></th>
			    <th width="12%" colspan="2" ><p align="center">政治</br>面貌 </p></th>
			    <th width="" valign="center" ><p align="center">${rs.zzmmmc }&nbsp;</p></th>
			  </tr>
			  <tr>
			    <th width="20%" height="30px" colspan="4" ><p align="center">系、级、班 </p></th>
			    <th width="" colspan="5" valign="center" ><p>${rs.xymc }&nbsp;${rs.nj }&nbsp;${rs.bjmc } </p></th>
			    <th width="" colspan="2" ><p align="center">学号 </p></th>
			    <th width="" colspan="2" valign="center" ><p>${rs.xh }&nbsp; </p></th>
			  </tr>
			  <tr>
			    <th width="5%" height="220px" >
			    	</br>
			        <p align="center">主 </p>
			        <p align="center">要 </p>
			        <p align="center">事 </p>
			        <p align="center">迹 </p>
			    </th>
			    	<th width="" colspan="12" valign="top" align="left"><p>${rs.主要事迹 }&nbsp; </p></th>
			  </tr>
			  <tr>
			    <th width="8%" colspan="2" >
			    	<p align="center">班 级</br>意 见</p>
				</th>
			    <th width="" colspan="11" valign="top" >
			    	<p valign="center" align="left">${rs.shyj1 }&nbsp;</p>
			        <p align="right">签字（章） &nbsp;&nbsp;</p>
			        <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp; </p></th>
			  </tr>
			  <tr>
			    <th width="" colspan="2" >
			    	<p align="center">年 级</br>意 见</p>
			    </th>
			    <th width="" colspan="11" valign="top" ><p>&nbsp; </p>
			        <p align="right">签字（章） &nbsp;&nbsp;</p>
			        <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp; </p></th>
			  </tr>
			  <tr>
			    <th width="" colspan="2" >
			    	<p align="center">系 部</br>意 见</p>
			    </th>
			    <th width="" colspan="11" valign="top">
			    	<p valign="center" align="left">${rs.shyj2 }&nbsp; </p>
			        <p align="right">签字（章） &nbsp;&nbsp;</p>
			        <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp; </p></th>
			  </tr>
			  <tr>
			    <th width="" colspan="2" >
			    	<p align="top">学 院</br>意 见</p>
			    </th>
			    <th width="" colspan="11" valign="center" >
			    	<p valign="center" align="left">${rs.shyj3 }&nbsp;</p>
			        <p align="right">签字（章） &nbsp;&nbsp;</p>
			        <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp; </p></th>
			  </tr>
			  <tr>
			    <th width="" colspan="2" height="100px">
			    	<p align="center">备 注</p>
			    </th>
			    <th width="" colspan="11" valign="top" >
			        <p align="right">签字（章） &nbsp;&nbsp;</p>
			        <p align="right">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp; </p></th>
			  </tr>
		</table>
		<span>
			<strong>
				<font size="2">此表由组织填写，一式两份。
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				学生处制</font> 
			</strong>
		</span>
		<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				直接打印
			</button>
		</div>
		</html:form>
	</body>
</html>
