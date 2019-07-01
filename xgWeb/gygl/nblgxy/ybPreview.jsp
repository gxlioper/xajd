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
				<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
		<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/nblgxy_gygl" method="post">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<div align="center">
					<b><bean:write name="rs" property="nf" />��<bean:write name="rs" property="yf" />�·���ס��Ԣ����Ա�����±���</b>
				</div>
				<div align="right">
					�㱨����ʱ��Σ�
					<bean:write name="rs" property="gzksrq" />
					��
					<bean:write name="rs" property="gzjsrq" />
				</div>
				<div align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="15%">
								����Ա����
							</td>
							<td>
								<bean:write name="rs" property="xm" />
							</td>
							<td width="15%">
								��ס¥��
							</td>
							<td>
								<bean:write name="rs" property="rzqsh" />
							</td>
						</tr>
						<tr>
							<td>
								����¥��
							</td>
							<td colspan="3">
								<bean:write name="rs" property="fzld" />
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="5" width="8%">
								<br>
								<br>
								<p align="center">
									��
								</p>
								<p align="center">
									Ҫ
								</p>
								<p align="center">
									��
								</p>
								<p align="center">
									��
								</p>
								<p align="center">
									��
								</p>
								<p align="center">
									��
								</p>
								<br>
								<br>
							</td>
							<td height="60px" title="ÿ��һ�������ղ�ʱ�䡢���ݡ������Ա�������������Ҫ����">
								<bean:write name="rs" property="gznr_jyfk" />
							</td>
						</tr>
						<tr>
							<td height='60px' title="ÿ��ֵ��ʱ���ڽӴ�ѧ����̸�ġ��߷����ҵȵļ�Ҫ��¼">
								<bean:write name="rs" property="jgznr_jyjl" />
							</td>
						</tr>
						<tr>
							<td height='60px' title="�ٿ�¥�㳤��ѧ���Ǹɻ����ʱ�䡢�ص����Ҫ��������">
								<bean:write name="rs" property="gznr_jhynr" />
							</td>
						</tr>
						<tr>
							<td height='60px' title="һ��������Ҫ������¥��ѧ����̬">
								<bean:write name="rs" property="gznr_jxsdt" />
							</td>
						</tr>
						<tr>
							<td height='60px' title="������������">
								<bean:write name="rs" property="gznr_jqtnr" />
							</td>
						</tr>
						<tr>
							<td rowspan="3">
								<br>
								<br>
								<p align="center">
									��Ҫ
								</p>
								<p align="center">
									����
								</p>
								<p align="center">
									����
								</p>
								<p align="center">
									����
								</p>
								<p align="center">
									����
								<p align="center">
									����
								</p>
								<br>
								<br>
							</td>
							<td height='60px'
								title="������ѧ�����������ڵ����ѡ��������顢�ص㹤�������������Ҫ������ͻ���¼�����ȣ�">
								<bean:write name="rs" property="fkyj_xyfk" />
							</td>
						</tr>
						<tr>
							<td height='60px' title="��������Ժ��������ѧ����̬�����ȶ���ͷ�����עѧ���ȣ�ע����Ժ��">
								<bean:write name="rs" property="fkyj_xgbfk" />
							</td>
						</tr>
						<tr>
							<td height='60px' title="����������԰������������ģ����漰�ճ�����������ҪЭͬ�Ĺ�����">
								<bean:write name="rs" property="fkyj_fwzxfk" />
							</td>
						</tr>
						<tr>
							<td align="center">
								��ע
							</td>
							<td>
								<bean:write name="rs" property="bz" />
							</td>
						</tr>
					</table>
				</div>
			</logic:notEmpty>
			<br>
			<br>
			<div class="buttontool noprint" align="center">
				<input type="button" class="button2" value="ҳ������"
					onclick="WebBrowser.ExecWB(8,1);">
				<input type="button" class="button2" value="��ӡԤ��"
					onclick="WebBrowser.ExecWB(7,1)">
				<input type="button" class="button2" value="ֱ�Ӵ�ӡ"
					onclick="WebBrowser.ExecWB(6,6)">
			</div>
		</html:form>
	</body>
</html>
