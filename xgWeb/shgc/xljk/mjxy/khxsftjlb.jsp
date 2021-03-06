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
style='font-size:18.0pt;font-family:宋体;color:black'>心理困惑学生访谈记录</span></b></p> 
  <p><b><span style='font-size:15.0pt;
font-family:楷体_GB2312;color:#535353'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编号 ：${rs.bh }<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></b></p> 
  <div align=center> 
    <table width="100%" id="rsT" class="printstyle"> 
      <tr> 
        <td width=141 class="Normal"> <p align=center style='text-align:center'><span
  style='font-family:宋体'>访谈时间</span></p></td> 
        <td width=184 class="Normal">&nbsp; ${rs.ftsj }</td> 
        <td width=107 class="Normal"> <p align=center style='text-align:center'><span
  style='font-family:宋体'>访谈老师</span></p></td> 
        <td width=204 class="Normal">&nbsp;${rs.ftls } </td> 
      </tr> 
      <tr> 
        <td width=141 class="Normal"> <p align=center style='text-align:center'><span
  style='font-family:宋体'>系</span><span lang=EN-US>(</span><span
  style='font-family:宋体'>院</span><span lang=EN-US>)</span><span
  style='font-family:宋体'>站长跟踪情况</span></p></td> 
        <td width=495 colspan=3 class="Normal">&nbsp; ${rs.gzqk }</td> 
      </tr> 
      <tr> 
        <td width=141 class="Normal"> <p align=center style='text-align:center'><span
  style='font-family:宋体'>访谈主要内容</span></p></td> 
        <td width=495 colspan=3 class="Normal">&nbsp; ${rs.zynr }</td> 
      </tr> 
      <tr> 
        <td width=141 class="Normal"> <p align=center style='text-align:center'><span
  style='font-family:宋体'>访谈结果</span></p></td> 
        <td width=495 colspan=3 class="Normal">&nbsp; ${rs.ftjg }</td> 
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
