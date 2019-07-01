<%@ page language="java" contentType="text/html;charset=GBK"%>

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
<html:html locale="true">
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="正方软件 zfsoft" />
</head>
<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<base target="_self">
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>

<body>
	<html:form action="/data_search" method="post">
		<input type="hidden" id="realTable" name="realTable"
			value="<bean:write name="realTable" scope="request"/>">
		<input type="hidden" id="tableName" name="tableName"
			value="<bean:write name="tableName" scope="request"/>">
		<input type="hidden" id="pk" name="pk" value="xh">
		<input type="hidden" id="act" name="act" value="xsxxwh">
		<input type="hidden" id="pkValue" name="pkValue" value="">
		<logic:equal name="doResult" value="true">
			<script>
       alert('保存成功！');
       refresh();
    </script>
		</logic:equal>
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：
				<bean:write name="tips" scope="request" />
			</div>
		</div>
		<table align="center" class="tbstyle" style="width:100%">
			<thead>
				<tr><td colspan="4" align="center">
					学生基本信息维护
				</td></tr>
			</thead>
			<tr>
				<td align="right">
					学号：
				</td>
				<td>
					<html:text property="xh" name="rs" readonly="true" styleId="xh"></html:text>
				</td>
				<td align="right">
					姓名：
				</td>
				<td>
					<bean:write name="rs" property="xm" />
				</td>
			</tr>
			<tr>
				<td align="right">
					联系电话1：
				</td>
				<td>
					<html:text property="lxdh1" name="rs" maxlength="25"/>
				</td>
				<td align="right">
					班级名称：
				</td>
				<td>
					<bean:write name="rs" property="bjmc" />
				</td>
			</tr>
			<tr>
				<td align="right">
					E-mail：
				</td>
				<td>
					<html:text property="email" name="rs" maxlength="25"/>
				</td>
				<td align="right">
					专业名称：
				</td>
				<td>
					<bean:write name="rs" property="zymc" />
				</td>
			</tr>
			<tr>
				<td align="right">
					手机号码：
				</td>
				<td>
					<html:text property="sjhm" name="rs" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')"/>
				</td>
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />名称：
				</td>
				<td>
					<bean:write name="rs" property="xymc" />
				</td>
			</tr>
			
			<tr>
				<td align="right">
					宿舍编号：
				</td>
				<td>
					<html:text property="ssbh" name="rs" maxlength="8"/>
				</td>
				<td align="right">
					床位号：
				</td>
				<td>
					<html:text property="cwh" name="rs" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'') "/>
				</td>
			</tr>

		</table>
		<div align="center" class="buttontool">
			<button type="button" class="button2" onclick="xsxxSave('save')">
				保存
			</button>
		</div>
	</html:form>
	<logic:equal value="true" name="result">
	<script>
		alert("操作成功！");
	</script>
	</logic:equal>
	<logic:equal value="false" name="result">
	<script>
		alert("操作失败！");
	</script>
	</logic:equal>	
</body>
</html:html>
