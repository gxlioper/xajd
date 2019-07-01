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
	<style>
<!--
 /* Font Definitions */
 @font-face
	{font-family:宋体;
	panose-1:2 1 6 0 3 1 1 1 1 1;
	mso-font-alt:SimSun;
	mso-font-charset:134;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:3 135135232 16 0 262145 0;}
@font-face
	{font-family:黑体;
	panose-1:2 1 6 0 3 1 1 1 1 1;
	mso-font-alt:SimHei;
	mso-font-charset:134;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:1 135135232 16 0 262144 0;}
@font-face
	{font-family:仿宋_GB2312;
	panose-1:2 1 6 9 3 1 1 1 1 1;
	mso-font-charset:134;
	mso-generic-font-family:modern;
	mso-font-pitch:fixed;
	mso-font-signature:1 135135232 16 0 262144 0;}
@font-face
	{font-family:"\@仿宋_GB2312";
	panose-1:2 1 6 9 3 1 1 1 1 1;
	mso-font-charset:134;
	mso-generic-font-family:modern;
	mso-font-pitch:fixed;
	mso-font-signature:1 135135232 16 0 262144 0;}
@font-face
	{font-family:"\@黑体";
	panose-1:2 1 6 0 3 1 1 1 1 1;
	mso-font-charset:134;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:1 135135232 16 0 262144 0;}
@font-face
	{font-family:"\@宋体";
	panose-1:2 1 6 0 3 1 1 1 1 1;
	mso-font-charset:134;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:3 135135232 16 0 262145 0;}
 /* Style Definitions */
 p.MsoNormal, li.MsoNormal, div.MsoNormal
	{mso-style-parent:"";
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	mso-pagination:none;
	font-size:10.5pt;
	mso-bidi-font-size:10.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:宋体;
	mso-font-kerning:1.0pt;}
p.MsoDate, li.MsoDate, div.MsoDate
	{mso-style-next:正文;
	margin:0cm;
	margin-bottom:.0001pt;
	mso-para-margin-top:0cm;
	mso-para-margin-right:0cm;
	mso-para-margin-bottom:0cm;
	mso-para-margin-left:25.0gd;
	mso-para-margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	mso-pagination:none;
	font-size:16.0pt;
	mso-bidi-font-size:10.0pt;
	font-family:黑体;
	mso-hansi-font-family:"Times New Roman";
	mso-bidi-font-family:"Times New Roman";
	mso-font-kerning:1.0pt;}
 /* Page Definitions */
 @page
	{mso-page-border-surround-header:no;
	mso-page-border-surround-footer:no;}
@page Section1
	{size:595.3pt 841.9pt;
	margin:72.0pt 90.0pt 72.0pt 90.0pt;
	mso-header-margin:42.55pt;
	mso-footer-margin:49.6pt;
	mso-paper-source:0;
	layout-grid:15.6pt;}
div.Section1
	{page:Section1;}
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
	<div class=Section1 style='layout-grid:15.6pt'>

		<p class=MsoNormal align=center style='text-align:center'>
			<b style='mso-bidi-font-weight:
normal'><span
				style='font-size:18.0pt;mso-bidi-font-size:10.0pt;font-family:
仿宋_GB2312'>宁波城市职业技术<bean:message key="lable.xsgzyxpzxy" />学生违纪拟处分通知<span
					lang=EN-US><o:p></o:p>
				</span>
			</span>
			</b>
		</p>

		<p class=MsoDate
			style='margin-left:262.5pt;text-indent:42.0pt;mso-char-indent-count:
