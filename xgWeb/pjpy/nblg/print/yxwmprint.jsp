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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>
	<!-- ��ӡ�ؼ�begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
		.noPrin{display:none;}
	</style>
	<!-- end -->
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
</head>
<body>
	<html:form action="/pjpynblgwh">
		<div align="center" style="font-size:20px;font:'����' ">
			<b>${nd} ��������У������������������ѡ���Ƽ���</b>
		</div>
		<br />
		<div>
			<table width="100%" class="printstyle">
				<tr>
					<th width="10%">
						ѧ&nbsp;&nbsp;У
					</th>
					<td width="40%">
						&nbsp;
					</td>
					<th width="12%">
						��ѡ������
						<br />
						��༶(����)
					</th>
					<td width="38%">
						&nbsp;
					</td>
				</tr>
				<tr>
					<th>
						��������
					</th>
					<td>
						&nbsp;
					</td>
					<th>
						��ѡ��
						<br />
						��ְ���
					</th>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<th colspan="4">
						<p align="left">
							��Ҫ�¼�:(����ҳ)
						</p>
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
						<br />
					</th>
				</tr>
				<tr>
					<th>
						<p>
							&nbsp;
						</p>
						<p>
							ѧ
						</p>
						<p>
							У
						</p>
						<p>
							��
						</p>
						<p>
							��
						</p>
						<p>
							&nbsp;
						</p>
					</th>
					<th colspan="3">
						<br />
						<br />
						<br />
						<br />
						<br />
						<p align="right">
							(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<br />
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
					</th>
				</tr>
			</table>
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
		</div>
	</html:form>
</body>
</html:html>
