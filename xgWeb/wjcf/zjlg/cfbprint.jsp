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
			<span style='font-size:22.0pt;font-family:����'>${xxmc }ѧ�����֣�����������֪��</span>
		</p>
		<p align=center style='text-align:center'>
			<span style='font-size:15.0pt;font-family:����'>��ѧ�����棩</span>
		</p>
				<p>
			<span style='font-size:16.0pt;
font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;${rs.xm }&nbsp;&nbsp;��</span>
		</p>
		<p align=left style='text-align:left;'>
			<span style='font-size:16.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;��<span
				lang=EN-US><logic:empty name="rs" property="cfyymc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty><logic:notEmpty name="rs" property="cfyymc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.cfyymc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:notEmpty></span>ԭ�򣬾��о���<br/>����<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>��<br/>��������<span lang=EN-US><logic:empty name="rs" property="cflbmc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty><logic:notEmpty name="rs" property="cflbmc">&nbsp;&nbsp;&nbsp;&nbsp;${rs.cflbmc }&nbsp;&nbsp;&nbsp;&nbsp;</logic:notEmpty>
			</span>���֡�</span>
		</p>
		<p align=left style='text-align:left;'>
			<span style='font-size:16.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;�ش˸�֪���������飬�����յ�����֪����������������Уѧ�����ߴ���ίԱ�ᣨУ���칫�ң�����������ߡ�</span>
		</p>
		<p align=right style='text-align:right;'>
			<span style='font-size:15.0pt;font-family:����_GB2312'>��ίѧ����</span>
		</p>
		<p align=right style='text-align:right;
word-break:break-all'>
			<span style='font-size:15.0pt;font-family:����_GB2312'>��<span
				lang=EN-US>&nbsp; </span>��<span lang=EN-US>&nbsp; </span>��</span>
		</p>
		<p align="center">
			<span  style='font-size:16.0pt;
font-family:����_GB2312'>������������������������������������������������������</span>
		</p>
		<p align=center style='text-align:center'>
			<span style='font-size:22.0pt;font-family:����'>${xxmc }ѧ�����֣�����������֪��</span>
		</p>
		<p align=center style='text-align:center'>
			<span style='font-size:15.0pt;font-family:����'>��ѧУ���棩</span>
		</p>
		<p>
			<span style='font-size:16.0pt;
font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;${rs.xm }&nbsp;&nbsp;��</span>
		</p>
		<p align=left style='text-align:left;'>
			<span style='font-size:16.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;��<span
				lang=EN-US><logic:empty name="rs" property="cfyymc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty><logic:notEmpty name="rs" property="cfyymc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rs.cfyymc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:notEmpty></span>ԭ�򣬾��о���<br/>����<span lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>��<br/>��������<span lang=EN-US><logic:empty name="rs" property="cflbmc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</logic:empty><logic:notEmpty name="rs" property="cflbmc">&nbsp;&nbsp;&nbsp;&nbsp;${rs.cflbmc }&nbsp;&nbsp;&nbsp;&nbsp;</logic:notEmpty>
			</span>���֡�</span>
		</p>
		<p align=left style='text-align:left;'>
			<span style='font-size:16.0pt;font-family:����_GB2312'>&nbsp;&nbsp;&nbsp;&nbsp;�ش˸�֪���������飬�����յ�����֪����������������Уѧ�����ߴ���ίԱ�ᣨУ���칫�ң�����������ߡ�</span>
		</p>
		<p align=right style='text-align:right;'>
			<span style='font-size:15.0pt;font-family:����_GB2312'>����ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
		</p>
		<p align=right style='text-align:right;
word-break:break-all'>
			<span style='font-size:15.0pt;font-family:����_GB2312'>��<span
				lang=EN-US>&nbsp; </span>��<span lang=EN-US>&nbsp; </span>��</span>
		</p>
		
	</html:form>
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
		<%--    </div>--%>
</body>
</html:html>
