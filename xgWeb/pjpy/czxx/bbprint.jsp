<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">

<head>
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
	<script language="javascript" src="js/function.js"></script>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
<style>
<!--
 /* Font Definitions */
 @font-face
	{font-family:宋体;
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:"\@宋体";
	panose-1:2 1 6 0 3 1 1 1 1 1;}
 /* Style Definitions */
 p.MsoNormal, li.MsoNormal, div.MsoNormal
	{margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	font-size:10.5pt;
	font-family:"Times New Roman";}
p.MsoHeader, li.MsoHeader, div.MsoHeader
	{margin:0cm;
	margin-bottom:.0001pt;
	text-align:center;
	layout-grid-mode:char;
	border:none;
	padding:0cm;
	font-size:9.0pt;
	font-family:"Times New Roman";}
p.MsoFooter, li.MsoFooter, div.MsoFooter
	{margin:0cm;
	margin-bottom:.0001pt;
	layout-grid-mode:char;
	font-size:9.0pt;
	font-family:"Times New Roman";}
p.MsoBodyText, li.MsoBodyText, div.MsoBodyText
	{margin:0cm;
	margin-bottom:.0001pt;
	text-align:center;
	font-size:10.5pt;
	font-family:"Times New Roman";
	font-weight:bold;}
p.MsoBodyTextIndent, li.MsoBodyTextIndent, div.MsoBodyTextIndent
	{margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	text-indent:14.05pt;
	font-size:14.0pt;
	font-family:宋体;
	font-weight:bold;}
p.MsoDate, li.MsoDate, div.MsoDate
	{margin-top:0cm;
	margin-right:0cm;
	margin-bottom:0cm;
	margin-left:5.0pt;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	font-size:14.0pt;
	font-family:宋体;
	font-weight:bold;}
 /* Page Definitions */
 @page Section1
	{size:595.3pt 841.9pt;
	margin:59.55pt 89.85pt 48.2pt 89.85pt;
	layout-grid:15.6pt;}
div.Section1
	{page:Section1;}
 /* List Definitions */
 ol
	{margin-bottom:0cm;}
ul
	{margin-bottom:0cm;}
.style2 {
	font-family: "Times New Roman", Times, serif;
	font-size: 14pt;
}
-->
</style>

</head>

<body lang=ZH-CN style='text-justify-trim:punctuation'>
<div align="center">
  <h2><strong>
  常州信息职业技术<bean:message key="lable.xsgzyxpzxy" />${mc}推荐表
  </strong></h2>
</div>
<div align="left" class="MsoNormal">
<span
  style='font-size:14.0pt;font-family:宋体'>
&nbsp;院（系）别：${rs.xymc }
</span>
</div>
<table class="printstyle" border=1 cellspacing=0 cellpadding=0 width="98%"
 style='margin-left:5.4pt;border-collapse:collapse;border:none'>
 <tr style='height:30.75pt'>
  <td width=96 style='width:72.0pt;border:solid windowtext 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:30.75pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:14.0pt;font-family:宋体'>班级</span></p>
  </td>
  <td width=96 style='width:72.0pt;border:solid windowtext 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:30.75pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;${rs.bjmc }</span></p>
  </td>
  <td width=96 style='width:72.0pt;border:solid windowtext 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:30.75pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:14.0pt;font-family:宋体'>姓名</span></p>
  </td>
  <td width=84 style='width:63.0pt;border:solid windowtext 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:30.75pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;${rs.xm }</span></p>
  </td>
  <td width=120 style='width:90.0pt;border:solid windowtext 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:30.75pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:14.0pt;font-family:宋体'>性别</span></p>
  </td>
  <td width=84 style='width:63.0pt;border:solid windowtext 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:30.75pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;${rs.xb }</span></p>
  </td>
 </tr>
 <tr style='height:34.15pt'>
  <td width=96 rowspan=2 style='width:72.0pt;border:solid windowtext 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:34.15pt'>
  <p align="center" class=MsoBodyText style='text-align:justify;text-justify:inter-ideograph'><span
  style='font-size:14.0pt;font-family:宋体;font-weight:normal'>德育成绩</span></p>
  </td>
  <td width=96 rowspan=2 style='width:72.0pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:34.15pt'>
  <p align="center" class=MsoBodyText style='text-align:justify;text-justify:inter-ideograph'><span
  lang=EN-US style='font-size:14.0pt;font-weight:normal'>&nbsp;${rs.dcj }</span></p>
  </td>
  <td width=96 rowspan=2 style='width:72.0pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:34.15pt'>
  <p align="center" class=MsoBodyText style='text-align:justify;text-justify:inter-ideograph'><span
  style='font-size:14.0pt;font-family:宋体;font-weight:normal'>体育成绩</span></p>
  </td>
  <td width=84 rowspan=2 style='width:63.0pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:34.15pt'>
  <p align="center" class=MsoBodyText style='text-align:justify;text-justify:inter-ideograph'><span
  lang=EN-US style='font-size:14.0pt'>&nbsp;${rs.tcj }</span></p>
  </td>
  <td width=120 style='width:90.0pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:34.15pt'>
  <p align="center" class=MsoBodyText style='text-align:justify;text-justify:inter-ideograph'><span
  style='font-size:14.0pt;font-family:宋体;font-weight:normal'>奖学金等第</span></p>
  </td>
