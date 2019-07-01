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
	<meta name="Copyright" content="������� zfsoft" />
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
       alert('����ɹ���');
       refresh();
    </script>
		</logic:equal>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã�
				<bean:write name="tips" scope="request" />
			</div>
		</div>
		<table align="center" class="tbstyle" style="width:100%">
			<thead>
				<tr><td colspan="4" align="center">
					ѧ��������Ϣά��
				</td></tr>
			</thead>
			<tr>
				<td align="right">
					ѧ�ţ�
				</td>
				<td>
					<html:text property="xh" name="rs" readonly="true" styleId="xh"></html:text>
				</td>
				<td align="right">
					������
				</td>
				<td>
					<bean:write name="rs" property="xm" />
				</td>
			</tr>
			<tr>
				<td align="right">
					��ϵ�绰1��
				</td>
				<td>
					<html:text property="lxdh1" name="rs" maxlength="25"/>
				</td>
				<td align="right">
					�༶���ƣ�
				</td>
				<td>
					<bean:write name="rs" property="bjmc" />
				</td>
			</tr>
			<tr>
				<td align="right">
					E-mail��
				</td>
				<td>
					<html:text property="email" name="rs" maxlength="25"/>
				</td>
				<td align="right">
					רҵ���ƣ�
				</td>
				<td>
					<bean:write name="rs" property="zymc" />
				</td>
			</tr>
			<tr>
				<td align="right">
					�ֻ����룺
				</td>
				<td>
					<html:text property="sjhm" name="rs" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')"/>
				</td>
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />���ƣ�
				</td>
				<td>
					<bean:write name="rs" property="xymc" />
				</td>
			</tr>
			
			<tr>
				<td align="right">
					�����ţ�
				</td>
				<td>
					<html:text property="ssbh" name="rs" maxlength="8"/>
				</td>
				<td align="right">
					��λ�ţ�
				</td>
				<td>
					<html:text property="cwh" name="rs" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'') "/>
				</td>
			</tr>

		</table>
		<div align="center" class="buttontool">
			<button type="button" class="button2" onclick="xsxxSave('save')">
				����
			</button>
		</div>
	</html:form>
	<logic:equal value="true" name="result">
	<script>
		alert("�����ɹ���");
	</script>
	</logic:equal>
	<logic:equal value="false" name="result">
	<script>
		alert("����ʧ�ܣ�");
	</script>
	</logic:equal>	
</body>
</html:html>