3.5'>
			<span style='font-size:12.0pt;mso-bidi-font-size:10.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.year
				}&nbsp;年<span lang=EN-US><span style='mso-spacerun:yes'>&nbsp;${rs.mon
						}&nbsp;</span>
			</span>月<span style='mso-spacerun:yes'>&nbsp;${rs.date }&nbsp;</span>日<span
				lang=EN-US><o:p></o:p>
			</span>
			</span>
		</p>

		<table  class="printstyle" width="100%">
			<tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;height:119.35pt'>
				<th width=60
					style=''>
					<p class=MsoNormal align=center style='text-align:center'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;
  font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>违</span>
						</b><b style='mso-bidi-font-weight:normal'><span lang=EN-US
							style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
						</b>
					</p>
					<p class=MsoNormal align=center style='text-align:center'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;
  font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>纪</span>
						</b><b style='mso-bidi-font-weight:normal'><span lang=EN-US
							style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
						</b>
					</p>
					<p class=MsoNormal align=center style='text-align:center'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;
  font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>事</span>
						</b><b style='mso-bidi-font-weight:normal'><span lang=EN-US
							style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
						</b>
					</p>
					<p class=MsoNormal align=center style='text-align:center'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;
  font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>实</span>
						</b><span lang=EN-US style='mso-bidi-font-size:
  10.5pt'><o:p></o:p>
						</span>
					</p>
				</th>
				<td width=505
					style='width:378.65pt;border:solid windowtext 1.0pt;border-left:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:119.35pt'>
					<b><u>&nbsp;${rs.xm }</u>同学（学号：<u>&nbsp;${rs.xh } </u><bean:message key="lable.xsgzyxpzxy" /><u>&nbsp;${rs.xymc }</u> 专业<u>&nbsp;${rs.zymc }</u>
						班级<u>&nbsp;${rs.bjmc }</u>）</b>
					<p align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow:1;page-break-inside:avoid;height:64.75pt'>
				<th width=60
					style='width:45.1pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:64.75pt'>
					<p class=MsoNormal align=center style='text-align:center'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;
  font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>阅</span>
						</b><b style='mso-bidi-font-weight:normal'><span lang=EN-US
							style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
						</b>
					</p>
					<p class=MsoNormal align=center style='text-align:center'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;
  font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>件</span>
						</b><b style='mso-bidi-font-weight:normal'><span lang=EN-US
							style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
						</b>
					</p>
					<p class=MsoNormal align=center style='text-align:center'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;
  font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>意</span>
						</b><b style='mso-bidi-font-weight:normal'><span lang=EN-US
							style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
						</b>
					</p>
					<p class=MsoNormal align=center style='text-align:center'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;
  font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>见</span>
						</b><span lang=EN-US style='mso-bidi-font-size:
  10.5pt'><o:p></o:p>
						</span>
					</p>
				</th>
				<td width=505
					style='width:378.65pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:64.75pt'>
					<p class=MsoNormal
						style='text-indent:21.0pt;mso-char-indent-count:2.0'>
						<span
							style='mso-bidi-font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>本人（代理人）是否对违纪事由及学校拟作出的违纪行为处分作出申辩？</span><span
							lang=EN-US style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
					</p>
					<p class=MsoNormal
						style='margin-top:7.8pt;margin-right:0cm;margin-bottom:
  7.8pt;margin-left:0cm;mso-para-margin-top:.5gd;mso-para-margin-right:0cm;
  mso-para-margin-bottom:.5gd;mso-para-margin-left:0cm;text-indent:52.5pt;
  mso-char-indent-count:5.0'>
						<span lang=EN-US style='mso-bidi-font-size:10.5pt'>(<span
							style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp; </span>)</span><span
							style='mso-bidi-font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>申辩</span><span
							lang=EN-US style='mso-bidi-font-size:10.5pt'><span
							style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>(<span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp; </span>)</span><span
							style='mso-bidi-font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>不申辩</span><span
							lang=EN-US style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
					</p>
					<p class=MsoNormal
						style='text-indent:183.75pt;mso-char-indent-count:17.5'>
						<span
							style='mso-bidi-font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>签名：</span><span
							lang=EN-US style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
					</p>
					<p class=MsoNormal
						style='text-indent:237.0pt;mso-char-indent-count:22.57'>
						<span
							style='mso-bidi-font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>年</span><span
							lang=EN-US style='mso-bidi-font-size:10.5pt'><span
							style='mso-spacerun:yes'>&nbsp;&nbsp; </span>
						</span><span
							style='mso-bidi-font-size:10.5pt;font-family:宋体;
  mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>月</span><span
							lang=EN-US style='mso-bidi-font-size:10.5pt'><span
							style='mso-spacerun:yes'>&nbsp;&nbsp; </span>
						</span><span
							style='mso-bidi-font-size:
  10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>日</span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow:2;height:121.1pt'>
				<th width=60
					style='width:45.1pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:121.1pt'>
					<p class=MsoNormal align=center
						style='text-align:center;line-height:19.0pt;
  mso-line-height-rule:exactly'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>陈述</span>
						</b><b style='mso-bidi-font-weight:
  normal'><span lang=EN-US
							style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
						</b>
					</p>
					<p class=MsoNormal align=center
						style='text-align:center;line-height:19.0pt;
  mso-line-height-rule:exactly'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>和</span>
						</b><b style='mso-bidi-font-weight:
  normal'><span lang=EN-US
							style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
						</b>
					</p>
					<p class=MsoNormal align=center
						style='text-align:center;line-height:19.0pt;
  mso-line-height-rule:exactly'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>申辩</span>
						</b><b style='mso-bidi-font-weight:
  normal'><span lang=EN-US
							style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
						</b>
					</p>
					<p class=MsoNormal align=center
						style='text-align:center;line-height:19.0pt;
  mso-line-height-rule:exactly'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>事由</span>
						</b><span lang=EN-US style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
					</p>
				</th>
				<td width=505
					style='width:378.65pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:121.1pt'>
					<p class=MsoNormal>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
					<p class=MsoNormal align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.sbsy }
					</p>
					<p class=MsoNormal
						style='text-indent:210.0pt;mso-char-indent-count:20.0'>
						<span
							style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>签名：</span>
					</p>
					<p class=MsoNormal
						style='text-indent:231.0pt;mso-char-indent-count:22.0'>
						<span
							style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>年</span><span
							lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;
						</span>
						</span><span
							style='font-family:
  宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>月</span><span
							lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;
						</span>
						</span><span
							style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>日</span>
					</p>
					<p class=MsoNormal>
						<span
							style='font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>&nbsp;&nbsp;&nbsp;（不申辩的同学无须填写此栏，需申辩的可另附材料）</span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow:3;height:99.2pt'>
				<th width=60
					style='width:45.1pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:99.2pt'>
					<p class=MsoNormal align=center
						style='text-align:center;line-height:19.0pt;
  mso-line-height-rule:exactly'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>学生所在<bean:message key="lable.xsgzyxpzxy" />意见</span>
						</b><b style='mso-bidi-font-weight:normal'><span lang=EN-US
							style='mso-bidi-font-size:
  10.5pt'><o:p></o:p>
						</span>
						</b>
					</p>
				</th>
				<td width=505
					style='width:378.65pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:99.2pt'>
					<p class=MsoNormal>
						<span lang=EN-US><o:p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyclyj }</o:p>
						</span>
					</p>
				</td>
			</tr>
			<tr style='mso-yfti-irow:4;mso-yfti-lastrow:yes;height:121.9pt'>
				<th width=60
					style='width:45.1pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:121.9pt'>
					<p class=MsoNormal align=center
						style='text-align:center;line-height:19.0pt;
  mso-line-height-rule:exactly'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>处理</span>
						</b><b style='mso-bidi-font-weight:
  normal'><span lang=EN-US
							style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
						</b>
					</p>
					<p class=MsoNormal align=center
						style='text-align:center;line-height:19.0pt;
  mso-line-height-rule:exactly'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>部门</span>
						</b><b style='mso-bidi-font-weight:
  normal'><span lang=EN-US
							style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
						</b>
					</p>
					<p class=MsoNormal align=center
						style='text-align:center;line-height:19.0pt;
  mso-line-height-rule:exactly'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>复核</span>
						</b><b style='mso-bidi-font-weight:
  normal'><span lang=EN-US
							style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
						</b>
					</p>
					<p class=MsoNormal align=center
						style='text-align:center;line-height:19.0pt;
  mso-line-height-rule:exactly'>
						<b style='mso-bidi-font-weight:normal'><span
							style='mso-bidi-font-size:10.5pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>意见</span>
						</b><b style='mso-bidi-font-weight:
  normal'><span lang=EN-US
							style='mso-bidi-font-size:10.5pt'><o:p></o:p>
						</span>
						</b>
					</p>
				</th>
				<td width=505
					style='width:378.65pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:121.9pt'>
					<p class=MsoNormal style='text-indent:200.25pt'>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
					<p class=MsoNormal style='text-indent:200.25pt'>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
					<p class=MsoNormal>
						<span lang=EN-US><o:p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxclyj }</o:p>
						</span>
					</p>
					<p class=MsoNormal style='text-indent:200.25pt'>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
					<p class=MsoNormal style='text-indent:200.25pt'>
						<span lang=EN-US><o:p>&nbsp;</o:p>
						</span>
					</p>
					<p class=MsoNormal style='text-indent:200.25pt'>
						<span
							style='font-family:
  宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>负责人签字：</span><span
							lang=EN-US><span style='mso-spacerun:yes'>&nbsp; </span>
						</span>
					</p>
					<p class=MsoNormal
						style='text-indent:231.0pt;mso-char-indent-count:22.0'>
						<span
							style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>年</span><span
							lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;
						</span>
						</span><span
							style='font-family:
  宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>月</span><span
							lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;
						</span>
						</span><span
							style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>日</span><span
							lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
						</span>
					</p>
				</td>
			</tr>
		</table>

		<p class=MsoNormal>
			<span
				style='font-family:宋体;mso-ascii-font-family:"Times New Roman";
mso-hansi-font-family:"Times New Roman"'>收到本表后在</span><span
				lang=EN-US>3</span><span
				style='font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
"Times New Roman"'>个工作日内填妥送达学生处</span>
		</p>

	</div>

	<br />
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
</body>
</html:html>
