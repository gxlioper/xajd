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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>

	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getTowdays.js'></script>

	<body>
			<fieldset>
				<legend>
					Э���鲹����ϸ��Ϣ
				</legend>
				<table width="98%" id="rsT" class="tbstyle">
					<tr style="height:22px">
						<td align="right" width="15%">
							����:
						</td>
						<td align="left" width="25%">
							<bean:write name="rs" property="xm" />
						</td>
						<td align="right" width="10%">
							�Ա�
						</td>
						<td align="left" width="15%">
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right" width="13%">
							ѧ����
						</td>
						<td align="left" width="25%">
							<bean:write name="rs" property="xl" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							ѧ��:
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xh" />
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��ҵʱ��:
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="bysj" />
						</td>
						<td align="right">
							רҵ��
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="zymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							ԭЭ������:
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="jyxybh" />
						</td>
						<td align="right">
							��Э������:
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="newjyxybh" />
						</td>
					</tr>
					<tr>
						<td align="right">
							����ʱ��
						</td>
						<td align="left" colspan="5">
							<bean:write name="rs" property="sqsj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />���:
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xysh" />
						</td>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />���ʱ��:
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xyshsj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							ѧУ���:
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xxsh" />
						</td>
						<td align="right">
							ѧУ���ʱ��:
						</td>
						<td align="left" colspan="2">
							<bean:write name="rs" property="xxshsj" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							���뱨�棺
						</td>
						<td align="left" colspan="5">
							<html:textarea name="rs" property="sqbg" rows="15"
								style="width:100%" readonly="true" />
						</td>
					</tr>
				</table>
			</fieldset>
	</body>
</html>

