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
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>

	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<style>
<!--
 /* Font Definitions */
 @font-face
	{font-family:����;
	panose-1:2 1 6 0 3 1 1 1 1 1;
	mso-font-alt:SimSun;
	mso-font-charset:134;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:3 135135232 16 0 262145 0;}
@font-face
	{font-family:"\@����";
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
	mso-bidi-font-size:12.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:����;
	mso-font-kerning:1.0pt;}
p.MsoAcetate, li.MsoAcetate, div.MsoAcetate
	{mso-style-noshow:yes;
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	mso-pagination:none;
	font-size:9.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:����;
	mso-font-kerning:1.0pt;}
span.GramE
	{mso-style-name:"";
	mso-gram-e:yes;}
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
</head>
<body>
	<html:form action="/wjcfzjcmwh.do">
	<div align="center">
		<h2><bean:write name="xxmc" scope="session"/><bean:message key="lable.xsgzyxpzxy" />�����У�쿴�ڳʱ���</h2>
	</div>
	<table class=MsoTableGrid border=1 cellspacing=0 cellpadding=0 width="98%"
 style='margin-left:5.4pt;border-collapse:collapse;border:none;
 mso-border-alt:solid windowtext .5pt;mso-yfti-tbllook:480;mso-padding-alt:
 0cm 5.4pt 0cm 5.4pt;mso-border-insideh:.5pt solid windowtext;mso-border-insidev:
 .5pt solid windowtext'>
 <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;height:30.35pt'>
  <td width=60 rowspan=2 style='width:45.0pt;border:solid windowtext 1.0pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.35pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>����</span><span lang=EN-US
  style='font-size:14.0pt'><o:p></o:p></span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>��Ϣ</span><span lang=EN-US
  style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
  <td width=84 valign="middle" style='width:63.0pt;border:solid windowtext 1.0pt;
  border-left:none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.35pt'>
  <p class=MsoNormal style='text-indent:7.0pt;mso-char-indent-count:.5'><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>��</span><span style='font-size:14.0pt'>
  </span><span style='font-size:14.0pt;font-family:����;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>��</span><span
  lang=EN-US style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
  <td width=72 valign="middle" style='width:54.0pt;border:solid windowtext 1.0pt;
  border-left:none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.35pt'>
  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'><o:p>&nbsp;${rs.xm }</o:p></span></p>
  </td>
  <td width=84 style='width:63.0pt;border:solid windowtext 1.0pt;border-left:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:30.35pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>�Ա�</span><span lang=EN-US
  style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
  <td width=108 style='width:81.0pt;border:solid windowtext 1.0pt;border-left:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:30.35pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'><o:p>&nbsp;${rs.xb }</o:p></span></p>
  </td>
  <td width=72 style='width:54.0pt;border:solid windowtext 1.0pt;border-left:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:30.35pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>ѧ��</span><span lang=EN-US
  style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
  <td width=72 valign="middle" style='width:54.0pt;border:solid windowtext 1.0pt;
  border-left:none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.35pt'>
  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'><o:p>&nbsp;${rs.xh }</o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:1;height:30.05pt'>
  <td width=84 style='width:63.0pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:
  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.05pt'>
  <p class=MsoNormal align=center style='margin-left:14.0pt;text-align:center;
  text-indent:-14.0pt;mso-char-indent-count:-1.0'><span style='font-size:14.0pt;
  font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>ѧ</span><span style='font-size:14.0pt'> </span><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>Ժ</span><span lang=EN-US
  style='font-size:14.0pt'><o:p>&nbsp;&nbsp;&nbsp;</o:p></span></p>
  </td>
  <td width=72 valign="middle" style='width:54.0pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.05pt'>
  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'><o:p>&nbsp;${rs.xymc }</o:p></span></p>
  </td>
  <td width=84 style='width:63.0pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:
  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.05pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>�༶</span><span lang=EN-US
  style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
  <td width=108 style='width:81.0pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.05pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'><o:p>&nbsp;${rs.bjmc }</o:p></span></p>
  </td>
  <td width=72 style='width:54.0pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:
  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.05pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>�绰</span><span lang=EN-US
  style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
  <td width=72 valign="middle" style='width:54.0pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.05pt'>
  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'><o:p>&nbsp;${rs.sjhm }</o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:2;height:60.85pt'>
  <td width=144 colspan=2 style='width:108.0pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:60.85pt'>
  <p class=MsoNormal align=center style='margin-left:14.0pt;text-align:center;
  text-indent:-14.0pt;mso-char-indent-count:-1.0'><span lang=EN-US
  style='font-size:14.0pt'><span style='mso-spacerun:yes'>&nbsp; </span></span><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>ԭ��<span class=GramE>���ļ�</span></span><span
  lang=EN-US style='font-size:14.0pt'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>���ļ��ţ�</span><span lang=EN-US
  style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
  <td width=408 colspan=5 valign="middle" style='width:306.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:60.85pt'>
  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'><o:p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.cfwh }</o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:3;height:21.35pt'>
  <td width=144 colspan=2 style='width:108.0pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:21.35pt'>
  <p class=MsoNormal align=center style='margin-left:14.0pt;text-align:center;
  text-indent:-14.0pt;mso-char-indent-count:-1.0'><span style='font-size:14.0pt;
  font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>��������</span><span lang=EN-US style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
  <td width=156 colspan=2 valign="middle" align="center" style='width:117.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:21.35pt'>
  <p class=MsoNormal align="center"><span lang=EN-US style='font-size:14.0pt'><o:p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.cfyymc }</o:p></span></p>
  </td>
  <td width=108 valign="middle" style='width:81.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:21.35pt'>
  <p class=MsoNormal style='text-indent:7.0pt;mso-char-indent-count:.5'><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>����ʱ��</span><span lang=EN-US
  style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
  <td width=144 colspan=2 valign="middle" align="center" style='width:108.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:21.35pt'>
  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'><o:p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.cfsj }</o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:4;height:75.55pt'>
  <td width=144 colspan=2 style='width:108.0pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:75.55pt'>
  <p class=MsoNormal><span style='font-size:14.0pt;font-family:����;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>��У�쿴�ڼ�</span><span
  lang=EN-US style='font-size:14.0pt'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp; </span></span><span style='font-size:
  14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>ѧ������<span class=GramE>ǩ��</span></span><span lang=EN-US
  style='font-size:15.0pt'><o:p></o:p></span></p>
  </td>
  <td  colspan=5 valign="middle" style='border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:75.55pt'>
  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'><o:p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xsbx }</o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:5;height:115.3pt'>
  <td width=144 colspan=2 style='width:108.0pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:115.3pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>������Ҫ��ǰ</span><span lang=EN-US
  style='font-size:14.0pt'><span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></span><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>�����У�쿴�ڣ���Ҫ��ǰ�����ע�����ɣ�</span><span
  lang=EN-US style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
  <td  colspan=5 valign="middle" style='border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:115.3pt'>
  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'><o:p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.tqjcly }</o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:6;height:60.25pt'>
  <td width=144 colspan=2 style='width:108.0pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:60.25pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>����<span class=GramE>�����</span></span><span
  lang=EN-US style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
  <td colspan=5 valign=bottom style='border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:60.25pt'>
  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'><o:p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.fdyjdyj }</o:p></span></p>
  <p class=MsoNormal style='margin-right:21.0pt;text-indent:189.0pt;mso-char-indent-count:
  18.0'><span style='mso-bidi-font-size:10.5pt;font-family:����;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>������ǩ�֣�</span><span
  lang=EN-US style='mso-bidi-font-size:10.5pt'><o:p></o:p></span></p>
  <p class=MsoNormal style='margin-right:21.0pt;text-indent:183.75pt;
  mso-char-indent-count:17.5'><span style='mso-bidi-font-size:10.5pt;
  font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>��</span><span lang=EN-US style='mso-bidi-font-size:10.5pt'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span><span
  style='mso-bidi-font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>��</span><span lang=EN-US
  style='mso-bidi-font-size:10.5pt'><span style='mso-spacerun:yes'>&nbsp;&nbsp;
  </span></span><span style='mso-bidi-font-size:10.5pt;font-family:����;
  mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>��</span><span
  lang=EN-US style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:8;height:77.95pt'>
  <td width=144 colspan=2 style='width:108.0pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:77.95pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'><bean:message key="lable.xsgzyxpzxy" />���</span><span lang=EN-US
  style='font-size:14.0pt'><o:p></o:p></span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>������£�</span><span lang=EN-US
  style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
  <td colspan=5 valign=bottom style='border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:77.95pt'>
  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'><o:p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }</o:p></span></p>
  <p class=MsoNormal style='margin-center:26.0pt;text-indent:183.75pt;
  mso-char-indent-count:17.5;word-break:break-all'><span style='mso-bidi-font-size:
  10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'><bean:message key="lable.xsgzyxpzxy" />ѧ�������쵼ǩ�֣�</span><span lang=EN-US style='mso-bidi-font-size:
  10.5pt'><o:p>&nbsp;</o:p></span></p>
  <p class=MsoNormal style='margin-right:21.0pt;text-indent:189.0pt;mso-char-indent-count:
  18.0'><span style='mso-bidi-font-size:10.5pt;font-family:����;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>��</span><span
  lang=EN-US style='mso-bidi-font-size:10.5pt'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span><span
  style='mso-bidi-font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>��</span><span lang=EN-US
  style='mso-bidi-font-size:10.5pt'><span style='mso-spacerun:yes'>&nbsp;&nbsp;
  </span></span><span style='mso-bidi-font-size:10.5pt;font-family:����;
  mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>��</span><span
  lang=EN-US style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:8;height:77.95pt'>
  <td width=144 colspan=2 style='width:108.0pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:77.95pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>ѧ�������</span><span lang=EN-US
  style='font-size:14.0pt'><o:p></o:p></span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>������£�</span><span lang=EN-US
  style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
  <td colspan=5 valign=bottom style='border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:77.95pt'>
  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'><o:p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }</o:p></span></p>
  <p class=MsoNormal style='margin-right:21.0pt;text-indent:183.75pt;
  mso-char-indent-count:17.5;word-break:break-all'><span style='mso-bidi-font-size:
  10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>ѧ�����쵼ǩ�֣�</span><span lang=EN-US style='mso-bidi-font-size:
  10.5pt'><o:p></o:p></span></p>
  <p class=MsoNormal style='margin-right:21.0pt;text-indent:189.0pt;mso-char-indent-count:
  18.0'><span style='mso-bidi-font-size:10.5pt;font-family:����;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>��</span><span
  lang=EN-US style='mso-bidi-font-size:10.5pt'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span><span
  style='mso-bidi-font-size:10.5pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>��</span><span lang=EN-US
  style='mso-bidi-font-size:10.5pt'><span style='mso-spacerun:yes'>&nbsp;&nbsp;
  </span></span><span style='mso-bidi-font-size:10.5pt;font-family:����;
  mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman"'>��</span><span
  lang=EN-US style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:9;mso-yfti-lastrow:yes;height:43.7pt'>
  <td width=144 colspan=2 valign=top style='width:108.0pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:43.7pt'>
  <br/>
  <p class=MsoNormal style='text-indent:35.0pt;mso-char-indent-count:2.5'><span
  style='font-size:14.0pt;font-family:����;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>��</span><span style='font-size:14.0pt'>
  </span><span style='font-size:14.0pt;font-family:����;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>ע</span><span
  lang=EN-US style='font-size:14.0pt'><o:p></o:p></span></p>
  </td>
  <td width=408 colspan=5 valign=top style='width:306.0pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:43.7pt'>
  <p class=MsoNormal><span lang=EN-US style='font-size:14.0pt'><o:p>&nbsp;</o:p></span></p>
  </td>
 </tr>
</table>

<p class=MsoNormal><span style='font-family:����;mso-ascii-font-family:"Times New Roman";
mso-hansi-font-family:"Times New Roman"'>&nbsp;&nbsp;&nbsp;&nbsp;ע��</span><span lang=EN-US>1</span><span
style='font-family:����;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
"Times New Roman"'>�����������鸽��������ǩ����</span></p>

<p class=MsoNormal style='text-indent:21.0pt;mso-char-indent-count:2.0'><span
lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2</span><span style='font-family:����;mso-ascii-font-family:"Times New Roman";
mso-hansi-font-family:"Times New Roman"'>���˱�<bean:message key="lable.xsgzyxpzxy" />��ͬ����쿴<span class=GramE>���ļ�</span>һ���ϱ�ѧ������������ñ�<bean:message key="lable.xsgzyxpzxy" />�Լ����档</span></p>

	</html:form>
	<br />
	<div align="center" class='noPrin'>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			ҳ������
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			��ӡԤ��
		</button>
		<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			ֱ�Ӵ�ӡ
		</button>
	</div>
</body>
</html:html>
