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
	<!--[if gte mso 9]><xml>
 <o:DocumentProperties>
  <o:Author>����163���԰</o:Author>
  <o:LastAuthor>����163���԰</o:LastAuthor>
  <o:Revision>2</o:Revision>
  <o:TotalTime>2</o:TotalTime>
  <o:Created>2010-03-29T08:46:00Z</o:Created>
  <o:LastSaved>2010-03-29T08:46:00Z</o:LastSaved>
  <o:Pages>1</o:Pages>
  <o:Words>57</o:Words>
  <o:Characters>331</o:Characters>
  <o:Company>ICBCOA</o:Company>
  <o:Lines>2</o:Lines>
  <o:Paragraphs>1</o:Paragraphs>
  <o:CharactersWithSpaces>387</o:CharactersWithSpaces>
  <o:Version>11.5606</o:Version>
 </o:DocumentProperties>
</xml><![endif]--><!--[if gte mso 9]><xml>
 <w:WordDocument>
  <w:SpellingState>Clean</w:SpellingState>
  <w:GrammarState>Clean</w:GrammarState>
  <w:PunctuationKerning/>
  <w:DrawingGridVerticalSpacing>7.8 ��</w:DrawingGridVerticalSpacing>
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
   <w:BreakWrappedTables/>
   <w:SnapToGridInCell/>
   <w:WrapTextWithPunct/>
   <w:UseAsianBreakRules/>
   <w:DontGrowAutofit/>
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
	{font-family:����;
	panose-1:2 1 6 0 3 1 1 1 1 1;
	mso-font-alt:SimSun;
	mso-font-charset:134;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:3 135135232 16 0 262145 0;}
@font-face
	{font-family:����;
	panose-1:2 1 6 0 3 1 1 1 1 1;
	mso-font-alt:SimHei;
	mso-font-charset:134;
	mso-generic-font-family:auto;
	mso-font-pitch:variable;
	mso-font-signature:1 135135232 16 0 262144 0;}
@font-face
	{font-family:����_GB2312;
	panose-1:2 1 6 9 3 1 1 1 1 1;
	mso-font-charset:134;
	mso-generic-font-family:modern;
	mso-font-pitch:fixed;
	mso-font-signature:1 135135232 16 0 262144 0;}
@font-face
	{font-family:"\@����_GB2312";
	panose-1:2 1 6 9 3 1 1 1 1 1;
	mso-font-charset:134;
	mso-generic-font-family:modern;
	mso-font-pitch:fixed;
	mso-font-signature:1 135135232 16 0 262144 0;}
