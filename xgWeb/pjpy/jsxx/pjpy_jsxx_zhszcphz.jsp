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
    <title>pjpy_jsxx_zhszcphz.jsp</title>
<meta http-equiv=Content-Type content="text/html; charset=GB2312">
<meta name=ProgId content=Excel.Sheet>
<meta name=Generator content="Microsoft Excel 9">
<link rel=File-List href="./pjpy_jsxx_zhszcphz.files/filelist.xml">
<!--[if !mso]>
<style>
v\:* {behavior:url(#default#VML);}
o\:* {behavior:url(#default#VML);}
x\:* {behavior:url(#default#VML);}
.shape {behavior:url(#default#VML);}
</style>
<![endif]-->
<style id="2_15160_Styles">
<!--table
	{mso-displayed-decimal-separator:"\.";
	mso-displayed-thousand-separator:"\,";}
.font015160
	{color:windowtext;
	font-size:12.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font515160
	{color:windowtext;
	font-size:9.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font615160
	{color:windowtext;
	font-size:24.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:黑体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font715160
	{color:windowtext;
	font-size:24.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:"Times New Roman", serif;
	mso-font-charset:0;}
.font815160
	{color:windowtext;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font915160
	{color:windowtext;
	font-size:12.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:"Times New Roman", serif;
	mso-font-charset:0;}
.font1015160
	{color:windowtext;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:宋体;
	mso-generic-font-family:auto;
	mso-font-charset:134;}
.font1115160
	{color:windowtext;
	font-size:10.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:"Times New Roman", serif;
	mso-font-charset:0;}
.font1215160
	{color:windowtext;
	font-size:11.0pt;
	font-weight:400;
	font-style:normal;
	text-decoration:none;
	font-family:"Times New Roman", serif;
	mso-font-charset:0;}
.xl1515160
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
	mso-number-format:"\@";
	text-align:general;
	vertical-align:bottom;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:nowrap;}
.xl2215160
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
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl2315160
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
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl2415160
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
	mso-number-format:"\@";
	text-align:left;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl2515160
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
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl2615160
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
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl2715160
	{padding:0px;
	mso-ignore:padding;
	color:windowtext;
	font-size:8.0pt;
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
.xl2815160
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
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl2915160
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
	mso-number-format:"0\.00_ ";
	text-align:center;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3015160
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
	mso-number-format:"0\.00_ ";
	text-align:center;
	vertical-align:middle;
	border:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3115160
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
	mso-number-format:"\@";
	text-align:left;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:none;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3215160
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
	mso-number-format:"\@";
	text-align:general;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:none;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3315160
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
	mso-number-format:"\@";
	text-align:general;
	vertical-align:middle;
	border-top:none;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3415160
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
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:.5pt solid windowtext;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3515160
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
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3615160
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
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border-top:.5pt solid windowtext;
	border-right:.5pt solid windowtext;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3715160
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
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3815160
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
	mso-number-format:"\@";
	text-align:center;
	vertical-align:middle;
	border-top:none;
	border-right:none;
	border-bottom:.5pt solid windowtext;
	border-left:none;
	mso-background-source:auto;
	mso-pattern:auto;
	white-space:normal;}
.xl3915160
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
	mso-number-format:"\@";
	text-align:center;
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

<body>
<html:form action="/zhszcp_print" method="post">
<div id="2_15160" align=center x:publishsource="Excel">

<table x:str border=0 cellpadding=0 cellspacing=0 width=98% align="center" class=xl2215160
 style='border-collapse:collapse;table-layout:fixed;width:1035pt' >
 <col class=xl2215160 width=31 style='mso-width-source:userset;mso-width-alt:
 992;width:23pt'>
 <col class=xl2215160 width=64 style='mso-width-source:userset;mso-width-alt:
 2048;width:48pt'>
 <col class=xl2215160 width=40 style='mso-width-source:userset;mso-width-alt:
 1280;width:30pt'>
 <col class=xl2215160 width=31 style='mso-width-source:userset;mso-width-alt:
 992;width:23pt'>
 <col class=xl2215160 width=26 style='mso-width-source:userset;mso-width-alt:
 832;width:20pt'>
 <col class=xl2215160 width=31 span=3 style='mso-width-source:userset;
 mso-width-alt:992;width:23pt'>
 <col class=xl2215160 width=34 style='mso-width-source:userset;mso-width-alt:
 1088;width:26pt'>
 <col class=xl2215160 width=30 style='mso-width-source:userset;mso-width-alt:
 960;width:23pt'>
 <col class=xl2215160 width=25 style='mso-width-source:userset;mso-width-alt:
 800;width:19pt'>
 <col class=xl2215160 width=29 style='mso-width-source:userset;mso-width-alt:
 928;width:22pt'>
 <col class=xl2215160 width=35 style='mso-width-source:userset;mso-width-alt:
 1120;width:26pt'>
 <col class=xl2215160 width=40 style='mso-width-source:userset;mso-width-alt:
 1280;width:30pt'>
 <col class=xl2215160 width=32 style='mso-width-source:userset;mso-width-alt:
 1024;width:24pt'>
 <col class=xl2215160 width=54 style='mso-width-source:userset;mso-width-alt:
 1728;width:41pt'>
 <col class=xl2215160 width=34 style='mso-width-source:userset;mso-width-alt:
 1088;width:26pt'>
 <col class=xl2215160 width=37 style='mso-width-source:userset;mso-width-alt:
 1184;width:28pt'>
 <col class=xl2215160 width=42 style='mso-width-source:userset;mso-width-alt:
 1344;width:32pt'>
 <col class=xl2215160 width=38 style='mso-width-source:userset;mso-width-alt:
 1216;width:29pt'>
 <col class=xl2215160 width=55 style='mso-width-source:userset;mso-width-alt:
 1760;width:41pt'>
 <col class=xl2215160 width=36 style='mso-width-source:userset;mso-width-alt:
 1152;width:27pt'>
 <col class=xl2215160 width=53 style='mso-width-source:userset;mso-width-alt:
 1696;width:40pt'>
 <col class=xl2215160 width=35 style='mso-width-source:userset;mso-width-alt:
 1120;width:26pt'>
 <col class=xl2215160 width=42 style='mso-width-source:userset;mso-width-alt:
 1344;width:32pt'>
 <col class=xl2215160 width=80 style='mso-width-source:userset;mso-width-alt:
 2560;width:60pt'>
 <col class=xl2215160 width=72 span=5 style='width:54pt'>
 <tr height=48 style='mso-height-source:userset;height:36.0pt' align="center">
  <td colspan=${map.trlen} align="center" height=48 class=xl3715160 width=98% style='height:36.0pt;
  width:765pt'>学<font class=font715160> </font><font class=font615160>生</font><font
  class=font715160> </font><font class=font615160>综</font><font
  class=font715160> </font><font class=font615160>合素</font><font
  class=font715160> </font><font class=font615160>质</font><font
  class=font715160><span style="mso-spacerun: yes">&nbsp; </span></font><font
  class=font615160>测</font><font class=font715160> </font><font
  class=font615160>评</font><font class=font715160> </font><font
  class=font615160>汇</font><font class=font715160> </font><font
  class=font615160>总</font><font class=font715160> </font><font
  class=font615160>表</font></td>
 </tr>
 <tr class=xl2315160 height=23 style='mso-height-source:userset;height:17.25pt'>
  <td colspan=2 height=23 width=95 style='height:17.25pt;width:71pt'
  align=left valign=top><!--[if gte vml 1]><v:line id="_x0000_s1025" style='position:absolute;
   z-index:1' from="42.75pt,17.25pt" to="1in,129.75pt" strokecolor="windowText [64]"
   o:insetmode="auto"/><v:line id="_x0000_s1026" style='position:absolute;
   z-index:2' from="23.25pt,36.75pt" to="71.25pt,129pt" strokecolor="windowText [64]"
   o:insetmode="auto"/><![endif]--><![if !vml]><span style='mso-ignore:vglayout;
  position:absolute;z-index:0;margin-left:30px;margin-top:22px;width:68px;
  height:153px'>
  <table cellpadding=0 cellspacing=0>
   <tr>
    <td colspan=2 height=23 class=xl2215160 width=95 style='height:17.25pt;
    width:71pt'>系：</td>
   </tr>
  </table>
  </span></td>
  <td colspan=4 class=xl3815160 width=300 style='width:96pt'>系: <bean:write name="map" property="xymc"/></td>
  <td colspan=2 class=xl3815160 width=62 style='width:46pt'>班级：</td>
  <td colspan=4 class=xl3815160 width=64 style='width:49pt'><bean:write name="map" property="bjmc"/></td>
  <td class=xl2315160 width=35 style='width:26pt'></td>
  <td class=xl2315160 width=40 style='width:30pt'></td>
  <td class=xl2315160 width=32 style='width:24pt'></td>
  <td colspan=5 class=xl3815160 width=246 style='width:93pt'>日期：<bean:write name="map" property="year"/></td>
<%--  <td class=xl2215160 width=42 style='width:32pt'></td>--%>
<%--  <td class=xl2415160 width=80 style='width:60pt'></td>--%>
  <td class=xl2315160 width=54 style='width:41pt'></td>
  <td class=xl2315160 width=34 style='width:26pt'></td>
  <td class=xl2315160 width=37 style='width:28pt'></td>
  <td class=xl2315160 width=42 style='width:32pt'></td>
  <td class=xl2315160 width=38 style='width:29pt'></td>
  <td class=xl2315160 width=55 style='width:41pt'></td>
  
  <td class=xl2215160 width=72 style='width:54pt'></td>
  <td class=xl2315160 width=72 style='width:54pt'></td>
  <td class=xl2315160 width=72 style='width:54pt'></td>
  <td class=xl2315160 width=72 style='width:54pt'></td>
  <td class=xl2315160 width=72 style='width:54pt'></td>
 </tr>
 <tr class=xl2615160 height=23 style='mso-height-source:userset;height:17.25pt'>
  <td rowspan=2 height=149 class=xl2515160 width=100 >学号</td>
  <td rowspan=2 class=xl3215160 width=64 style='border-bottom:.5pt solid black;
  width:48pt'>成<font class=font1115160><span style="mso-spacerun:
  yes">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></font><font
  class=font815160>课</font><font class=font1115160><span style="mso-spacerun:
  yes">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span>. . </font><font class=font815160>绩</font><font class=font1115160><span
  style="mso-spacerun: yes">&nbsp;&nbsp;&nbsp;&nbsp; </span></font><font
  class=font815160>程</font><font class=font1115160><span style="mso-spacerun:
  yes">&nbsp; </span></font><font class=font815160>姓</font><font
  class=font1115160><span style="mso-spacerun: yes">&nbsp; </span></font><font
  class=font815160>名</font></td>
  <td colspan=${map.len} class=xl2515160 width=469 style='border-left:none;width:353pt'>智<font
  class=font1215160><span style="mso-spacerun:
  yes">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  </span></font><font class=font1015160>育</font></td>
  <td colspan=3 class=xl3415160 width=113 style='border-right:.5pt solid black;
  border-left:none;width:86pt'>体育</td>
  <td rowspan=2 class=xl2515160 width=38 style='width:29pt'>德育成绩</td>
  <td rowspan=2 class=xl2515160 width=55 style='width:41pt'>综合成绩</td>
  <td rowspan=2 class=xl2515160 width=36 style='width:27pt'>班级排名</td>
  <td rowspan=2 class=xl2515160 width=53 style='width:40pt'>操行等级</td>
  <td rowspan=2 class=xl2515160 width=35 style='width:26pt'>奖学金评定</td>
  <td rowspan=2 class=xl2515160 width=42 style='width:32pt'>先进申报</td>
  <td rowspan=2 class=xl2515160 width=80 style='width:60pt'>备<font
  class=font1215160><span style="mso-spacerun: yes">&nbsp;&nbsp; </span></font><font
  class=font1015160>注</font></td>
  <td class=xl2615160 width=72 style='width:54pt'></td>
  <td class=xl2615160 width=72 style='width:54pt'></td>
  <td class=xl2615160 width=72 style='width:54pt'></td>
  <td class=xl2615160 width=72 style='width:54pt'></td>
  <td class=xl2615160 width=72 style='width:54pt'></td>
 </tr>
 <tr class=xl2615160 height=126 style='height:94.5pt'>
  <td height=126 class=xl2715160 width=40 style='height:94.5pt;border-top:none;
  border-left:none;width:30pt'><bean:write name="rskc" property="kc1"/></td>
  <td class=xl2715160 width=31 style='border-top:none;border-left:none;
  width:23pt'><bean:write name="rskc" property="kc2"/></td>
  <td class=xl2715160 width=26 style='border-top:none;border-left:none;
  width:20pt'><bean:write name="rskc" property="kc3"/></td>
  <td class=xl2715160 width=31 style='border-top:none;border-left:none;
  width:23pt'><bean:write name="rskc" property="kc4"/></td>
  <td class=xl2715160 width=31 style='border-top:none;border-left:none;
  width:23pt'><bean:write name="rskc" property="kc5"/></td>
  
  <logic:greaterThan value="5" property="kclen" name="map">
  <td class=xl2715160 width=31 style='border-top:none;border-left:none;
  width:23pt'><bean:write name="rskc" property="kc6"/></td>
  </logic:greaterThan>
  <logic:greaterThan value="6" property="kclen" name="map">
  <td class=xl2715160 width=34 style='border-top:none;border-left:none;
  width:26pt'><bean:write name="rskc" property="kc7"/></td>
  </logic:greaterThan>
  <logic:greaterThan value="7" property="kclen" name="map">
  <td class=xl2715160 width=30 style='border-top:none;border-left:none;
  width:23pt'><bean:write name="rskc" property="kc8"/></td>
  </logic:greaterThan>
  <logic:greaterThan value="8" property="kclen" name="map">
  <td class=xl2715160 width=25 style='border-top:none;border-left:none;
  width:19pt'><bean:write name="rskc" property="kc9"/></td>
  </logic:greaterThan>
  <logic:greaterThan value="9" property="kclen" name="map">
  <td class=xl2715160 width=29 style='border-top:none;border-left:none;
  width:22pt'><bean:write name="rskc" property="kc10"/></td>
  </logic:greaterThan>
  <logic:greaterThan value="10" property="kclen" name="map">
  <td class=xl2715160 width=35 style='border-top:none;border-left:none;
  width:26pt'><bean:write name="rskc" property="kc11"/></td>
  </logic:greaterThan>
  <logic:greaterThan value="11" property="kclen" name="map">
  <td class=xl2715160 width=40 style='border-top:none;border-left:none;
  width:30pt'><bean:write name="rskc" property="kc12"/></td>
  </logic:greaterThan>
  <logic:greaterThan value="12" property="kclen" name="map">
  <td class=xl2715160 width=32 style='border-top:none;border-left:none;
  width:24pt'><bean:write name="rskc" property="kc13"/></td>
  </logic:greaterThan>

  <td class=xl2515160 width=54 style='border-top:none;border-left:none;
  width:41pt'>总分</td>
  <td class=xl2515160 width=37 style='border-top:none;border-left:none;
  width:26pt' colspan="3">体育成绩</td>
<%--  <td class=xl2515160 width=37 style='border-top:none;border-left:none;--%>
<%--  width:28pt'>平时考核</td>--%>
<%--  <td class=xl2515160 width=42 style='border-top:none;border-left:none;--%>
<%--  width:32pt'>考核成绩</td>--%>
<%--  --%>
  <td class=xl2615160 width=72 style='width:54pt'></td>
  <td class=xl2615160 width=72 style='width:54pt'></td>
  <td class=xl2615160 width=72 style='width:54pt'></td>
  <td class=xl2615160 width=72 style='width:54pt'></td>
  <td class=xl2615160 width=72 style='width:54pt'></td>
 </tr>
 <logic:iterate name="rs" id="v">
 <tr height=18 style='mso-height-source:userset;height:14.1pt'>
  <td height=18 class=xl2815160 width=100 ><bean:write name="v" property="xh"/></td>
  <td class=xl2815160 width=64 style='border-top:none;border-left:none;
  width:48pt'><bean:write name="v" property="xm"/></td>
  <td class=xl2815160 width=40 style='border-top:none;border-left:none;
  width:30pt'><bean:write name="v" property="kccj1"/></td>
  <td class=xl2815160 width=31 style='border-top:none;border-left:none;
  width:23pt'><bean:write name="v" property="kccj2"/></td>
  <td class=xl2815160 width=26 style='border-top:none;border-left:none;
  width:20pt'><bean:write name="v" property="kccj3"/></td>
  <td class=xl2815160 width=31 style='border-top:none;border-left:none;
  width:23pt'><bean:write name="v" property="kccj4"/></td>
  <td class=xl2815160 width=31 style='border-top:none;border-left:none;
  width:23pt'><bean:write name="v" property="kccj5"/></td>
  
  <logic:greaterThan value="5" property="kclen"  name="map">
  <td class=xl2815160 width=31 style='border-top:none;border-left:none;
  width:23pt'><bean:write name="v" property="kccj6"/></td>
  </logic:greaterThan>
  <logic:greaterThan value="6" property="kclen" name="map">
  <td class=xl2815160 width=34 style='border-left:none;
  width:26pt'><bean:write name="v" property="kccj7"/></td>
  </logic:greaterThan>
  <logic:greaterThan value="7" property="kclen" name="map">
  <td class=xl2815160 width=30 style='border-top:none;
  width:23pt'><bean:write name="v" property="kccj8"/></td>
  </logic:greaterThan>
  <logic:greaterThan value="8" property="kclen" name="map">
  <td class=xl2815160 width=25 style='border-top:none;border-left:none;
  width:19pt'><bean:write name="v" property="kccj9"/></td>
  </logic:greaterThan>
  <logic:greaterThan value="9" property="kclen" name="map">
  <td class=xl2815160 width=29 style='border-top:none;border-left:none;
  width:22pt'><bean:write name="v" property="kccj10"/></td>
  </logic:greaterThan>
  <logic:greaterThan value="10" property="kclen" name="map">
  <td class=xl2815160 width=35 style='border-top:none;border-left:none;
  width:26pt'><bean:write name="v" property="kccj11"/></td>
  </logic:greaterThan>
  <logic:greaterThan value="11" property="kclen" name="map">
  <td class=xl2815160 width=40 style='border-top:none;border-left:none;
  width:30pt'><bean:write name="v" property="kccj12"/></td>
  </logic:greaterThan>
  <logic:greaterThan value="12" property="kclen" name="map">
  <td class=xl2815160 width=32 style='border-top:none;border-left:none;
  width:24pt'><bean:write name="v" property="kccj13"/></td>
  </logic:greaterThan>
  
  <td class=xl2815160 width=54 style='width:41pt'>${v.zcjzf}</td>
  <td class=xl2815160 width=37 style='border-top:none;width:26pt' colspan="3">${v.tcjzf}</td>
<%--  <td class=xl2815160 width=37 style='border-top:none;border-left:none;--%>
<%--  width:28pt'>3.5</td>--%>
<%--  <td class=xl2815160 width=42 style='border-top:none;border-left:none;--%>
<%--  width:32pt'>8.5</td>--%>
  
  <td class=xl2815160 align=right>${v.dcjzf}</td>
  <td class=xl3015160 width=55 style='border-top:none;width:41pt'>${v.kpf}
  </td>
  <td class=xl2815160 width=36 style='border-top:none;border-left:none;
  width:27pt'>${v.bjpm}</td>
  <td class=xl2815160 width=53 style='border-top:none;border-left:none;
  width:40pt'>${v.cxdj}</td>
  <td class=xl2815160 width=35 style='border-top:none;border-left:none;
  width:26pt'>　</td>
  <td class=xl2815160 width=42 style='border-top:none;border-left:none;
  width:32pt'>　</td>
  <td class=xl2815160 width=80 style='border-top:none;border-left:none;
  width:60pt'>　</td>
  <td class=xl2215160 width=72 style='width:54pt'></td>
  <td class=xl2215160 width=72 style='width:54pt'></td>
  <td class=xl2215160 width=72 style='width:54pt'></td>
  <td class=xl2215160 width=72 style='width:54pt'></td>
  <td class=xl2215160 width=72 style='width:54pt'></td>
 </tr>
 </logic:iterate>
 <tr height=19 style='height:14.25pt'>
  <td colspan=${map.trlen} height=19 class=xl3115160 width=98% style='height:14.25pt;
  width:765pt'>附：此表请严格按照苏信院<font class=font915160>{2004}7</font><font
  class=font015160>号文《学生综合素质测评条例》填写。</font></td>
 </tr>
 <tr height=19 style='height:14.25pt'>
  <td height=19 class=xl2215160 width=31 style='height:14.25pt;width:23pt'></td>
  <td class=xl2215160 width=64 style='width:48pt'></td>
  <td class=xl2215160 width=40 style='width:30pt'></td>
  <td class=xl2215160 width=31 style='width:23pt'></td>
  <td class=xl2215160 width=26 style='width:20pt'></td>
  <td class=xl2215160 width=31 style='width:23pt'></td>
  <td class=xl2215160 width=31 style='width:23pt'></td>
  <td class=xl2215160 width=31 style='width:23pt'></td>
  <td class=xl2215160 width=34 style='width:26pt'></td>
  <td class=xl2215160 width=30 style='width:23pt'></td>
  <td class=xl2215160 width=25 style='width:19pt'></td>
  <td class=xl2215160 width=29 style='width:22pt'></td>
  <td class=xl2215160 width=35 style='width:26pt'></td>
  <td class=xl2215160 width=40 style='width:30pt'></td>
  <td class=xl2215160 width=32 style='width:24pt'></td>
  <td class=xl2215160 width=54 style='width:41pt'></td>
  <td class=xl2215160 width=34 style='width:26pt'></td>
  <td class=xl2215160 width=37 style='width:28pt'></td>
  <td class=xl2215160 width=42 style='width:32pt'></td>
  <td class=xl2215160 width=38 style='width:29pt'></td>
  <td class=xl2215160 width=55 style='width:41pt'></td>
  <td class=xl2215160 width=36 style='width:27pt'></td>
  <td class=xl2215160 width=53 style='width:40pt'></td>
  <td class=xl2215160 width=35 style='width:26pt'></td>
  <td class=xl2215160 width=42 style='width:32pt'></td>
  <td class=xl2215160 width=80 style='width:60pt'></td>
  <td class=xl2215160 width=72 style='width:54pt'></td>
  <td class=xl2215160 width=72 style='width:54pt'></td>
  <td class=xl2215160 width=72 style='width:54pt'></td>
  <td class=xl2215160 width=72 style='width:54pt'></td>
  <td class=xl2215160 width=72 style='width:54pt'></td>
 </tr>
<%-- <tr height=30 style='mso-height-source:userset;height:22.5pt'>--%>
<%--  <td colspan=4 height=30 class=xl2215160 width=166 style='height:22.5pt;--%>
<%--  width:124pt'>班主任签名：</td>--%>
<%--  <td class=xl2215160 width=26 style='width:20pt'></td>--%>
<%--  <td class=xl2215160 width=31 style='width:23pt'></td>--%>
<%--  <td class=xl2215160 width=31 style='width:23pt'></td>--%>
<%--  <td class=xl2215160 width=31 style='width:23pt'></td>--%>
<%--  <td colspan=8 class=xl2215160 width=279 style='width:211pt'>辅导员签字：</td>--%>
<%--  <td class=xl2215160 width=34 style='width:26pt'></td>--%>
<%--  <td class=xl2215160 width=37 style='width:28pt'></td>--%>
<%--  <td class=xl2215160 width=42 style='width:32pt'></td>--%>
<%--  <td colspan=3 class=xl2215160 width=129 style='width:97pt'>系部意见：</td>--%>
<%--  <td class=xl2215160 width=53 style='width:40pt'></td>--%>
<%--  <td class=xl2215160 width=35 style='width:26pt'></td>--%>
<%--  <td class=xl2215160 width=42 style='width:32pt'></td>--%>
<%--  <td class=xl2215160 width=80 style='width:60pt'></td>--%>
<%--  <td class=xl2215160 width=72 style='width:54pt'></td>--%>
<%--  <td class=xl2215160 width=72 style='width:54pt'></td>--%>
<%--  <td class=xl2215160 width=72 style='width:54pt'></td>--%>
<%--  <td class=xl2215160 width=72 style='width:54pt'></td>--%>
<%--  <td class=xl2215160 width=72 style='width:54pt'></td>--%>
<%-- </tr>--%>
</table>
</div>
<font class=xl3915160>班主任签名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;辅导员签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xb" />意见：</font>
</html:form>
  </body>
  </html>
</html:html>