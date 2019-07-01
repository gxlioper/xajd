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
	<!-- ��ӡ�ؼ�begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
		.noPrin{display:none;}
	</style>
	<!-- end -->
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/pjpyFunction.js"></script>

	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
</head>
<body>
	<html:form action="/pjpyshcbyswh">
		<div align="center" style="font-size:18px;font:'����' ">
			&nbsp;
		</div>
		<h3 align="center">
			${title1}ѧ�ڿγ̳ɼ���
		</h3>
		<table width="98%" class="tbstyle">

			<tr align="center">
				<th scope="col">
					ѧ&nbsp;&nbsp;��
				</th>

				<th scope="col">
					ѧ&nbsp;&nbsp;��
				</th>
				<th scope="col">
					�γ�����
				</th>
				<th scope="col">
					�γ�����
				</th>
				<th scope="col">
					��&nbsp;&nbsp;��
				</th>
				<th scope="col">
					�����ɼ�
				</th>
			</tr>
			<logic:notEmpty name="rs1">
				<logic:iterate name="rs1" id="s">
					<tr style="cursor:hand;" align="center" onclick="rowOnClick(this)">
						<logic:iterate id="v" name="s">
							<td scope="col">
								<bean:write name="v" />
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
				<tr><td colspan="6">&nbsp;</td></tr>
				<tr>
					<td>ƽ���ɼ�</td><td>${map1.pjcj}</td>
					<td>ƽ���ɼ�����</td><td>${map1.pjcjpm}</td>
					<td>���гɼ�</td><td>${map1.cxcj}</td>		
				</tr>
				<tr>
					<td>�ܳɼ�</td><td>${map1.zcj}</td>
					<td>�ܳɼ�����</td><td>${map1.pm}</td>
					<td></td><td></td>		
				</tr>
			</logic:notEmpty>
			<logic:empty name="rs1">
				<tr>
					<th scope="col" colspan="6">
						δ�ҵ��κμ�¼��
					</th>
				</tr>
			</logic:empty>
		</table>
		<h3 align="center">
			${title2}ѧ�ڿγ̳ɼ���
		</h3>
		<table width="98%" class="tbstyle">

			<tr align="center">
				<th scope="col">
					ѧ&nbsp;&nbsp;��
				</th>

				<th scope="col">
					ѧ&nbsp;&nbsp;��
				</th>
				<th scope="col">
					�γ�����
				</th>
				<th scope="col">
					�γ�����
				</th>
				<th scope="col">
					��&nbsp;&nbsp;��
				</th>
				<th scope="col">
					�����ɼ�
				</th>
			</tr>
			<logic:notEmpty name="rs2">
				<logic:iterate name="rs2" id="s">
					<tr style="cursor:hand;" align="center" onclick="rowOnClick(this)">
						<logic:iterate id="v" name="s">
							<td scope="col">
								<bean:write name="v" />
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
				<tr><td colspan="6">&nbsp;</td></tr>
				<tr align="center">
					<td>ƽ���ɼ�</td><td>${map2.pjcj}</td>
					<td>ƽ���ɼ�����</td><td>${map2.pjcjpm}</td>
					<td>���гɼ�</td><td>${map2.cxcj}</td>		
				</tr>
				<tr align="center">
					<td>�ܳɼ�</td><td>${map2.zcj}</td>
					<td>�ܳɼ�����</td><td>${map2.pm}</td>
					<td></td><td></td>		
				</tr>
			</logic:notEmpty>
			<logic:empty name="rs2">
				<tr>
					<th scope="col" colspan="6">
						δ�ҵ��κμ�¼��
					</th>
				</tr>
			</logic:empty>
		</table>
		
	</html:form>
</body>
</html:html>
