<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- ��ӡ�ؼ�begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
		.noPrin{display:none;}
	</style>
		<!-- end -->
	</head>

	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		<p align="center"><font size="3">${xxmc}����ѧ�����ŵǼǱ�</font></p>
		<span ><font size="1">ѧ�꣺${xn}</font></span>
		<table width="100%" id="rsT" class="printstyle">
			<tr>
				<td colspan="2" align="center" valign="center">
					��������
				</td>
				<td colspan="3" >
					&nbsp;${rs.stmc }
				</td>
			</tr>
			<tr  height="260px">
				<td  align="center" valign="center">
					��<br/>
					&nbsp;<br/>
					Ҫ<br/>
					&nbsp;<br/>
					��<br/>
					&nbsp;<br/>
					��<br/>
					&nbsp;<br/>
					��<br/>
					&nbsp;<br/>
					��
				</td>
				<td colspan="4">
						&nbsp;
				</td>
			</tr>
			<tr  height="260px">
				<td align="center" valign="center">
					��<br/>
					&nbsp;<br/>
					��<br/>
					&nbsp;<br/>
					��<br/>
					&nbsp;<br/>
					��<br/>
					&nbsp;<br/>
					��<br/>
					&nbsp;<br/>
					��<br/>
					&nbsp;<br/>
					��
				</td>
				<td colspan="2" align="right" valign="bottom">
					<br/><br/><br/><br/><br/><br/>
							ǩ�֣��£�<br/>
                      ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
				</td>
				<td align="center" valign="center">
					��<br/>
					&nbsp;<br/>
					��<br/>
					&nbsp;<br/>
					֧<br/>
					&nbsp;<br/>
					��<br/>
					&nbsp;<br/>
					��<br/>
				</td>
				<td  align="right" valign="bottom">
					<br/><br/><br/><br/><br/><br/>
							ǩ�֣��£�<br/>
                      ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
				</td>
			</tr>
			<tr  height="260px">
				<td align="center" valign="center">
					Ժ<br/>
					&nbsp;<br/>
					��<br/>
					&nbsp;<br/>
					ί<br/>
					&nbsp;<br/>
					��<br/>
					&nbsp;<br/>
					��<br/>
				</td>
				<td colspan="2" align="right" valign="bottom">
					<br/><br/><br/><br/><br/><br/>
							ǩ�֣��£�<br/>
                      ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
				</td>
				<td align="center" valign="center">
					Ժ<br/>
					&nbsp;<br/>
					��<br/>
					&nbsp;<br/>
					ί<br/>
					&nbsp;<br/>
					��<br/>
					&nbsp;<br/>
					��
				</td>
				<td align="right" valign="bottom">
					<br/><br/><br/><br/><br/><br/>
							ǩ�֣��£�<br/>
                      ��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
				</td>
			</tr>
			<tr>
				<td align="center" valign="center">
					��
					<br/>
					ע
				</td>
				<td colspan="4">
					&nbsp;
				</td>
			</tr>
			<tr height=0>
				<td style="width:20px"></td>
				<td style="width:30px"></td>
				<td style="width:220px"></td>
				<td style="width:20px"></td>
				<td style="width:250px"></td>
			</tr>
		</table>
			<p align="left">�˱�����֯��д��һʽ���ݡ�</p>
			<p align="right">�����ųɶ�����ѧԺίԱ��</p>
		<div align="center" class='noPrin'>
			<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				ҳ������
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				��ӡԤ��
			</button>
			<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
				ֱ�Ӵ�ӡ
			</button>
		</div>
		
	</body>
</html>
