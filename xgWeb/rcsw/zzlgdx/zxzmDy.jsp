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
<html>
	<base target="_self" />
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta name="Copyright" content="������� zfsoft" />

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<style media="print">
		.noprint{
			display:none;
		}
		.print{
			display:block;
		}
		.tbstyle {
	border-collapse: collapse;
}
		.tbstyle td {
	border: 1px #97B7DB solid;
	padding: 3px;
	font-size: 14px;
}
	</style>
	</head>
	<body>
		<div align="center">
			<table border=1 cellspacing=0 cellpadding=0 align=center width="100%"
				class="theTable">
				<tr>
					<td scope="col" colspan="6">
						<div align="center">
							<h2>
								<strong> <bean:write name='rs' property="xxmc" />ѧ����У���ּ��Ļ��̶ȼ�����</strong>
							</h2>
						</div>
					</td>
				</tr>
				<tr>
					<td width="13%">
						<div align="center">
							����
						</div>
					</td>
					<td width="20%">
						<div align="center">
							<bean:write name='rs' property="xm" />
						</div>
					</td>
					<td width="13%">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td width="20%">
						<div align="center">
							<bean:write name='rs' property="xb" />
						</div>
					</td>
					<td width="13%">
						<div align="center">
							���֤��
						</div>
					</td>
					<td width="21%">
						<div align="center">
							&nbsp;
							<bean:write name='rs' property="sfzh" />
						</div>
					</td>
				</tr>
				<tr height="40px">
					<td>
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						<div align="center">
							<bean:write name='rs' property="xh" />
						</div>
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td>
						&nbsp;
						<bean:write name='rs' property="mzmc" />
					</td>
					<td>
						<div align="center">
							��Уʱ��
						</div>
					</td>
					<td>
						<div align="center">
							&nbsp;
							<bean:write name='rs' property="rxrq" />
						</div>
					</td>
				</tr>
				<tr height="40px">
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name='rs' property="xymc" />
						</div>
					</td>
					<td>
						<div align="center">
							�༶
						</div>
					</td>
					<td colspan="2">
						<div align="center">
							<bean:write name='rs' property="bjmc" />
						</div>
					</td>
				</tr>
				<tr height="500px">
					<td colspan="6" valign="top">
						&nbsp;��У���ּ��Ļ��̶ȼ��������
						<br />
						<br />
						<logic:empty name='rs' property="zxbxjwhcdbx">
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
						</logic:empty>
						<logic:notEmpty name='rs' property="zxbxjwhcdbx">
							<bean:write name='rs' property="zxbxjwhcdbx" />
						</logic:notEmpty>
						<br />
						<div style="margin-top: 350px;margin-right:5px">
							<div align="right">
								�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
							<br />
							<div align="right">
								(
								<bean:message key="lable.xsgzyxpzxy" />
								����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
							<br />
							<div align="right">
								��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
						</div>
						<br />
					</td>
				</tr>
				<tr height="80px">
					<td>
						<div align="center">
							ѧ&nbsp;&nbsp;У
							<br />
							<br />
							��&nbsp;&nbsp;��
						</div>
					</td>
					<td colspan="5">
						<br />
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;�������������ʵ��ͬ��
						<bean:message key="lable.xsgzyxpzxy" />
						�Ը�����У���ּ��Ļ��̶ȵļ���
						<br />
						<div align="right">
							(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br />
						<div align="right">
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<br />
					</td>
				</tr>
			</table>
			</td>
			</tr>
			</table>
		</div>
		<div class="buttontool noprint" align="center">
			<input  class="button2" value="ҳ������"
				onclick="WebBrowser.ExecWB(8,1);">
			<input  class="button2" value="��ӡԤ��"
				onclick="WebBrowser.ExecWB(7,1)">
			<input  class="button2" value="ֱ�Ӵ�ӡ"
				onclick="WebBrowser.ExecWB(6,6)">
		</div>
	</body>
</html>
