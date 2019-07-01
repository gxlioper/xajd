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
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/pjpycqkjjxjsh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��������� - ��� - ��ѧ����� - ����Ա���
				</div>
			</div>
			<input type="hidden" name="pkVal"
				value="<bean:write name="xn||nd||xh||jxjdm"/>" />
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							������ѧ�����
						</td>
					</tr>
				</thead>
				<tr style="height:22px" >
					<td align="right" nowrap="true">
						ѧ�ţ�
					</td>
					<td align="left" id="selxh">
						<bean:write name="XH" />
					</td>
					<td align="right">
						��ȣ�
					</td>
					<td align="left">
						<bean:write name="ND" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name="XM" />
					</td>
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<bean:write name="XN" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name="XB" />
					</td>
					<td align="right">
						��ѧ��
					</td>
					<td align="left">
						<bean:write name="JXJMC" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<bean:write name="NJ" />
					</td>
					<td align="right">
						�³ɼ���
					</td>
					<td align="left">
						<bean:write name='DCJ' />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<bean:write name="XYMC" />
					</td>
					<td align="right">
						�ǳɼ���
					</td>
					<td align="left">
						<bean:write name="ZCJ" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<bean:write name="ZYMC" />
					</td>
					<td align="right">
						��ɼ���
					</td>
					<td align="left">
						<bean:write name="TCJ" />
					</td>
				</tr>

				<tr style="height:22px">
					<td align="right">
						�ɼ����Σ�
					</td>
					<td align="left">
						<bean:write name="cjmc" />
					</td>
					<td align="right">
						�ۺ��������Σ�
					</td>
					<td align="left">
						<bean:write name="zhpfmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<bean:write name="BJMC" />
					</td>
					<td align="right">
						��ˣ�
					</td>
					<td align="left">
						<html:select name="pjpyForm" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" nowrap="true">
						����ְ��
					</td>
					<td align="left" colspan="3">
						<bean:write name="DRZW" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" nowrap="true">
						������Ŀ��
					</td>
					<td colspan="7">
						<textarea name='kyxm' style="width:99%" rows='5' type="_moz">
								<bean:write name="KYCG" />
						</textarea>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right" nowrap="true">
						�������ɣ�
					</td>
					<td align="left" colspan="3">
						<bean:write name="SQLY" />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="refreshForm('savefdysh.do');"
					style="width:90px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:90px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("�����ɹ�!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