<td width=84 style='width:63.0pt;border:solid windowtext 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:30.75pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;${rs.jxjmc }</span></p>
  </td>
 </tr>
 <tr style='height:34.1pt'>
  <td width=120 style='width:90.0pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:34.1pt'>
  <p align="center" class=MsoBodyText style='text-align:justify;text-justify:inter-ideograph'><span
  style='font-size:12.0pt;font-family:宋体;font-weight:normal'>优秀学生类别</span></p>
  </td>
<td width=84 style='width:63.0pt;border:solid windowtext 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:30.75pt' nowrap="nowrap">
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;${rs.rychmc }</span></p>
  </td>
 </tr>
 <tr style='height:28.5pt'>
  <td width=96 style='width:72.0pt;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:28.5pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:14.0pt'><span
  style='font-size:14.0pt;font-family:宋体'>综合测评班级排名</span></p>
  </td>
  <td width=480 colspan=5 style='width:360.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:28.5pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;${rs.zfpm }</span></p>
  </td>
 </tr>
 <tr style='height:209.65pt'>
  <td width=96 style='width:72.0pt;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:209.65pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:14.0pt'><span
  style='font-size:14.0pt;font-family:宋体'>主要事迹及获奖情况</span></p>
  </td>
  <td width=480 colspan=5 style='width:360.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:156.0pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;${rs.zysj }</span></p>
  </td>
 </tr>
 <tr style='height:131.3pt'>
  <td width=96 style='width:72.0pt;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:131.3pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:14.0pt'><span
  style='font-size:14.0pt;font-family:宋体'>院</span><span lang=EN-US
  style='font-size:14.0pt'>(</span><span style='font-size:14.0pt;font-family:
  宋体'>系</span><span lang=EN-US style='font-size:14.0pt'>)</span></p>
  <p class=MsoNormal align=center style='text-align:center;line-height:14.0pt'><span
  style='font-size:14.0pt;font-family:宋体'>意见</span></p>
  </td>
  <td width=480 colspan=5 style='width:360.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:131.3pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }</span></p>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;</span></p>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span><span style='font-size:14.0pt;font-family:宋体'>盖</span><span
  style='font-size:14.0pt'> </span><span style='font-size:14.0pt;font-family:
  宋体'>章：</span></p>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span><span style='font-size:14.0pt;font-family:宋体'>年</span><span
  lang=EN-US style='font-size:14.0pt'>&nbsp;&nbsp;&nbsp;&nbsp; </span><span
  style='font-size:14.0pt;font-family:宋体'>月</span><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
  style='font-size:14.0pt;font-family:宋体'>日</span></p>
  </td>
 </tr>
 <tr style='height:156.0pt'>
  <td width=96 style='width:72.0pt;border:solid windowtext 1.0pt;border-top:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:156.0pt'>
  <p class=MsoNormal align=center style='text-align:center;line-height:14.0pt'><span
  style='font-size:14.0pt;font-family:宋体'>学工处</span></p>
  <p class=MsoNormal align=center style='text-align:center;line-height:14.0pt'><span
  style='font-size:14.0pt;font-family:宋体'>意见</span></p>
  </td>
  <td width=480 colspan=5 style='width:360.0pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:156.0pt'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;${rs.xxshyj }</span></p>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;</span></p>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;</span></p>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span><span style='font-size:14.0pt;font-family:宋体'>盖</span><span
  style='font-size:14.0pt'> </span><span style='font-size:14.0pt;font-family:
  宋体'>章：</span></p>
  <p class=MsoNormal align=center style='text-align:center'><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span><span style='font-size:14.0pt;font-family:宋体'>年</span><span
  lang=EN-US style='font-size:14.0pt'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
  style='font-size:14.0pt;font-family:宋体'>月</span><span lang=EN-US
  style='font-size:14.0pt'>&nbsp;&nbsp;&nbsp;&nbsp; </span><span
  style='font-size:14.0pt;font-family:宋体'>日</span></p>
  </td>
 </tr>
</table>

<p class=MsoNormal><span lang=EN-US>&nbsp;</span></p>

</div>
    <div align="center" class='noPrin'>
	<button class='button2'  onclick="WebBrowser.ExecWB(8,1);return false;">页面设置</button>
    <button class='button2'  onclick="WebBrowser.ExecWB(7,1);return false;">打印预览</button>
    <button class='button2'  onclick="WebBrowser.ExecWB(6,6);return false;">直接打印</button> 
    </div>
</body>



</html>