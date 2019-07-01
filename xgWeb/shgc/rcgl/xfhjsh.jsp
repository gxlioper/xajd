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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script> 
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/bb_shOne" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"><bean:write name="tips" scope="request"/></span>
				</div>
			</div>
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<fieldset>
					<table width="100%" class="tbstyle" id="rsTable">
					   <thead>
						   <tr style="height:22px">
							  <td colspan="4" align="center">
								�������
							  <br></td>
						   </tr>
					   </thead>
					   <tr>
							<td align="right">
								ѧ�ţ�
							<br></td>
							<td align="left">
								<bean:write name="xh" scope="request"/>								
							<br></td>
							<td align="right">
								��ȣ�
							<br></td>
							<td align="left">
								<bean:write name="nd" scope="request"/>
							<br></td>
						</tr>
						<tr>
							<td align="right">
								������
							<br></td>
							<td align="left">
								<bean:write name="xm" scope="request"/>
							<br></td>
							<td align="right">
								ѧ�꣺
							<br></td>
							<td align="left">
								<bean:write name="xn" scope="request"/>
							<br></td>
						</tr>
						<tr>
							<td align="right">
								�Ա�
							<br></td>
							<td align="left">
								<bean:write name="xb" scope="request"/>
							<br></td>
							<td align="right">
								ѧ�ڣ�
							<br></td>
							<td align="left">
								<bean:write name="xq" scope="request"/>
							<br></td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							<br></td>
							<td align="left">
								<bean:write name="nj" scope="request"/>
							<br></td>
							<td align="right">
								�������ޣ�
							<br></td>
							<td align="left">
								<bean:write name="hjqx" scope="request"/>
							<br></td>
							
						</tr>
						<tr>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							<br></td>
							<td align="left">
								<bean:write name="xymc" scope="request"/>
							<br></td>
							<td align="right">
							  ���:
							<br></td>
							<td align="left">
							<html:select property="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<br>
							</td>
						</tr>
						<tr>
							<td align="right">
								רҵ��
							<br></td>
							<td align="left">
								<bean:write name="zymc" scope="request"/>
							<br></td>
							<td align="right">
								�༶��
							<br></td>
							<td align="left">
								<bean:write name="bjmc" scope="request"/>
							<br></td>
						</tr>
						<tr>
							<td align="right">
								����ԭ��
							<br></td>
							<td align="left" colspan="3">
								<bean:write name="bz"/>
							</td>
						</tr>						
					</table>
				</fieldset>
				<div class="buttontool">					
					<button class="button2"
						onclick="refreshForm('/xgxt/bb_shOne.do?doType=save')"
						style="width:80px" id="buttonSave">
						�� ��
					</button>					
				</div>
		</html:form>
	</body>
	<logic:present name="done">
		<logic:equal value="yes" name="done">
			<script type="text/javascript">
	            alert("�����ɹ���");
	            Close();
				window.dialogArguments.document.getElementById('search_go').click();
	        </script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script type="text/javascript">
	            alert("����ʧ�ܣ�");
	             Close();
				window.dialogArguments.document.getElementById('search_go').click();
	        </script>
		</logic:equal>
	</logic:present>
</html>