@font-face
	{font-family:"\@����";
	panose-1:2 1 6 0 3 1 1 1 1 1;
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
	mso-font-signature:1 135135232 16 0 262144 0;}
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
<!--[if gte mso 10]>
<style>
 /* Style Definitions */
 table.MsoNormalTable
	{mso-style-name:��ͨ���;
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
  <body>
    <html:form action="/wjcfzjcmwh.do">
    <p align="center"><span style='font-size:
18.0pt;mso-bidi-font-size:12.0pt;font-family:����;mso-ascii-font-family:"Times New Roman"'>�㽭��ý<bean:message key="lable.xsgzyxpzxy" />ѧ�����ֳ���ǼǱ�</span>
</p>
	<table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0 width="98%" align="center"
	 style='width:441.0pt;margin-left:5.4pt;border-collapse:collapse;border:none;
	 mso-border-alt:solid windowtext .5pt;mso-padding-alt:0cm 5.4pt 0cm 5.4pt;
	 mso-border-insideh:.5pt solid windowtext;mso-border-insidev:.5pt solid windowtext'>
	 <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;page-break-inside:avoid;
	  height:30.45pt'>
	  <td width=60 rowspan=2 style='width:45.0pt;border:solid windowtext 1.0pt;
	  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.45pt'>
	  <p class=MsoNormal align=center style='text-align:center;mso-line-height-alt:
	  0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>����<span lang=EN-US><o:p></o:p></span></span></p>
	  <p class=MsoNormal align=center style='text-align:center;mso-line-height-alt:
	  0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>��Ϣ<span lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	  <td width=60 valign=top style='width:45.0pt;border:solid windowtext 1.0pt;
	  border-left:none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
	  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.45pt'>
	  <p class=MsoNormal align=center style='text-align:center;line-height:25.0pt'><span
	  style='font-size:12.0pt;font-family:����_GB2312'>����<span lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	  <td width=132 valign=top style='width:99.0pt;border:solid windowtext 1.0pt;
	  border-left:none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
	  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.45pt'>
	  <p class=MsoNormal align=center style='text-align:center;line-height:25.0pt'><span
	  lang=EN-US style='font-size:12.0pt;font-family:����_GB2312'><o:p>&nbsp;${rs.xm }</o:p></span></p>
	  </td>
	  <td width=60 valign=top style='width:45.0pt;border:solid windowtext 1.0pt;
	  border-left:none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
	  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.45pt'>
	  <p class=MsoNormal align=center style='text-align:center;line-height:25.0pt'><span
	  style='font-size:12.0pt;font-family:����_GB2312'>�Ա�<span lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	  <td width=60 valign=top style='width:45.0pt;border:solid windowtext 1.0pt;
	  border-left:none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
	  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.45pt'>
	  <p class=MsoNormal align=center style='text-align:center;line-height:25.0pt'><span
	  lang=EN-US style='font-size:12.0pt;font-family:����_GB2312'><o:p>&nbsp;${rs.xb }</o:p></span></p>
	  </td>
	  <td width=60 valign=top style='width:45.0pt;border:solid windowtext 1.0pt;
	  border-left:none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
	  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.45pt'>
	  <p class=MsoNormal align=center style='text-align:center;line-height:25.0pt'><span
	  style='font-size:12.0pt;font-family:����_GB2312'>ѧ��<span lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	  <td width=156 colspan=2 valign=top style='width:117.0pt;border:solid windowtext 1.0pt;
	  border-left:none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
	  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.45pt'>
	  <p class=MsoNormal align=center style='text-align:center;line-height:25.0pt'><span
	  lang=EN-US style='font-size:12.0pt;font-family:����_GB2312'><o:p>&nbsp;${rs.xh }</o:p></span></p>
	  </td>
	 </tr>
	 <tr style='mso-yfti-irow:1;page-break-inside:avoid;height:30.45pt'>
	  <td width=60 valign=top style='width:45.0pt;border-top:none;border-left:none;
	  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
	  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
	  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.45pt'>
	  <p class=MsoNormal align=center style='text-align:center;line-height:25.0pt'><span
	  style='font-size:12.0pt;font-family:����_GB2312'><bean:message key="lable.xsgzyxpzxy" /><span lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	  <td width=132 valign=top style='width:99.0pt;border-top:none;border-left:
	  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
	  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
	  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.45pt'>
	  <p class=MsoNormal align=center style='text-align:center;line-height:25.0pt'><span
	  lang=EN-US style='font-size:12.0pt;font-family:����_GB2312'><o:p>&nbsp;${rs.xymc }</o:p></span></p>
	  </td>
	  <td width=60 valign=top style='width:45.0pt;border-top:none;border-left:none;
	  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
	  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
	  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.45pt'>
	  <p class=MsoNormal align=center style='text-align:center;line-height:25.0pt'><span
	  style='font-size:12.0pt;font-family:����_GB2312'>�༶<span lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	  <td width=120 colspan=2 valign=top style='width:90.0pt;border-top:none;
	  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
	  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
	  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.45pt'>
	  <p class=MsoNormal align=center style='text-align:center;line-height:25.0pt'><span
	  lang=EN-US style='font-size:12.0pt;font-family:����_GB2312'><o:p>&nbsp;${rs.bjmc }</o:p></span></p>
	  </td>
	  <td width=60 valign=top style='width:45.0pt;border-top:none;border-left:none;
	  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
	  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
	  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.45pt'>
	  <p class=MsoNormal align=center style='text-align:center;line-height:25.0pt'><span
	  style='font-size:12.0pt;font-family:����_GB2312'>�绰<span lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	  <td width=96 style='width:72.0pt;border-top:none;border-left:none;border-bottom:
	  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:
	  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
	  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:30.45pt'>
	  <p class=MsoNormal align=center style='text-align:center;line-height:25.0pt'><span
	  lang=EN-US style='font-size:12.0pt;font-family:����_GB2312'><o:p>&nbsp;${rs.sjhm }</o:p></span></p>
	  </td>
	 </tr>
	 <tr style='mso-yfti-irow:2;page-break-inside:avoid;height:56.5pt'>
	  <td width=60 style='width:45.0pt;border:solid windowtext 1.0pt;border-top:
	  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
	  padding:0cm 5.4pt 0cm 5.4pt;height:56.5pt'>
	  <p class=MsoNormal align=center style='text-align:center;mso-line-height-alt:
	  0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>Υ��������� ���� ���<span
	  lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	  <td width=528 colspan=7 valign=top style='width:396.0pt;border-top:none;
	  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
	  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
	  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:56.5pt'>
	  <br/>
	  <p class=MsoNormal >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }
	  <p class=MsoNormal style='line-height:25.0pt'><span style='mso-bidi-font-size:
	  10.5pt;font-family:����;mso-hansi-font-family:"Times New Roman";mso-bidi-font-family:
	  ����;color:black;mso-font-kerning:0pt;mso-ansi-language:ZH-CN'><span
	  style='mso-spacerun:yes'>&nbsp; </span>&nbsp;&nbsp;�����㽭��ý<bean:message key="lable.xsgzyxpzxy" />ѧ��Υ�ʹ��ְ취��&nbsp;${rs.cfwh }&nbsp;����__����__��涨������<u>&nbsp;${rs.cflbmc }&nbsp;</u>���֡�</span><b
	  style='mso-bidi-font-weight:normal'><span lang=EN-US style='font-size:12.0pt;
	  font-family:����_GB2312;color:red'><o:p></o:p></span></b></p>
	  </td>
	 </tr>
	 <tr style='mso-yfti-irow:3;page-break-inside:avoid;height:84.85pt'>
	  <td width=60 style='width:45.0pt;border:solid windowtext 1.0pt;border-top:
	  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
	  padding:0cm 5.4pt 0cm 5.4pt;height:84.85pt'>
	  <p class=MsoNormal align=center style='text-align:center;mso-line-height-alt:
	  0pt'><span style='font-size:12.0pt;font-family:����_GB2312'≯����¼<span
	  lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	  <td width=528 colspan=7 style='width:396.0pt;border-top:none;border-left:
	  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
	  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
	  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:84.85pt'>
	  <p class=MsoNormal style='margin-left:-5.45pt;mso-para-margin-left:-.52gd;
	  text-indent:5.4pt;mso-char-indent-count:.45;line-height:25.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u><span
	  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u></span><span
	  style='font-size:12.0pt;font-family:����_GB2312'>��<u><span lang=EN-US><span
	  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span></u>��<u><span
	  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span></u>�գ�<u><span
	  lang=EN-US><span
	  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  </span></span></u>��<u><span lang=EN-US><span
	  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  </span></span></u>��ʦ��Υ��ѧ��̸�������ݰ�����<span lang=EN-US>(1)</span>Υ�������<span
	  lang=EN-US>(2)</span>���ֳ�������<span lang=EN-US>(3) </span>����������Ȩ����<span
	  lang=EN-US><o:p></o:p></span></span></p>
	  <p class=MsoNormal style='text-indent:114.0pt;mso-char-indent-count:9.5;
	  line-height:25.0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>ѧ��ǩ�֣�<span
	  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp; </span><span
	  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span
	  style='mso-spacerun:yes'>&nbsp;</span></span>��<span lang=EN-US><span
	  style='mso-spacerun:yes'>&nbsp;&nbsp; </span></span>��<span lang=EN-US><span
	  style='mso-spacerun:yes'>&nbsp;&nbsp; </span></span>��<span lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	 </tr>
	 <tr style='mso-yfti-irow:4;page-break-inside:avoid;height:100.25pt'>
	  <td width=60 style='width:45.0pt;border:solid windowtext 1.0pt;border-top:
	  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
	  padding:0cm 5.4pt 0cm 5.4pt;height:100.25pt'>
	  <p class=MsoNormal align=center style='text-align:center;mso-line-height-alt:
	  0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>ѧ����������¼<span
	  lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	  <td width=528 colspan=7 style='width:396.0pt;border-top:none;border-left:
	  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
	  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
	  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:100.25pt'>
	  <p class=MsoNormal style='text-indent:24.0pt;mso-char-indent-count:2.0;
	  line-height:25.0pt'><span lang=EN-US style='font-size:12.0pt;mso-ascii-font-family:
	  ����_GB2312;mso-fareast-font-family:����_GB2312'>��</span><span style='font-size:
	  12.0pt;font-family:����_GB2312'>ѧ����ͬ��Υ�������������֪�����ֵĳ�������ݡ�<span lang=EN-US><o:p></o:p></span></span></p>
	  <p class=MsoNormal style='text-indent:24.0pt;mso-char-indent-count:2.0;
	  line-height:25.0pt'><span lang=EN-US style='font-size:12.0pt;mso-ascii-font-family:
	  ����_GB2312;mso-fareast-font-family:����_GB2312'>��</span><span style='font-size:
	  12.0pt;font-family:����_GB2312'>ѧ��<span class=GramE>��</span>��ͬ��Υ������������������������ݽ�����磺<span
	  lang=EN-US><o:p></o:p></span></span></p>
	  <p class=MsoNormal style='line-height:25.0pt'><span lang=EN-US
	  style='font-size:12.0pt;font-family:����_GB2312'><o:p>&nbsp;</o:p></span></p>
	  <p class=MsoNormal style='line-height:25.0pt'><span lang=EN-US
	  style='font-size:12.0pt;font-family:����_GB2312'><o:p>&nbsp;</o:p></span></p>
	  <p class=MsoNormal style='line-height:25.0pt'><span lang=EN-US
	  style='font-size:12.0pt;font-family:����_GB2312'><o:p>&nbsp;</o:p></span></p>
	  <p class=MsoNormal style='line-height:25.0pt'><span lang=EN-US
	  style='font-size:12.0pt;font-family:����_GB2312'><o:p>&nbsp;</o:p></span></p>
	  <p class=MsoNormal style='line-height:25.0pt'><span lang=EN-US
	  style='font-size:12.0pt;font-family:����_GB2312'><o:p>&nbsp;</o:p></span></p>
	  <p class=MsoNormal style='text-indent:210.0pt;mso-char-indent-count:17.5;
	  line-height:25.0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>ѧ��ǩ�֣�<span
	  lang=EN-US><o:p></o:p></span></span></p>
	  <p class=MsoNormal style='text-indent:264.0pt;mso-char-indent-count:22.0;
	  line-height:25.0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>&nbsp;&nbsp;��<span
	  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></span>��<span
	  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp; </span></span>��<span
	  lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	 </tr>
	 <tr style='mso-yfti-irow:5;mso-yfti-lastrow:yes;page-break-inside:avoid;
	  height:108.55pt'>
	  <td width=60 style='width:45.0pt;border:solid windowtext 1.0pt;border-top:
	  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
	  padding:0cm 5.4pt 0cm 5.4pt;height:108.55pt'>
	  <p class=MsoNormal align=center style='text-align:center;mso-line-height-alt:
	  0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>��ѧ��������ݵĺ�ʵ���<span
	  lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	  <td width=528 colspan=7 valign=top style='width:396.0pt;border-top:none;
	  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
	  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
	  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:108.55pt'>
	  <p class=MsoNormal style='line-height:25.0pt'><span lang=EN-US
	  style='font-size:12.0pt;font-family:����_GB2312'><o:p>&nbsp;</o:p></span></p>
	  <p class=MsoNormal style='text-indent:228.0pt;mso-char-indent-count:19.0;
	  line-height:25.0pt'><span lang=EN-US style='font-size:12.0pt;font-family:
	  ����_GB2312'><o:p>&nbsp;</o:p></span></p>
	  <p class=MsoNormal style='text-indent:228.0pt;mso-char-indent-count:19.0;
	  line-height:25.0pt'><span lang=EN-US style='font-size:12.0pt;font-family:
	  ����_GB2312'><o:p>&nbsp;</o:p></span></p>
	  <p class=MsoNormal style='line-height:25.0pt'><span lang=EN-US
	  style='font-size:12.0pt;font-family:����_GB2312'><o:p>&nbsp;</o:p></span></p>
	  <p class=MsoNormal style='text-indent:120.0pt;mso-char-indent-count:10.0;
	  line-height:25.0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>��ʵ��ǩ�֣�<span
	  lang=EN-US><span
	  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  </span></span>��<span lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;
	  </span></span>��<span lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;
	  </span></span>��<span lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	 </tr>
	 
	 <tr style='mso-yfti-irow:4;page-break-inside:avoid;height:100.25pt'>
	  <td width=60 style='width:45.0pt;border:solid windowtext 1.0pt;border-top:
	  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
	  padding:0cm 5.4pt 0cm 5.4pt;height:100.25pt'>
	  <p class=MsoNormal align=center style='text-align:center;mso-line-height-alt:
	  0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>������У�ڼ������ܹ�����<span
	  lang=EN-US><o:p></o:p></span></span></p>
	  </td>
	  <td width=528 colspan=7 style='width:396.0pt;border-top:none;border-left:
	  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
	  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
	  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:100.25pt'>
	  <p class=MsoNormal style='text-indent:24.0pt;mso-char-indent-count:2.0;
	  line-height:25.0pt'>
	  
			
	  <span style='font-size:12.0pt;font-family:����_GB2312'>
	 	<logic:notEmpty name="lnwjList">
	 	<logic:iterate name="lnwjList" id="wjInfo">
			ѧ��:${wjInfo.xn }&nbsp;&nbsp;���:${wjInfo.nd }&nbsp;&nbsp;
			Υ�����${wjInfo.cflbmc }&nbsp;&nbsp;Υ��ԭ��${wjInfo.cfyymc }<br>
		</logic:iterate>
		 </logic:notEmpty>
	  <span lang=EN-US><o:p></o:p></span></span></p>
	 
	  
	  </td>
	 </tr>
	</table>
	<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>	 
	<div style='page-break-before:always;'>&nbsp;</div>
    
    <table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0 width="98%" align="center"
 style='width:490.0pt;margin-left:5.4pt;border-collapse:collapse;border:none;
 mso-border-alt:solid windowtext .5pt;mso-padding-alt:0cm 5.4pt 0cm 5.4pt;
 mso-border-insideh:.5pt solid windowtext;mso-border-insidev:.5pt solid windowtext'>
 <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;page-break-inside:avoid;
  height:131.9pt'>
  <td width=60 style='width:45.0pt;border:solid windowtext 1.0pt;mso-border-alt:
  solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:131.9pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-line-height-alt:
  0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>���۾���ѧ������<span
  lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=528 style='width:396.0pt;border:solid windowtext 1.0pt;border-left:
  none;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:131.9pt'>
  <p class=MsoNormal style='text-indent:24.0pt;mso-char-indent-count:2.0;
  line-height:25.0pt'><span lang=EN-US style='font-size:12.0pt;font-family:
  ����_GB2312'><span style='mso-spacerun:yes'>&nbsp;</span><u><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></u></span><span
  style='font-size:12.0pt;font-family:����_GB2312'>��<u><span lang=EN-US><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span></u>��<u><span
  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span></u>�գ�</span><span
  style='font-family:����_GB2312'>��</span><u><span lang=EN-US style='font-size:
  12.0pt;font-family:����_GB2312'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></span></u><span style='font-size:12.0pt;font-family:����_GB2312'>�������ۣ������������<u><span
  lang=EN-US><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></span></u>ͬѧ<u><span lang=EN-US><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><o:p></o:p></span></u>���֡�</span></p>
  <p class=MsoNormal style='text-indent:174.0pt;mso-char-indent-count:14.5;
  line-height:25.0pt'><span style='font-size:12.0pt;font-family:����_GB2312'><bean:message key="lable.xsgzyxpzxy" />ѧ�������쵼ǩ�֣�<span
  lang=EN-US><o:p></o:p></span></span></p>
   <p class=MsoNormal style='text-indent:222.0pt;mso-char-indent-count:18.5;
  line-height:25.0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>��<span
  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span>��<span
  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span>��<span
  lang=EN-US><o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:1;page-break-inside:avoid;height:116.05pt'>
  <td width=60 style='width:45.0pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:116.05pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-line-height-alt:
  0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>֪ͨ���ֽ��<span
  lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=528 style='width:396.0pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:116.05pt'>
  <p class=MsoNormal style='text-indent:36.0pt;mso-char-indent-count:3.0;
  line-height:25.0pt'><u><span lang=EN-US style='font-size:12.0pt;font-family:
  ����_GB2312'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span></u><span
  style='font-size:12.0pt;font-family:����_GB2312'>��<u><span lang=EN-US><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;</span></span></u>��<u><span
  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span></u>�գ���<u><span
  lang=EN-US><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></span></u>��ʦ��<u><span lang=EN-US><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></span></u>��ʦ��Υ��ѧ���ʹﴦ���ļ�������֪����ʱ�޼���ʽ��<span lang=EN-US><o:p></o:p></span></span></p>
  <p class=MsoNormal style='text-indent:24.0pt;mso-char-indent-count:2.0;
  line-height:25.0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>ѧ��ǩ�����<u><span
  lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>���ҳ���֪���<u><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></u>��</span><span
  lang=EN-US style='font-family:����_GB2312'><o:p></o:p></span>
  <br/>
  <span  style='font-size:12.0pt;font-family:����_GB2312;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��<span
  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span>��<span
  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span>��</span><span
  lang=EN-US style='font-family:����_GB2312'><o:p></o:p></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:2;page-break-inside:avoid;height:116.85pt'>
  <td width=60 style='width:45.0pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:116.85pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-line-height-alt:
  0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>�������<span
  lang=EN-US><o:p></o:p></span></span></p>
  </td>
  <td width=528 style='width:396.0pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:116.85pt'>
  <p class=MsoNormal style='line-height:25.0pt'><span lang=EN-US
  style='font-size:12.0pt;font-family:����_GB2312'><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span><span lang=EN-US
  style='font-size:12.0pt;mso-ascii-font-family:����_GB2312;mso-fareast-font-family:
  ����_GB2312'>1.</span><span style='font-size:12.0pt;font-family:����_GB2312'>����ѧ�����ֳ���<span
  lang=EN-US><o:p></o:p></span></span></p>
  <p class=MsoNormal style='text-indent:24.0pt;mso-char-indent-count:2.0;
  line-height:25.0pt'><span lang=EN-US style='font-size:12.0pt;mso-ascii-font-family:
  ����_GB2312;mso-fareast-font-family:����_GB2312'>&nbsp;&nbsp;2.</span><span style='font-size:
  12.0pt;font-family:����_GB2312'>������ѧ�����ֳ���<span lang=EN-US><o:p></o:p></span></span></p>
  <p class=MsoNormal style='text-indent:144.0pt;mso-char-indent-count:12.0;
  line-height:25.0pt'><span style='font-size:12.0pt;font-family:����_GB2312'><bean:message key="lable.xsgzyxpzxy" />ѧ�������쵼ǩ��<span
  lang=EN-US>(</span>�ǹ���<span lang=EN-US>)</span>��<span lang=EN-US><o:p></o:p></span></span></p>
  <p class=MsoNormal style='text-indent:222.0pt;mso-char-indent-count:18.5;
  line-height:25.0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>��<span
  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span>��<span
  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span>��<span
  lang=EN-US><o:p></o:p></span></span></p>
  </td>
 </tr>
 <tr style='mso-yfti-irow:3;mso-yfti-lastrow:yes;page-break-inside:avoid;
  height:271.95pt'>
  <td width=60 style='width:45.0pt;border:solid windowtext 1.0pt;border-top:
  none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:271.95pt'>
  <p class=MsoNormal align=center style='text-align:center;mso-line-height-alt:
  0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>��ע<span lang=EN-US><o:p></o:p></span></span></p>
  <p class=MsoNormal align=center style='text-align:center;mso-line-height-alt:
  0pt'><span lang=EN-US style='font-size:12.0pt;font-family:����_GB2312'><span
  style='mso-spacerun:yes'>&nbsp;</span><o:p></o:p></span></p>
  </td>
  <td width=528 valign=top style='width:396.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 5.4pt 0cm 5.4pt;height:271.95pt'>
  <p class=MsoNormal style='text-indent:24.0pt;mso-char-indent-count:2.0;
  line-height:25.0pt'><span lang=EN-US style='font-size:12.0pt;mso-ascii-font-family:
  ����_GB2312;mso-fareast-font-family:����_GB2312'>1.</span><span style='font-size:
  12.0pt;font-family:����_GB2312'>����<span class=GramE>�ļ��ļ�</span>�ţ�<u><span
  lang=EN-US><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span><o:p></o:p></span></u></span></p>
  <p class=MsoNormal style='text-indent:24.0pt;mso-char-indent-count:2.0;
  line-height:25.0pt'><span lang=EN-US style='font-size:12.0pt;mso-ascii-font-family:
  ����_GB2312;mso-fareast-font-family:����_GB2312'>2.</span><span style='font-size:
  12.0pt;font-family:����_GB2312'>ѧ�����߼�¼<span lang=EN-US><o:p></o:p></span></span></p>
  <p class=MsoNormal style='margin-left:42.0pt;text-indent:-18.0pt;line-height:
  25.0pt;mso-list:l0 level1 lfo1;tab-stops:list 42.0pt'><span
  lang=EN-US style='font-size:12.0pt;font-family:����_GB2312;mso-bidi-font-family:
  ����_GB2312'><span style='mso-list:Ignore'>(1)</span></span><span
  style='font-size:12.0pt;font-family:����_GB2312'>δ�������<span lang=EN-US><o:p></o:p></span></span></p>
  <p class=MsoNormal style='margin-left:-5.35pt;mso-para-margin-left:-.51gd;
  text-indent:30.0pt;mso-char-indent-count:2.5;line-height:25.0pt'><span
  lang=EN-US style='font-size:12.0pt;font-family:����_GB2312'>(2)<u><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></u></span><span
  style='font-size:12.0pt;font-family:����_GB2312'>��<u><span lang=EN-US><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp; </span></span></u>��<u><span
  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span></u>��������ߣ�<u><span
  lang=EN-US><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></u>��<u><span
  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp; </span></span></u>��<u><span
  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span></u>�վ�Уѧ�����ߴ���ίԱ���о���ά��<span
  lang=EN-US>(</span>���ġ�����<span lang=EN-US>)</span>ԭ�����������<u> <span lang=EN-US><span
  style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span></u>��<span
  lang=EN-US><o:p></o:p></span></span></p>
  <p class=MsoNormal style='text-indent:24.0pt;mso-char-indent-count:2.0;
  line-height:25.0pt'><span lang=EN-US style='font-size:12.0pt;mso-ascii-font-family:
  ����_GB2312;mso-fareast-font-family:����_GB2312'>3.</span><span style='font-size:
  12.0pt;font-family:����_GB2312'>�浵��ʽ<span lang=EN-US><o:p></o:p></span></span></p>
  <p class=MsoNormal align=left style='text-align:left;text-indent:24.0pt;
  mso-char-indent-count:2.0;line-height:25.0pt'><span lang=EN-US
  style='font-size:12.0pt;font-family:����_GB2312'>(1)</span><span
  style='font-size:12.0pt;font-family:����_GB2312'>�Ѵ���ѧУԺ�����鵵����<span lang=EN-US><o:p></o:p></span></span></p>
  <p class=MsoNormal align=left style='text-align:left;text-indent:24.0pt;
  mso-char-indent-count:2.0;line-height:25.0pt'><span lang=EN-US
  style='font-size:12.0pt;font-family:����_GB2312'>(2)</span><span
  style='font-size:12.0pt;font-family:����_GB2312'>�Ѵ���ѧ�����˵�����<span lang=EN-US><o:p></o:p></span></span></p>
  <p class=MsoNormal align=left style='text-align:left;text-indent:24.0pt;
  mso-char-indent-count:2.0;line-height:25.0pt'><span lang=EN-US
  style='font-size:12.0pt;mso-ascii-font-family:����_GB2312;mso-fareast-font-family:
  ����_GB2312'>4.</span><span style='font-size:12.0pt;font-family:����_GB2312'>����<span
  lang=EN-US><o:p></o:p></span></span></p>
  <p class=MsoNormal align=left style='text-align:left;text-indent:210.0pt;
  mso-char-indent-count:17.5;line-height:25.0pt'><span style='font-size:12.0pt;
  font-family:����_GB2312'>������ǩ�֣�<span lang=EN-US><o:p></o:p></span></span></p>
  <p class=MsoNormal style='text-indent:240.0pt;mso-char-indent-count:20.0;
  line-height:25.0pt'><span style='font-size:12.0pt;font-family:����_GB2312'>��<span
  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span>��<span
  lang=EN-US><span style='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp; </span></span>��</span><span
  lang=EN-US style='font-family:����_GB2312'><o:p></o:p></span></p>
  </td>
 </tr>
</table>

<p class=MsoNormal><span lang=EN-US><o:p>&nbsp;</o:p></span></p>
    
    </html:form>
    <br/>
    <div align="center" class='noPrin'>
	<button type="button" class='button2'  onclick="WebBrowser.ExecWB(8,1);return false;">ҳ������</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(7,1);return false;">��ӡԤ��</button>
    <button type="button" class='button2'  onclick="WebBrowser.ExecWB(6,6);return false;">ֱ�Ӵ�ӡ</button>
    </div>
   
  </body>
</html:html>
