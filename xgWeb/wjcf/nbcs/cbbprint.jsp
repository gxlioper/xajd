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
.MsoBodyText
	{text-align:center;
	font-size:12.0pt;
	font-family:����_GB2312;}
.MsoDate
	{text-align:justify;
	text-justify:inter-ideograph;
	font-size:14.0pt;
	font-family:����_GB2312;}
.Section1
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
	<div class=Section1 style='layout-grid:15.6pt' align="center"> 
  <b><span style='font-size:15.0pt;font-family:����'>${xxmc }ѧ��Υ�ʹ��ֳʱ���</span></b>
  <table class="printstyle" align="center" width="100%"> 
    <tr> 
      <td width=112 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>Υ��������</span></p></td> 
      <td width=126 class="Normal" align="center">&nbsp;${rs.xm } </td> 
      <td width=63 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>�� ��</span></p></td> 
      <td width=112 class="Normal" align="center">&nbsp;${rs.xb } </td> 
      <td width=63 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>ѧ ��</span></p></td> 
      <td width=126 class="Normal" align="center">&nbsp;${rs.xh } </td> 
    </tr> 
    <tr> 
      <td width=112 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>ѧ<span lang=EN-US>&nbsp; </span>Ժ</span></p></td> 
      <td width=126 class="Normal" align="center">&nbsp;${rs.xymc } </td> 
      <td width=63 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>ר ҵ</span></p></td> 
      <td width=112 class="Normal" align="center">&nbsp;${rs.zymc } </td> 
      <td width=63 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>�� ��</span></p></td> 
      <td width=126 class="Normal" align="center">&nbsp;${rs.nj } </td> 
    </tr> 
    <tr> 
      <td width=112 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>Υ��ԭ��</span></p></td> 
      <td width=490 colspan=5 class="Normal" align="center">&nbsp;${rs.cfyymc } </td> 
    </tr> 
    <tr> 
      <td width=112 colspan=2 class="Normal"> <p align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>���鲿��</span></p></td> 
      <td width=490 colspan=5 class="Normal">&nbsp; </td> 
    </tr> 
    <tr> 
      <td width=82 class="Normal"> <p class=MsoDate align=center style='text-align:center'><span
  style='font-size:12.0pt;'>Υ</span></p> 
         <p class=MsoDate align=center style='text-align:center'><span
  style='font-size:12.0pt;'>��</span></p> 
        <p class=MsoDate align=center style='text-align:center'><span
  style='font-size:12.0pt;'>��</span></p> 
        <p class=MsoDate align=center style='text-align:center'><span
  style='font-size:12.0pt;'>ʵ</span></p></td> 
      <td width=520 colspan=6 valign=top class="Normal">&nbsp; 
      	<p >&nbsp;</p>
      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }
      </td> 
    </tr> 
    <tr> 
      <td width=82 class="Normal"> <p class=MsoDate align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>����</span></p> 
        <p class=MsoDate align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>����</span></p> 
        <p class=MsoDate align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>����</span></p> 
        <p class=MsoDate align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>���</span></p></td> 
      <td width=520 colspan=6 valign="bottom"   class="Normal"> <p align="right"><span
  style='font-family:����;"Times New Roman"'>������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;</span><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span style='font-family:����;
  &quot;Times New Roman&quot;'>��</span><span lang=EN-US>&nbsp; </span><span style='font-family:����;
  &quot;Times New Roman&quot;'>��</span><span
  lang=EN-US>&nbsp; </span><span
  style='font-family:����;"Times New Roman"'>��</span></p> 
        <p align="right"><span
  style='font-family:����;"Times New Roman"'>��</span><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp; </span><span
  style='font-family:����;"Times New Roman"'>��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p></td> 
    </tr> 
    <tr> 
      <td width=82 valign=top class="Normal"> <p align=center style='text-align:center;line-height:13.0pt;
  '><span style='font-size:12.0pt;font-family:����_GB2312'><bean:message key="lable.xsgzyxpzxy" /></span></p> 
        <p align=center style='text-align:center;line-height:13.0pt;
  '><span style='font-size:12.0pt;font-family:����_GB2312'>����</span></p> 
        <p align=center style='text-align:center;line-height:13.0pt;
  '><span style='font-size:12.0pt;font-family:����_GB2312'>���</span></p></td> 
      <td width=520 colspan=6 valign="bottom" class="Normal">
      	<p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyclyj }</p>
       <p align="right">
      <span
  style='font-family:����;"Times New Roman"'>������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;</span><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span style='font-family:����;
  &quot;Times New Roman&quot;'>��</span><span lang=EN-US>&nbsp; </span><span style='font-family:����;
  &quot;Times New Roman&quot;'>��</span><span
  lang=EN-US>&nbsp; </span><span
  style='font-family:����;"Times New Roman"'>��</span></p> 
        <p align="right"><span
  style='font-family:����;"Times New Roman"'>��</span><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp; </span><span
  style='font-family:����;"Times New Roman"'>��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p></td> 
    </tr> 
    <tr> 
      <td width=82 valign=top class="Normal"> <p class=MsoDate align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>ѧ����</span></p> 
        <p class=MsoDate align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>����</span></p> 
        <p class=MsoDate align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>���</span></p></td> 
      <td width=520 colspan=6 valign="bottom" class="Normal"> <p align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxclyj }</p><p align="right"><span
  style='font-family:����;"Times New Roman"'>������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;</span><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span style='font-family:����;
  &quot;Times New Roman&quot;'>��</span><span lang=EN-US>&nbsp; </span><span style='font-family:����;
  &quot;Times New Roman&quot;'>��</span><span
  lang=EN-US>&nbsp; </span><span
  style='font-family:����;"Times New Roman"'>��</span></p> 
        <p style='text-align:right;
  '><span
  style='font-family:����;"Times New Roman"'> ��</span><span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp; </span><span
  style='font-family:����;"Times New Roman"'>��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></p></td> 
    </tr> 
    <tr> 
      <td width=82 valign=top class=""> <p class=MsoDate align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:����_GB2312'>��ע</span></p></td> 
      <td width=520 colspan=6 valign=top class="Normal">&nbsp; </td> 
    </tr> 
    <tr height=0> 
      <td width=82 class="Normal"></td> 
      <td width=30 class="Normal"></td> 
      <td width=126 class="Normal"></td> 
      <td width=63 class="Normal"></td> 
      <td width=112 class="Normal"></td> 
      <td width=63 class="Normal"></td> 
      <td width=126 class="Normal"></td> 
    </tr> 
  </table> 
</div> 
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
