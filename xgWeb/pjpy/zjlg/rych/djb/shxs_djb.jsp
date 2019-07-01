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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
<style media="print" type="text/css">
   .noPrin{display:none}
</style>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
    <object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<body>
		<script language="javascript" src="js/function.js"></script>

		<html:form action="/zjlgPjpy" method="post">
			<div align="center" style="font-size: 20px;font: bold">
							�㽭����ѧ<u>&nbsp;<bean:write name="xn"/>&nbsp;</u>ѧ�ꡰ����ѧ�����ǼǱ�
			</div>
			<br>
			<table width="100%" class="tbstyle">
				<tr>
					<td width="5%" align="center">
						����
					</td>
					<td width="10%">
						<bean:write name="rs" property="xm"/>
					</td>
					<td width="5%"  align="center">
						�Ա�
					</td>
					<td width="8%">
						<bean:write name="rs" property="xb"/>
					</td>
					<td width="5%"  align="center">
						��Դ��
					</td>
					<td width="15%" >
					<bean:write name="rs" property="syd"/>
					</td>
					<td width="5%"  align="center">
						��������
					</td>
					<td width="10%">
						<bean:write name="rs" property="csrq"/>
					</td>
				</tr>
				<tr>
					<td  align="center">
						ѧ��
					</td>
					<td>
						<bean:write name="rs" property="xh"/>
					</td>
					<td  align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
					<td  align="center">
						�༶
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
					<td  align="center">
						������ò
					</td>
					<td>
						<bean:write name="rs" property="zzmmmc"/>
					</td>
				</tr>
				<tr>
					<td  align="center" colspan="2">
						�ۺϲ����ɼ��༶����
					</td>
					<td colspan="2">
						<bean:write name="rsCj" property="pm"/>
					</td>
					<td  align="center">
						�س�
					</td>
					<td colspan="3">
						<bean:write name="rs" property="tc"/>
					</td>
				</tr>
				<tr>
					<td height="200"  align="center">
						��<br><br>��<br><br>��<br><br>��
					</td>
					<td colspan="7">
						<bean:write name="bz" />
					</td>
				</tr>
				<tr>
					<td height="100"  align="center">
						��<br>��<br>��<br>��<br>��
					</td>
					<td colspan="7" valign="bottom">
						<div align="center">
							������ǩ�֣����£�
						</div>
						<div align="right">
							&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��
						</div>
					</td>
				</tr>
				<tr>
					<td height="100"  align="center">
						ѧ<br>Ժ<br>��<br>��
					</td>
					<td colspan="7" valign="bottom">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />�쵼ǩ�֣����£�
						</div>
						<div align="right">
							��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��
						</div>
					</td>
				</tr>
				<tr>
					<td height="100" align="center">
						ѧ<br>У<br>��<br>��
					</td>
					<td colspan="7" valign="bottom">
						<div align="center">
							ѧУ�쵼ǩ�֣����£�
						</div>
						<div align="right">
							��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��
						</div>
					</td>
				</tr>
				<tr>
					<td  align="center">
						��<br>ע
					</td>
					<td colspan="7">
						<div align="left">
							1. ����һʽ���ݣ�������д����Ҫ����Դ�ӡ��
						</div>
						<div align="left">
							2. <bean:message key="lable.xsgzyxpzxy" />���༶��д�����
						</div>
					</td>
				</tr>
			</table>
			<div align="right">
				������ڣ� &nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;��
			</div>
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

</html>
