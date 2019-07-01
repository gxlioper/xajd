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

	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<title>成都体育学院处理学生登记表</title>
<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Author>user</o:Author>
  <o:LastAuthor>福建163软件园</o:LastAuthor>
  <o:Revision>2</o:Revision>
  <o:TotalTime>40</o:TotalTime>
  <o:LastPrinted>2005-06-01T06:45:00Z</o:LastPrinted>
  <o:Created>2011-10-18T05:41:00Z</o:Created>
  <o:LastSaved>2011-10-18T05:41:00Z</o:LastSaved>
  <o:Pages>1</o:Pages>
  <o:Words>37</o:Words>
  <o:Characters>216</o:Characters>
  <o:Company>Microsoft China</o:Company>
  <o:Lines>1</o:Lines>
  <o:Paragraphs>1</o:Paragraphs>
  <o:CharactersWithSpaces>252</o:CharactersWithSpaces>
  <o:Version>11.5606</o:Version>
 </o:DocumentProperties>
</xml><![endif]--><!--[if gte mso 9]><xml>
 <w:WordDocument>
  <w:SpellingState>Clean</w:SpellingState>
  <w:PunctuationKerning/>
  <w:DrawingGridHorizontalSpacing>5.25 磅</w:DrawingGridHorizontalSpacing>
  <w:DrawingGridVerticalSpacing>7.15 磅</w:DrawingGridVerticalSpacing>
  <w:DisplayHorizontalDrawingGridEvery>0</w:DisplayHorizontalDrawingGridEvery>
  <w:DisplayVerticalDrawingGridEvery>2</w:DisplayVerticalDrawingGridEvery>
  <w:ValidateAgainstSchemas/>
  <w:SaveIfXMLInvalid>false</w:SaveIfXMLInvalid>
  <w:IgnoreMixedContent>false</w:IgnoreMixedContent>
  <w:AlwaysShowPlaceholderText>false</w:AlwaysShowPlaceholderText>
  <w:Compatibility>
   <w:SpaceForUL/>
   <w:BalanceSingleByteDoubleByteWidth/>
   <w:DoNotLeaveBackslashAlone/>
   <w:ULTrailSpace/>
   <w:DoNotExpandShiftReturn/>
   <w:AdjustLineHeightInTable/>
   <w:SelectEntireFieldWithStartOrEnd/>
   <w:UseWord2002TableStyleRules/>
   <w:UseFELayout/>
  </w:Compatibility>
  <w:BrowserLevel>MicrosoftInternetExplorer4</w:BrowserLevel>
 </w:WordDocument>
</xml><![endif]--><!--[if gte mso 9]><xml>
 <w:LatentStyles DefLockedState="false" LatentStyleCount="156">
 </w:LatentStyles>
</xml><![endif]-->
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
	mso-bidi-font-size:12.0pt;
	font-family:"Times New Roman";
	mso-fareast-font-family:宋体;
	mso-font-kerning:1.0pt;}
 /* Page Definitions */
 @page
	{mso-page-border-surround-header:no;
	mso-page-border-surround-footer:no;}
@page Section1
	{size:595.25pt 841.85pt;
	margin:3.0cm 70.9pt 70.9pt 70.9pt;
	mso-header-margin:1.0cm;
	mso-footer-margin:1.0cm;
	mso-paper-source:0;}
div.Section1
	{page:Section1;}
