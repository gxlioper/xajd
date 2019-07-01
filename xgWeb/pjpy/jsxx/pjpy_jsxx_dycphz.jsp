<%@page contentType="application/vnd.ms-excel;charset=GBK" %>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template" prefix="template" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
<html xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">
  <head>  
    <title>pjpy_jsxx_dycphz.jsp</title>
<meta http-equiv=Content-Type content="text/html; charset=GB2312">
<meta name=ProgId content=Excel.Sheet>
<meta name=Generator content="Microsoft Excel 9">
<link rel=File-List href="./pjpy_jsxx_dycphz.files/filelist.xml">
<!--[if !mso]>
<style>
v\:* {behavior:url(#default#VML);}
o\:* {behavior:url(#default#VML);}
x\:* {behavior:url(#default#VML);}
.shape {behavior:url(#default#VML);}
</style>
<![endif]-->
<style id="1_13486_Styles">
<!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
.font013486
	{color:windowtext;
	font-size:12.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font513486
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font613486
	{color:windowtext;
	font-size:18.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:黑体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font713486
	{color:windowtext;
	font-size:18.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:"Times New Roman", serif;
	mso-font-charset:0;}
.font813486
	{color:windowtext;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font913486
	{color:windowtext;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font1013486
	{color:windowtext;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:"Times New Roman", serif;
	mso-font-charset:0;}
.font1113486
	{color:windowtext;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:"Times New Roman", serif;
	mso-font-charset:0;}
.font1213486
	{color:windowtext;
	font-size:12.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:"Times New Roman", serif;
	mso-font-charset:0;}
.xl2213486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:12.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl2313486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl2413486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl2513486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl2613486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:12.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl2713486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl2813486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl2913486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3013486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3113486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:12.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3213486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:none;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3313486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:general;
	vertical-align:middle;
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3413486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:18.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:黑体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3513486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:24.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:黑体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3613486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:none;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3713486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:12.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:center;
	vertical-align:middle;
	border-top:none;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3813486
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-number-format:General;
	text-align:left;
	vertical-align:middle;
	border-top:none;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
ruby
	{ruby-align:left;}
rt
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;
	mso-char-type:none;}
-->
</style>
</head>

<body link=blue vlink=purple>
<html:form action="/dyszjf_print" method="post">
<div id="1_13486" align=center x:publishsource="Excel">
<table x:str border=0 cellpadding=0 cellspacing=0 width=842 class=xl2213486
 style='border-collapse:collapse;table-layout:fixed;width:636pt'>
 <col class=xl2213486 width=31 style='mso-width-source:userset;mso-width-alt:
 992;width:23pt'>
 <col class=xl2213486 width=64 style='mso-width-source:userset;mso-width-alt:
 2048;width:48pt'>
 <col class=xl2213486 width=37 span=18 style='mso-width-source:userset;
 mso-width-alt:1184;width:28pt'>
 <col class=xl2213486 width=38 style='mso-width-source:userset;mso-width-alt:
 1216;width:29pt'>
 <col class=xl2213486 width=43 style='mso-width-source:userset;mso-width-alt:
 1376;width:32pt'>
 <tr height=58 style='mso-height-source:userset;height:43.5pt'>
  <td colspan=22 height=58 class=xl3413486 width=842 style='height:43.5pt;
  width:636pt'>学<font class=font713486> </font><font class=font613486>生</font><font
  class=font713486> </font><font class=font613486>德</font><font
  class=font713486> </font><font class=font613486>育</font><font
  class=font713486> </font><font class=font613486>测</font><font
  class=font713486> </font><font class=font613486>评</font><font
  class=font713486> </font><font class=font613486>汇</font><font
  class=font713486> </font><font class=font613486>总</font><font
  class=font713486> </font><font class=font613486>表</font></td>
 </tr>
 <tr class=xl2313486 height=38 style='mso-height-source:userset;height:28.5pt'>
  <td colspan=2 height=38 width=95 style='height:28.5pt;width:71pt' align=left
  valign=top><!--[if gte vml 1]><v:line id="_x0000_s1025" style='position:absolute;
   z-index:1' from="42pt,27.75pt" to="71.25pt,1in" strokecolor="windowText [64]"
   o:insetmode="auto"/><v:line id="_x0000_s1026" style='position:absolute;
   z-index:2' from="23.25pt,48pt" to="71.25pt,1in" strokecolor="windowText [64]"
   o:insetmode="auto"/><![endif]--><![if !vml]><span style='mso-ignore:vglayout;
  position:absolute;z-index:0;margin-left:30px;margin-top:36px;width:67px;
  height:62px'><img width=67 height=62
  src="./pjpy_jsxx_dycphz.files/1_13486_image001.gif" v:shapes="_x0000_s1025 _x0000_s1026"></span><![endif]><span
  style='mso-ignore:vglayout2'>
  <table cellpadding=0 cellspacing=0>
   <tr>
    <td colspan=2 height=38 class=xl2213486 width=95 style='height:28.5pt;
    width:71pt'>系：</td>
   </tr>
  </table>
  </span></td>
  <td colspan=4 class=xl3613486 width=148 style='width:112pt'><bean:write name="map" property="xymc"/></td>
  <td colspan=2 class=xl3713486 width=74 style='width:56pt'>班级：</td>
  <td colspan=4 class=xl3613486 width=148 style='width:112pt'><bean:write name="map" property="bjmc"/></td>
  <td class=xl2313486 width=37 style='width:28pt'></td>
  <td class=xl2313486 width=37 style='width:28pt'></td>
  <td class=xl2313486 width=37 style='width:28pt'></td>
  <td class=xl2313486 width=37 style='width:28pt'></td>
  <td colspan=6 class=xl3813486 width=229 style='width:173pt'>测评日期：<font
  class=font1013486><span style="mso-spacerun:
  yes"><bean:write name="map" property="year"/> </span></font><font
  class=font913486>年</font><font class=font1013486><span style="mso-spacerun:
  yes"><bean:write name="map" property="month"/> </span></font><font
  class=font913486>月</font><font class=font1013486><span style="mso-spacerun:
  yes"><bean:write name="map" property="day"/> </span></font><font class=font913486>日</font></td>
 </tr>
 <tr class=xl2413486 height=23 style='mso-height-source:userset;height:17.25pt'>
  <td rowspan=2 height=58 class=xl3013486 width=31 style='height:43.5pt;
  width:23pt'>学号</td>
  <td rowspan=2 class=xl3213486 width=64 style='border-bottom:.5pt solid black;
  width:48pt'>成<font class=font1113486><span style="mso-spacerun:
  yes">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></font><font
  class=font813486>课</font><font class=font1113486><span style="mso-spacerun:
  yes">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span>.<span style="mso-spacerun: yes">&nbsp; </span>.</font><font
  class=font813486>绩</font><font class=font1113486><span style="mso-spacerun:
  yes">&nbsp;&nbsp;&nbsp;&nbsp; </span></font><font class=font813486>程</font><font
  class=font1113486><span style="mso-spacerun: yes">&nbsp; </span></font><font
  class=font813486>姓</font><font class=font1113486><span style="mso-spacerun:
  yes">&nbsp; </span></font><font class=font813486>名</font></td>
  <td colspan=6 class=xl2713486 width=222 style='border-right:.5pt solid black;
  border-left:none;width:168pt'>平<font class=font1013486> </font><font
  class=font913486>时</font><font class=font1013486> </font><font
  class=font913486>表</font><font class=font1013486> </font><font
  class=font913486>现</font><font class=font1013486><span style="mso-spacerun:
  yes">&nbsp; </span>60</font><font class=font913486>分</font></td>
  <td colspan=6 class=xl2713486 width=222 style='border-right:.5pt solid black;
  border-left:none;width:168pt'>综<font class=font1013486> </font><font
  class=font913486>合</font><font class=font1013486> </font><font
  class=font913486>评</font><font class=font1013486> </font><font
  class=font913486>价</font><font class=font1013486><span style="mso-spacerun:
  yes">&nbsp; </span>40</font><font class=font913486>分</font></td>
  <td colspan=6 class=xl2713486 width=222 style='border-right:.5pt solid black;
  border-left:none;width:168pt'>附<font class=font1013486> </font><font
  class=font913486>加</font><font class=font1013486>(</font><font
  class=font913486>扣</font><font class=font1013486>) </font><font
  class=font913486>分</font></td>
  <td rowspan=2 class=xl3013486 width=38 style='border-top:none;width:29pt'>综合成绩</td>
  <td rowspan=2 class=xl3013486 width=43 style='border-top:none;width:32pt'>操行等第</td>
 </tr>
 <tr class=xl2313486 height=35 style='mso-height-source:userset;height:26.25pt'>
  <td height=35 class=xl2513486 width=37 style='height:26.25pt;border-top:none;
  border-left:none;width:28pt'>集体活动</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>上课学习</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>劳动卫生</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>校纪校规</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>其它</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>合计</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>政治学习</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>学习态度</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>道德修养</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>社会活动</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>法制观念</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>合计</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>干部任职</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>校风学风</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>积极上进</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>各类比赛</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>违纪处理</td>
  <td class=xl2513486 width=37 style='border-top:none;border-left:none;
  width:28pt'>合计</td>
 </tr>
 <logic:iterate name="rs" id="v">
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl2613486 width=31 style='height:14.25pt;border-top:none;
  width:23pt'><bean:write name="v" property="xh"/></td>
  <td class=xl2613486 width=64 style='border-top:none;border-left:none;
  width:48pt'><bean:write name="v" property="xm"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="jthdbx"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="skxxbx"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="ldwsbx"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="xjxgbx"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="qtbx"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="psbxhj"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="zzxx"/></td>
  <td class=xl2613486 width=37 style='border-top:none;
  width:28pt'><bean:write name="v" property="xxtd"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="ddxy"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="shhd"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="fzgn"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="zhpjhj"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="gbrzjf"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="xfxfjf"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="jjsjjf"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="glbsjf"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="wjcljf"/></td>
  <td class=xl2613486 width=37 style='border-top:none;border-left:none;
  width:28pt'><bean:write name="v" property="fjfhj"/></td>
  <td class=xl2613486 width=38 style='border-top:none;border-left:none;
  width:29pt'><bean:write name="v" property="dyzf"/></td>
  <td class=xl2613486 width=43 style='border-top:none;border-left:none;
  width:32pt'><bean:write name="v" property="dycxdj"/></td>
 </tr>
 </logic:iterate>
 <tr height=34 style='mso-height-source:userset;height:25.5pt'>
  <td colspan=19 height=34 class=xl3113486 width=724 style='height:25.5pt;
  width:547pt'>填表说明：依据学生素质综合测评条例所列项目，得分用“<font class=font1213486>+</font><font
  class=font013486>”表示，扣分用“</font><font class=font1213486>-</font><font
  class=font013486>”表示。</font></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=38 style='width:29pt'></td>
  <td class=xl2213486 width=43 style='width:32pt'></td>
 </tr>
 <tr height=31 style='mso-height-source:userset;height:23.25pt'>
  <td height=31 class=xl2213486 width=31 style='height:23.25pt;width:23pt'></td>
  <td colspan=6 class=xl2213486 width=249 style='width:188pt'>班委、团支部意见（班长）：</td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td colspan=3 class=xl2213486 width=111 style='width:84pt'>团支书：</td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=38 style='width:29pt'></td>
  <td class=xl2213486 width=43 style='width:32pt'></td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl2213486 width=31 style='height:14.25pt;width:23pt'></td>
  <td class=xl2213486 width=64 style='width:48pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=38 style='width:29pt'></td>
  <td class=xl2213486 width=43 style='width:32pt'></td>
 </tr>
 <tr height=38 style='mso-height-source:userset;height:28.5pt'>
  <td height=38 class=xl2213486 width=31 style='height:28.5pt;width:23pt'></td>
  <td colspan=2 class=xl2213486 width=101 style='width:76pt'>班主任签字：</td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td colspan=3 class=xl2213486 width=111 style='width:84pt'>辅导员签字：</td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=38 style='width:29pt'></td>
  <td class=xl2213486 width=43 style='width:32pt'></td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl2213486 width=31 style='height:14.25pt;width:23pt'></td>
  <td colspan=6 class=xl2213486 width=249 style='width:188pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=37 style='width:28pt'></td>
  <td class=xl2213486 width=38 style='width:29pt'></td>
  <td class=xl2213486 width=43 style='width:32pt'></td>
 </tr>
 <![if supportMisalignedColumns]>
 <tr height=0 style='display:none'>
  <td width=31 style='width:23pt'></td>
  <td width=64 style='width:48pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=37 style='width:28pt'></td>
  <td width=38 style='width:29pt'></td>
  <td width=43 style='width:32pt'></td>
 </tr>
 <![endif]>
</table>
</div>
</html:form>
  </body>
  </html>
</html:html>
