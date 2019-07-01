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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var isXX = document.getElementById('isXX').value;
			var xxsh = document.getElementById('xxsh').value;
			if(("ͨ��" == xxsh) && (isXX != "is")){
				alert("ѧУ�����ͨ�����������޸���˽��!");
	          	return false;
			}
			 refreshForm('/xgxt/auditing_hzzy_lstdsq_one.do?actDo=save');Close();window.dialogArguments.document.getElementById('search_go').click();
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ������ - ��� - ��ɫͨ����� - �������
				</div>
			</div>
			<input type="hidden" name="pkVal" value="<bean:write name="pk"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" name="tName" value="<bean:write name="tName" />" />
			<input type="hidden" id="xxsh" name="xxsh" value="<bean:write name="rs" property="xxsh" />" />
			<input type="hidden" id="isXX" name="isXX" value="<bean:write name="isXX" />" />
			<table width="98%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right"  width="14%">
						ѧ��
					</td>
					<td align="left" width="36%">
						<bean:write name="rs" property="xh" />
					</td>
					<td width="14%">
						<div align="right">
							����
						</div>
					</td>
					<td width="36%" align="left">
						<bean:write name="rs" property="xm" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td>
						<div align="right">
							�༶
						</div>
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							Ƿ��ԭ��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="qfyy" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							�Ƚ���ѧ��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xjnxf" />
						&nbsp;&nbsp;&nbsp;&nbsp;(��д)&nbsp;&nbsp;
						<bean:write name="rs" property="xjnxf_dx" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							�Ƚ��ɴ��ܷ�
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="xjndgf" />
						&nbsp;&nbsp;&nbsp;&nbsp;(��д)&nbsp;&nbsp;
						<bean:write name="rs" property="xjndgf_dx" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							�ϼ�
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="hj" />
						&nbsp;&nbsp;&nbsp;&nbsp;(��д)&nbsp;&nbsp;
						<bean:write name="rs" property="hj_dx" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							���ಿ�ֻ��ʽ�ͻ���ʱ��
						</div>
					</td>
					<td colspan="3" align="left">
						<bean:write name="rs" property="qybfhkfshsj" />
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							����ʱ��
						</div>
					</td>
					<td align="left">
						<bean:write name="rs" property="sqsj" />
					</td>
					<td>
						<div align="right">
							��˽��
						</div>
					</td>
					<td>
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="yz()" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
	</body>
</html>
