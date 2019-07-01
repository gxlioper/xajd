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

		<meta name="Copyright" content="正方软件 zfsoft" />
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
	<script language="javascript">

	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>

		<fieldset>
			<legend>
				就业协议
			</legend>
			<table width="100%" id="rsT" class="tbstyle">
				<tr style="height:22px" align="center">
					<td rowspan="4" width="5%">
						学
						<br>
						生
						<br>
						情
						<br>
						况
					</td>
					<td width="12%">
						姓名
					</td>
					<td>
						<bean:write name="rs" property="xm" />
					</td>
					<td width="8%">
						性别
					</td>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<td width="12%">
						年龄
					</td>
					<td width="15%">
						<bean:write name="rs" property="nl" />
					</td>
					<td width="5%">
						民族
					</td>
					<td>
						<bean:write name="rs" property="mz" />
					</td>
				</tr>
				<tr style="height:22px" align="center">
					<td>
						政治面貌
					</td>
					<td>
						<bean:write name="rs" property="zzmm"  />
					</td>
					<td>
						培养方式
					</td>
					<td colspan="2">
						<bean:write name="rs" property="pyfs"/>
					</td>
					<td>
						学历
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xl" />
					</td>
				</tr>
				<tr align="center">
					<td>
						专业
					</td>
					<td colspan="3">
						<bean:write name="rs" property="zymc" />
					</td>
					<td>
						学制
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xz"  />
					</td>
				</tr>
				<tr align="center">
					<td>
						家庭地址
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtdz" />
					</td>
					<td>
						联系电话
					</td>
					<td colspan="3">
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr>
					<td colspan="9">
						&nbsp;
					</td>
				</tr>
			</table>
			<table class="tbstyle" width="100%">
				<tr align="center">
					<td rowspan="5" width="5%">
						用
						<br>
						人
						<br>
						单
						<br>
						位
						<br>
						情
						<br>
						况
					</td>
					<td width="10%">
						单位名称
					</td>
					<td colspan="2">
						<bean:write name="rs" property="dwmc"  />
					</td>
					<td width="15%">
						单位隶属
					</td>
					<td colspan="2">
						<bean:write name="rs" property="dwls"  />
					</td>
				</tr>
				<tr align="center">
					<td>
						联系人
					</td>
					<td width="17%">
						<bean:write name="rs" property="dwlxr"  />
					</td>
					<td width="10%">
						联系电话
					</td>
					<td>
						<bean:write name="rs" property="dwlxdh" />
					</td>
					<td width="12%">
						邮政编码
					</td>
					<td>
						<bean:write name="rs" property="dwyb"  />
					</td>
				</tr>
				<tr align="center">
					<td>
						通讯地址
					</td>
					<td colspan="3">
						<bean:write name="rs" property="dwdz"  />
					</td>
					<td>
						所有制性质
					</td>
					<td align="left">
						<bean:write name="rs" property="dwxz"  />
					</td>
				</tr>
				<tr>
					<td>
						单位性质
					</td>
					<td colspan="5">
						<bean:write name="rs" property="hyfl" />
					</td>
				</tr>
				<tr>
					<td align="center">
						档案转寄
						<br>
						详细地址
					</td>
					<td colspan="5">
						<bean:write name="rs" property="dayjdz"  />
					</td>
				</tr>
			</table>
			<br>
		</fieldset>
	</body>
</html>
