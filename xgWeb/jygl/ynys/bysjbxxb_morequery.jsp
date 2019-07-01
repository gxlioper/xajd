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
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	
  
	</script>
	<body>
		<table width="100%" id="rsT" class="tbstyle">
			<thead>
				<tr style="height:25px">
					<td colspan="4" align="center">
						<b>��ҵ��������Ϣ</b>
					</td>
				</tr>
			</thead>
			<tr style="height:22px">
				<td align="right" width="15%">
					ѧ�ţ�
				</td>
				<td align="left" width="35%">
					<html:text property="xsxh" name="rs" styleId="xsxh" readonly="true"
						style="width:210px" />
				</td>
				<td align="right" width="15%">
					������
				</td>
				<td align="left" width="35%">
					<html:text name="rs" property="name" readonly="true"
						style="width:210px" />
				</td>
			</tr>
			<tr style="height:22px">
				<td align="right">
					�Ա�
				</td>
				<td align="left">
					<bean:write name="rs" property="xb" />
				</td>
				<td align="right">
					�꼶��
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
			</tr>
			<tr style="height:22px">
				<td align="right">
					רҵ��
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
				<td align="right">
					��Դ�أ�
				</td>
				<td align="left">
					<bean:write name="rs" property="sydq" />
				</td>
			</tr>
			<tr style="height:22px">
				<td align="right">
					�������ڣ�
				</td>
				<td align="left">
					<bean:write name="rs" property="csrq" />
				</td>
				<td align="right">
					���壺
				</td>
				<td align="left">
					<bean:write name="rs" property="mz" />
				</td>
			</tr>
			<tr style="height:22px">
				<td align="right">
					������ò��
				</td>
				<td align="left">
					<bean:write name="rs" property="zzmm" />
				</td>
				<td align="right">
					��ϵ�绰��
				</td>
				<td align="left">
					<bean:write name="rs" property="lxdh" />
				</td>
			</tr>
			<tr style="height:22px">
				<td align="right">
					��ѧʱ�䣺
				</td>
				<td align="left">
					<bean:write name="rs" property="nd" />
				</td>
				<td align="right">
					��ҵ��ȣ�
				</td>
				<td align="left">
					<bean:write name="rs" property="bynd" />
				</td>
			</tr>
			<tr style="height:22px">
				<td align="right">
					��ҵ��ʽ��
				</td>
				<td align="left">
					<bean:write name="rs" property="jyfs" />
				</td>
				<td align="right">
					��ҵ����
				</td>
				<td align="left">
					<bean:write name="rs" property="jyyx" />
				</td>
			</tr>
			<tr style="height:22px">
				<td align="right">
					Ƿ�������
				</td>
				<td align="left" colspan="3">
					<html:textarea name="rs" property="qkqk" rows="4" readonly="true"
						style="width:100%" />
				</td>

			</tr>
			<tr>
				<td align="right">
					���������
				</td>
				<td align="left" colspan="3">
					<html:textarea name="rs" property="jcqk" rows="4" readonly="true"
						style="width:100%" />
				</td>
			</tr>
		</table>
	</body>
</html>
