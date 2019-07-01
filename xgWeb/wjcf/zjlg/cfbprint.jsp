<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title><bean:message key="lable.title" />
	</title>
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
	<style>
	<!--
	.Section1
		{page:Section1;}
	.style1 {
		font-size: 24px;
		font-weight: bold;
	}
	-->
	</style>
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>

	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
</head>
<body>
	<html:form action="/wjcfxmlgwh">
		<p align=center style='text-align:center'>
			<span style='font-size:22.0pt;font-family:黑体'>${xxmc }学生处分（处理）决定告知书</span>
		</p>
		<p align=center style='text-align:center'>
			<span style='font-size:15.0pt;font-family:宋体'>（学生留存）</span>
		</p>
				<p>
			<span style='font-size:16.0pt;
font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;${rs.xm }&nbsp;&nbsp;：</span>
		</p>
		<p align=left style='text-align:left;'>
			<span style='font-size:16.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;因<span
				lang=EN-US><logic:empty name="rs" property="cfyymc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty><logic:notEmpty name="rs" property="cfyymc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.cfyymc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:notEmpty></span>原因，经研究，<br/>根据<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>，<br/>决定给予<span lang=EN-US><logic:empty name="rs" property="cflbmc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty><logic:notEmpty name="rs" property="cflbmc">&nbsp;&nbsp;&nbsp;&nbsp;${rs.cflbmc }&nbsp;&nbsp;&nbsp;&nbsp;</logic:notEmpty>
			</span>处分。</span>
		</p>
		<p align=left style='text-align:left;'>
			<span style='font-size:16.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;特此告知。如有异议，可在收到本告知书的五个工作日内向校学生申诉处理委员会（校长办公室）提出书面申诉。</span>
		</p>
		<p align=right style='text-align:right;'>
			<span style='font-size:15.0pt;font-family:仿宋_GB2312'>党委学工部</span>
		</p>
		<p align=right style='text-align:right;
word-break:break-all'>
			<span style='font-size:15.0pt;font-family:仿宋_GB2312'>年<span
				lang=EN-US>&nbsp; </span>月<span lang=EN-US>&nbsp; </span>日</span>
		</p>
		<p align="center">
			<span  style='font-size:16.0pt;
font-family:仿宋_GB2312'>———————————————————————————</span>
		</p>
		<p align=center style='text-align:center'>
			<span style='font-size:22.0pt;font-family:黑体'>${xxmc }学生处分（处理）决定告知书</span>
		</p>
		<p align=center style='text-align:center'>
			<span style='font-size:15.0pt;font-family:宋体'>（学校留存）</span>
		</p>
		<p>
			<span style='font-size:16.0pt;
font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;${rs.xm }&nbsp;&nbsp;：</span>
		</p>
		<p align=left style='text-align:left;'>
			<span style='font-size:16.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;因<span
				lang=EN-US><logic:empty name="rs" property="cfyymc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty><logic:notEmpty name="rs" property="cfyymc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.cfyymc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:notEmpty></span>原因，经研究，<br/>根据<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>，<br/>决定给予<span lang=EN-US><logic:empty name="rs" property="cflbmc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty><logic:notEmpty name="rs" property="cflbmc">&nbsp;&nbsp;&nbsp;&nbsp;${rs.cflbmc }&nbsp;&nbsp;&nbsp;&nbsp;</logic:notEmpty>
			</span>处分。</span>
		</p>
		<p align=left style='text-align:left;'>
			<span style='font-size:16.0pt;font-family:仿宋_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;特此告知。如有异议，可在收到本告知书的五个工作日内向校学生申诉处理委员会（校长办公室）提出书面申诉。</span>
		</p>
		<p align=right style='text-align:right;'>
			<span style='font-size:15.0pt;font-family:仿宋_GB2312'>本人签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		</p>
		<p align=right style='text-align:right;
word-break:break-all'>
			<span style='font-size:15.0pt;font-family:仿宋_GB2312'>年<span
				lang=EN-US>&nbsp; </span>月<span lang=EN-US>&nbsp; </span>日</span>
		</p>
		
	</html:form>
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
		<%--    </div>--%>
</body>
</html:html>