-->
</style>
<!--[if gte mso 10]>
<style>
 /* Style Definitions */
 table.MsoNormalTable
	{mso-style-name:普通表格;
	mso-tstyle-rowband-size:0;
	mso-tstyle-colband-size:0;
	mso-style-noshow:yes;
	mso-style-parent:"";
	mso-padding-alt:0cm 5.4pt 0cm 5.4pt;
	mso-para-margin:0cm;
	mso-para-margin-bottom:.0001pt;
	mso-pagination:widow-orphan;
	font-size:10.0pt;
	font-family:"Times New Roman";
	mso-ansi-language:#0400;
	mso-fareast-language:#0400;
	mso-bidi-language:#0400;}
</style>
<![endif]--><!--[if gte mso 9]><xml>
 <o:shapedefaults v:ext="edit" spidmax="2050"/>
</xml><![endif]--><!--[if gte mso 9]><xml>
 <o:shapelayout v:ext="edit">
  <o:idmap v:ext="edit" data="1"/>
 </o:shapelayout></xml><![endif]-->
  </head>
<body lang=ZH-CN style='tab-interval:21.0pt;text-justify-trim:punctuation'>
    <html:form action="/wjcfzjcmwh.do">
    
    	
<div class=Section1>

<p class=MsoNormal align=center style='text-align:center'><span
style='font-size:16.0pt;mso-bidi-font-size:12.0pt;font-family:黑体;mso-ascii-font-family:
"Times New Roman"'>${xxmc}学生纪律处理登记表</span><span lang=EN-US style='font-size:
16.0pt;mso-bidi-font-size:12.0pt;mso-fareast-font-family:黑体'><o:p></o:p></span></p><br/>

<p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
style='font-size:12.0pt;mso-bidi-font-size:12.0pt;mso-fareast-font-family:黑体'>&nbsp;${rs.xymc }</span>
<span
style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
"Times New Roman";mso-hansi-font-family:"Times New Roman"'>系</span><span
lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'>(</span><span
style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
"Times New Roman";mso-hansi-font-family:"Times New Roman"'>部</span><span
lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'>)<span
style='mso-spacerun:yes'>&nbsp;</span></span><span
style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
"Times New Roman";mso-hansi-font-family:"Times New Roman"'>&nbsp;
	${rs.nj }<logic:empty name="rs" property="nj">&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty>年级</span><span
lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><span
style='mso-spacerun:yes'>&nbsp;</span></span><span
style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
"Times New Roman";mso-hansi-font-family:"Times New Roman"'>&nbsp;${rs.bjmc }<logic:empty name="rs" property="nj">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty>班</span><span
lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><span
style='mso-spacerun:yes'>&nbsp;</span></span><span
style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
"Times New Roman";mso-hansi-font-family:"Times New Roman"'>&nbsp;&nbsp;填表时间：</span><span
lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><span
style='mso-spacerun:yes'></span></span><span
style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
"Times New Roman";mso-hansi-font-family:"Times New Roman"'>${time }</span><o:p></o:p></span></p><br/>

<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0
 style='border-collapse:collapse;border:none;mso-border-alt:double windowtext 1.5pt;
 mso-padding-alt:0cm 5.4pt 0cm 5.4pt;mso-border-insideh:.5pt solid windowtext;
 mso-border-insidev:.5pt solid windowtext'>
 <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;page-break-inside:avoid'>
  <td width=49 height="60px" style='width:36.9pt;border-top:double 1.5pt;border-left:double 1.5pt;
  border-bottom:solid 1.0pt;border-right:solid 1.0pt;border-color:windowtext;
  mso-border-top-alt:double 1.5pt;mso-border-left-alt:double 1.5pt;mso-border-bottom-alt:
  solid .5pt;mso-border-right-alt:solid .5pt;mso-border-color-alt:windowtext;
  padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>姓名</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=136 colspan=2 style='width:102.3pt;border-top:double windowtext 1.5pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  mso-border-top-alt:double windowtext 1.5pt;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p>${rs.xm }</o:p></span></p>
  </td>
  <td width=62 style='width:46.4pt;border-top:double windowtext 1.5pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  mso-border-top-alt:double windowtext 1.5pt;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>性别</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=62 style='width:46.4pt;border-top:double windowtext 1.5pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  mso-border-top-alt:double windowtext 1.5pt;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p>${rs.xb }</o:p></span></p>
  </td>
  <td width=62 style='width:46.45pt;border-top:double windowtext 1.5pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  mso-border-top-alt:double windowtext 1.5pt;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>出生</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>年月</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=133 style='width:99.7pt;border-top:double windowtext 1.5pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  mso-border-top-alt:double windowtext 1.5pt;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p>${rs.csrq }</o:p></span></p>
  </td>
  <td width=35 style='width:26.25pt;border-top:double windowtext 1.5pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  mso-border-top-alt:double windowtext 1.5pt;padding:0cm 5.4pt 0cm 5.4pt' valign="center">
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>民族</span></p>
  </td>
  <td width=80 valign=center style='width:59.85pt;border-top:double windowtext 1.5pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:double windowtext 1.5pt;
  mso-border-left-alt:solid windowtext .5pt;mso-border-top-alt:double 1.5pt;
  mso-border-left-alt:solid .5pt;mso-border-bottom-alt:solid .5pt;mso-border-right-alt:
  double 1.5pt;mso-border-color-alt:windowtext;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal><span lang=EN-US><o:p>${rs.mzmc }</o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:1;page-break-inside:avoid'>
  <td width=49 height="60px" style='width:36.9pt;border-top:none;border-left:double windowtext 1.5pt;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  mso-border-left-alt:double windowtext 1.5pt;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>学号</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=136 colspan=2 style='width:102.3pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p>${rs.xh }</o:p></span></p>
  </td>
  <td width=62 style='width:46.4pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:
  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>政治</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>面貌</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=62 style='width:46.4pt;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:
  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt' valign="center">
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt'>${rs.zzmmmc }</span></p>
  </td>
  <td width=62 style='width:46.45pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>家庭</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>地址</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=248 colspan=3 style='width:185.8pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:double windowtext 1.5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;mso-border-right-alt:double windowtext 1.5pt;
  padding:0cm 5.4pt 0cm 5.4pt'>
  <p class=MsoNormal><span lang=EN-US><o:p>${rs.jtdz }</o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:2;page-break-inside:avoid;height:193.7pt'>
  <td width=49 heigth="330px" style='width:36.9pt;border-top:none;border-left:double windowtext 1.5pt;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  mso-border-left-alt:double windowtext 1.5pt;padding:0cm 5.4pt 0cm 5.4pt;
 '>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>主要<br/>事实</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=570 valign="top" colspan=8 style='width:427.35pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:double windowtext 1.5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;mso-border-right-alt:double windowtext 1.5pt;
  padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal><span lang=EN-US><o:p>${rs.bz }</o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:3;page-break-inside:avoid;height:99.35pt'>
  <td width=49 rowspan=3 style='width:36.9pt;border-top:none;border-left:double windowtext 1.5pt;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  mso-border-left-alt:double windowtext 1.5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:99.35pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>处理<br/>意见</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=91 height="140px" style='width:68.25pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>系</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'>(</span><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>部</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'>)<o:p></o:p></span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>意见</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=479 colspan=7 valign=bottom style='width:359.1pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:double windowtext 1.5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;mso-border-right-alt:double windowtext 1.5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:99.35pt'>
  <p class=MsoNormal align=right style='text-align:right;word-break:break-all'><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;</span><o:p></o:p></span></p>
  <p class=MsoNormal style='margin-right:28.0pt;word-break:break-all'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>系（部）领导签字：</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></span><span style='font-size:12.0pt;mso-bidi-font-size:12.0pt;
  font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>（盖章）</span><span lang=EN-US style='font-size:12.0pt;
  mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  <p class=MsoNormal align=right style='margin-right:7.0pt;text-align:right'><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><span
  style='mso-spacerun:yes'>&nbsp; </span></span><span style='font-size:12.0pt;
  mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>年</span><span lang=EN-US
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><span
  style='mso-spacerun:yes'>&nbsp; </span></span><span style='font-size:12.0pt;
  mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>月</span><span lang=EN-US
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><span
  style='mso-spacerun:yes'>&nbsp; </span></span><span style='font-size:12.0pt;
  mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>日</span><span lang=EN-US
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:4;page-break-inside:avoid;height:99.3pt'>
  <td width=91 height="140px" style='width:68.25pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>学生处</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>意见</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=479 colspan=7 valign=bottom style='width:359.1pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:double windowtext 1.5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;mso-border-right-alt:double windowtext 1.5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:99.3pt'>
  <p class=MsoNormal style='margin-right:28.0pt;word-break:break-all'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>处领导签字：</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></span><span style='font-size:12.0pt;mso-bidi-font-size:12.0pt;
  font-family:宋体;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:
  "Times New Roman"'>（盖章）</span><span lang=EN-US style='font-size:12.0pt;
  mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  <p class=MsoNormal align=right style='text-align:right;word-break:break-all'><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><span
  style='mso-spacerun:yes'>&nbsp; </span></span><span style='font-size:12.0pt;
  mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>年</span><span lang=EN-US
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><span
  style='mso-spacerun:yes'>&nbsp; </span></span><span style='font-size:12.0pt;
  mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>月</span><span lang=EN-US
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><span
  style='mso-spacerun:yes'>&nbsp; </span></span><span style='font-size:12.0pt;
  mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>日</span><span lang=EN-US
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:5;page-break-inside:avoid;height:91.15pt'>
  <td width=91 height="140px" style='width:68.25pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'><bean:message key="lable.xb" /></span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>意见</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=479 colspan=7 valign=bottom style='width:359.1pt;border-top:none;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:double windowtext 1.5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;mso-border-right-alt:double windowtext 1.5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:91.15pt'>
  <p class=MsoNormal align=right style='text-align:right;word-break:break-all'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>年</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><span
  style='mso-spacerun:yes'>&nbsp; </span></span><span style='font-size:12.0pt;
  mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>月</span><span lang=EN-US
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><span
  style='mso-spacerun:yes'>&nbsp; </span></span><span style='font-size:12.0pt;
  mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:"Times New Roman";
  mso-hansi-font-family:"Times New Roman"'>日</span><span lang=EN-US
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><span
  style='mso-spacerun:yes'>&nbsp; </span><o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:6;mso-yfti-lastrow:yes;page-break-inside:avoid;
  height:47.3pt'>
  <td width=49 height="70px" style='width:36.9pt;border-top:none;border-left:double windowtext 1.5pt;
  border-bottom:double windowtext 1.5pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid .5pt;
  mso-border-left-alt:double 1.5pt;mso-border-bottom-alt:double 1.5pt;
  mso-border-right-alt:solid .5pt;mso-border-color-alt:windowtext;padding:0cm 5.4pt 0cm 5.4pt;
  '>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;mso-bidi-font-size:12.0pt;font-family:宋体;mso-ascii-font-family:
  "Times New Roman";mso-hansi-font-family:"Times New Roman"'>备注</span><span
  lang=EN-US style='font-size:12.0pt;mso-bidi-font-size:12.0pt'><o:p></o:p></span></p>
  </td>
  <td width=570 colspan=8 style='width:427.35pt;border-top:none;border-left:
  none;border-bottom:double windowtext 1.5pt;border-right:double windowtext 1.5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:47.3pt'>
  <p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>
  </td>
 </tr>
 <![if !supportMisalignedColumns]>
 <tr height=0>
  <td width=49 style='border:none'></td>
  <td width=91 style='border:none'></td>
  <td width=45 style='border:none'></td>
  <td width=62 style='border:none'></td>
  <td width=62 style='border:none'></td>
  <td width=62 style='border:none'></td>
  <td width=133 style='border:none'></td>
  <td width=35 style='border:none'></td>
  <td width=80 style='border:none'></td>
 </tr>
 <![endif]>
</table>

<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>

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
