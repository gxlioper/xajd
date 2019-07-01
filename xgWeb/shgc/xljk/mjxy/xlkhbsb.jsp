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
<body bgcolor="#FFFFFF" class="Normal" style='text-justify-trim:punctuation' lang=ZH-CN> 
<div class=Section1 style='layout-grid:15.6pt'> 
  <p align=center style='text-align:center'><b><span
style='font-size:15.0pt;font-family:宋体'>闽江学院</span></b><b><span lang=EN-US
style='font-size:15.0pt'>&nbsp; </span></b><b><span style='font-size:15.0pt;
font-family:宋体'>年</span></b><b><span lang=EN-US style='font-size:15.0pt'>&nbsp; </span></b><b><span style='font-size:15.0pt;font-family:宋体'>月学生心理状态报送表</span></b></p> 
   <table width="100%" id="rsT" class="printstyle"> 
    <tr> 
      <td width=64 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>系别</span></p></td> 
      <td width=84 class="Normal">&nbsp; ${rs.xymc }</td> 
      <td width=162 colspan=3 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>学生心理整体状态</span></p></td> 
      <td width=336 colspan=3 class="Normal">&nbsp; ${rs.xlztmc } </td> 
    </tr> 
    <tr> 
      <td width=64 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>具体情况</span></p></td> 
      <td width=582 colspan=7 class="Normal">&nbsp;${rs.jtqk } </td> 
    </tr> 
    <tr> 
      <td width=64 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>报送人</span></p></td> 
      <td width=132 colspan=2 class="Normal">&nbsp;${rs.bsr } </td> 
      <td width=74 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>负责人</span></p></td> 
      <td width=136 colspan=2 class="Normal">&nbsp;${rs.fzr } </td> 
      <td width=84 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>报送时间</span></p></td> 
      <td width=156 class="Normal">&nbsp;${rs.bssj } </td> 
    </tr> 
    <tr height=0> 
      <td width=64 class="Normal"></td> 
      <td width=84 class="Normal"></td> 
      <td width=48 class="Normal"></td> 
      <td width=74 class="Normal"></td> 
      <td width=40 class="Normal"></td> 
      <td width=96 class="Normal"></td> 
      <td width=84 class="Normal"></td> 
      <td width=156 class="Normal"></td> 
    </tr> 
  </table> 
</div> 
</body>
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
</html>
