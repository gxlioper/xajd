<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
		<title>ѧ��������ϵͳ</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>



	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<fieldset>
			<legend>
				ѧ�����������Ϣ
			</legend>
			<table width="100%" class="tbstyle">
				<tr>
					<td colspan="4">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ѧ�ţ�
						<bean:write name="rs" property="xh" />
					</td>
				</tr>
				<tr>
					<td align="right" style="width:22%">
						������
					</td>
					<td style="width:28%">
						<bean:write name="rs" property="xm" />
					</td>
					<td align="right" style="width:22%">
						�Ա�
					</td>
					<td style="width:28%">
						<bean:write name="rs" property="xb" />
					</td>
				</tr>
				<tr>
					<td align="right">
						�꼶��
					</td>
					<td>
						<bean:write name="rs" property="nj" />
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
				</tr>
				<tr>
					<td align="right">
						רҵ��
					</td>
					<td>
						<bean:write name="rs" property="zymc" />
					</td>
					<td align="right">
						�༶��
					</td>
					<td>
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						���������Ϣ
					</td>
				</tr>
				<tr>
					<td align="right">
						��ͥ��ַ��
					</td>
					<td>
						<bean:write name="rs" property="jtdz" />
					</td>
					<td align="right">
						��ͥ�������룺
					</td>
					<td>
						<bean:write name="rs" property="jtyzbm" />
					</td>
				</tr>


				<tr>
					<td align="right">
						��ͥ��ϵ�绰��
					</td>
					<td>
						<bean:write name="rs" property="jtlxdh" />
					</td>
					<td align="right">
						������λ��
					</td>
					<td>
						<bean:write name="rs" property="gzdw" />
					</td>
				</tr>
				<tr>
					<td align="right">
						������λ��ַ��
					</td>
					<td>
						<bean:write name="rs" property="gzdwdz" />
					</td>
					<td align="right">
						������λ��ϵ�绰��
					</td>
					<td>
						<bean:write name="rs" property="gzdwlxdh" />
					</td>
				</tr>
				<tr>
					<td align="right">
						�ֻ��ţ�
					</td>
					<td>
						<bean:write name="rs" property="sjh" />
					</td>
					<td align="right">
						�����绰��
					</td>
					<td>
						<bean:write name="rs" property="zjdh" />
					</td>
				</tr>

				<tr>
					<td align="right">
						�������䣺
					</td>
					<td>
						<bean:write name="rs" property="email" />
					</td>
					<td align="right">
						QQ�ţ�
					</td>
					<td>
						<bean:write name="rs" property="qq" />
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						��ͬ��Ϣ
					</td>
				</tr>

				<tr>
					<td align="right">
						��ͬ�ţ�
					</td>
					<td>
						<bean:write name="rs" property="hth" />
					</td>
					<td align="right">
						�����ܽ�
					</td>
					<td>
						<bean:write name="rs" property="dkzje" />
					</td>
				</tr>


				<tr>
					<td align="right">
						�ſ���1��
					</td>
					<td>
						<bean:write name="rs" property="fkje1" />
					</td>
					<td align="right">
						�ſ���2��
					</td>
					<td>
						<bean:write name="rs" property="fkje2" />
					</td>
				</tr>
				<tr>
					<td align="right">
						�ſ���3��
					</td>
					<td>
						<bean:write name="rs" property="fkje3" />
					</td>
					<td align="right">
						�ſ���4��
					</td>
					<td>
						<bean:write name="rs" property="fkje4" />
					</td>
				</tr>
				<tr>
					<td align="right">
						�ſ���5��
					</td>
					<td colspan="3">
						<bean:write name="rs" property="fkje5" />
					</td>
				</tr>
				<tr>
					<td align="right">
						ΥԼ��
					</td>
					<td>
						<bean:write name="rs" property="wyje" />
					</td>
					<td align="right">
						ΥԼʱ�䣺
					</td>
					<td>
						<bean:write name="rs" property="wysj" />
					</td>
				</tr>
			</table>
		</fieldset>
	</body>
</html>

