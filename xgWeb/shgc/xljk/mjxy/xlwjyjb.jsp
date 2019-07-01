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
<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN> 
<div class=Section1 style='layout-grid:15.6pt'> 
  <p align=center style='
text-align:center'><span
style='font-size:14.0pt;font-family:黑体;'>闽江学院学生心理危机预警登记表</span></p> 
  <p align=center style='
text-align:center'><span
lang=EN-US style='font-size:14.0pt;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span lang=EN-US style='font-size:12.0pt;'>&nbsp;</span><span
style='font-size:12.0pt;font-family:仿宋_GB2312;
'>编号：</span></p> 
  <div align=center> 
    <table width="100%" id="rsT" class="printstyle"> 
      <tr> 
        <td width=79 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:仿宋_GB2312'>姓 名</span></p></td> 
        <td width=108 class="Normal">&nbsp;${rs.xm } </td> 
        <td width=96 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:仿宋_GB2312'>性 别</span></p></td> 
        <td width=84 class="Normal">&nbsp; ${rs.xb }</td> 
        <td width=96 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:仿宋_GB2312'>出生年月</span></p></td> 
        <td width=144 class="Normal">&nbsp; ${rs.csrq }</td> 
      </tr> 
      <tr> 
        <td width=79 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:仿宋_GB2312'>系别</span></p></td> 
        <td width=108 class="Normal">&nbsp;${rs.xymc } </td> 
        <td width=96 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:仿宋_GB2312'>年级专业</span></p></td> 
        <td width=84 class="Normal">&nbsp; ${rs.nj }  ${rs.zymc }</td> 
        <td width=96 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:仿宋_GB2312'>联系电话</span></p></td> 
        <td width=144 class="Normal">&nbsp;${rs.lxdh } </td> 
      </tr> 
      <tr> 
        <td width=79 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:仿宋_GB2312'>家 庭</span></p> 
          <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:仿宋_GB2312'>住 址</span></p></td> 
        <td width=288 colspan=4 class="Normal">&nbsp; ${rs.jtdz }</td> 
        <td width=96 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:仿宋_GB2312'>家庭联</span></p> 
          <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:仿宋_GB2312'>系电话</span></p></td> 
        <td width=144 class="Normal">&nbsp; ${rs.lxdh1 }</td> 
      </tr> 
      <tr> 
        <td width=79 class="Normal" align="center">基<br/>本<br/>情<br/>况</span></span></p></td> 
        <td width=528 colspan=7 class="Normal"> <p><span
  lang=EN-US style='font-size:12.0pt;font-family:
  仿宋_GB2312'>&nbsp;${rs.jbqk }</span><span
  style='font-size:12.0pt;font-family:仿宋_GB2312'><br><br><br>年<span
  lang=EN-US>&nbsp;&nbsp; </span>月<span
  lang=EN-US>&nbsp; </span>日</span></p></td> 
      </tr> 
      <tr> 
        <td width=79 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:仿宋_GB2312'>辅导员</span></p> 
          <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:仿宋_GB2312'>签<span
  lang=EN-US>&nbsp; </span>名</span></p></td> 
        <td width=168 colspan=2 class="Normal">&nbsp; </td> 
        <td width=156 colspan=3 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:仿宋_GB2312;'>系（院）</span></p> 
          <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:仿宋_GB2312'>负责人签名</span></p></td> 
        <td width=204 colspan=2 class="Normal">&nbsp; </td> 
      </tr> 
      <tr height=0> 
        <td width=79 class="Normal"></td> 
        <td width=108 class="Normal"></td> 
        <td width=60 class="Normal"></td> 
        <td width=36 class="Normal"></td> 
        <td width=84 class="Normal"></td> 
        <td width=36 class="Normal"></td> 
        <td width=60 class="Normal"></td> 
        <td width=144 class="Normal"></td> 
      </tr> 
    </table> 
  </div> 
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

