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
<html:html>
<base target="_self" />
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
</head>
	<script language="javascript" src="js/function.js"></script>
<style media="print" type="text/css">
  .brk{
	page-break-after:always;
   }
   .noPrin{display:none}
</style>
<body>
	<div colspan="8"/>
			<center>�㽭����ѧ<bean:write name="LoginXn"/>ѧ�굥�ѧ��ǼǱ�</center>
	</div>
		<html:form action="/zjlgPjpy" method="post">

			<table class="tbstyle" width="100%">
				<tr>
					<td align="center" colspan="1">
						��ѧ������
					</td>
					<td colspan="2">
						<bean:write name="rs" property="jxjmc"/>
					</td>
					<td colspan="1" scope="row">
						<div align="center">
							��ѧ�����
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="jxjlbmc"/>
					</td>
				</tr>
				<tr>
						<td align="center" colspan="1">
							ѧ��
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xh"/>
						</td>
					<td width="11%" colspan="1">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="4" scope="col">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							���п���
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="yhkh"/>
					</td>
					<td colspan="1">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="yhlx"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xn"/>
					</td>
					<td colspan="1">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="xq"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xb"/>
					</td>
					<td colspan="1">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="csrq"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							������ò
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="zzmm"/>
					</td>
					<td>
						<div align="center">
							����ְ��
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="zw"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xymc"/>
					</td>
					<td colspan="1">
						<div align="center">
							�༶
						</div>
					</td>
					<td colspan="4">
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							����������
						</div>
					</td>
					<td colspan="1">
						<bean:write name="rs" property="zhszcpcjpm"/>
					</td>
					<td colspan="1">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="1">
							<bean:write name="rs" property="dycj"/>
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td colspan="1">
							<bean:write name="rs" property="zycj"/>
					</td>
					<td>
						<div align="center">
							����
						</div>
					</td>
					<td colspan="1">
							<bean:write name="rs" property="tycj"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="2">
						<div align="center">
							��<br>��<br>��<br>��
						</div>
					</td>
					<td colspan="7">
						�����˵���������ı��֣�����������л�����ķ������ʵ���ȷ��棩
					</td>
				</tr>
				<tr>
					<td colspan="7">
						<bean:write name="rs" property="xxjl"/>
						<br>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1" style="height: 30px">
						<div align="center">
							<br>��<br>��<br>��<br>��<br>��<br>��
						</div>
					</td>
					<td colspan="8" rowspan="1">
						<bean:write name="rs" property="fdyyj"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>ѧ<br>Ժ<br>��<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="xyshyj"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>ѧ<br>У<br>��<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<bean:write name="rs" property="xxshyj"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>ע<br>
						</div>
					</td>
					<td colspan="8">
					<html:textarea name="rs" property="bz" rows='6' style="width:100%;display: none" />
						1. ����һʽ���ݣ�������д����Ҫ����Դ�ӡ��<br>
						2. <bean:message key="lable.xsgzyxpzxy" />���༶��д�����

					</td>
				</tr>
			</table>
	<div class='noPrin' align="center">
				<input type='button' class='button2' value='ҳ������'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='��ӡԤ��'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='ֱ�Ӵ�ӡ'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>

		</html:form>
</body>
</html:html>
