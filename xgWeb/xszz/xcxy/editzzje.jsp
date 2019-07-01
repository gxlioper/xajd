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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(titName){
			var zzje = document.getElementById('zzje').value;
			if(zzje == null || zzje == ''){
				alert("��������Ϊ��!");
				return false;
			}
			document.forms[0].action = "/xgxt/xcxyXszz.do?method=zzjeEdit&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��������ά�� - �������
		</div>
	</div>
		<html:form action="xcxyXszz.do?method=zzjeEdit&act=save" method="post">

			<logic:present name="save">
				<logic:match value="yes" name="save">
					<script language="javascript">
	         			alert("����ɹ���");
	         		</script>
				</logic:match>
				<logic:match value="no" name="save">
					<script language="javascript">
	         			alert("����ʧ�ܣ�");
	         		</script>
				</logic:match>
			</logic:present>
			<logic:present name="exist">
				<logic:match value="yes" name="exist">
					<script language="javascript">
	         			alert("����Ŀ�Ľ���Ѵ��ڣ�");
	         		</script>
				</logic:match>
			</logic:present>
			<input type="hidden" id="doType" name="doType"
				value="<bean:write name="doType" scope="request"/>" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="pk" scope="request"/>" />
			<table class="tbstyle" width="100%">
				<tr>
					<td width="30%">
						<div align="right">
							������Ŀ����:
						</div>
					</td>
					<td width="70%">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="xmdm" style="width:180px" styleId="xmdm">
								<html:options collection="zzxmList" property="xmdm" labelProperty="xmmc"/>
							</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<input type="hidden" name="temp" value="<bean:write name="rs" property="xmdm"/>">
							<html:select name="rs" property="xmdm" style="width:180px" disabled="true" styleId="xmdm">
								<html:options collection="zzxmList" property="xmdm" labelProperty="xmmc" />
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<td>
						<div align="right">
							������Ŀ���:
						</div>
					</td>
					<td>
						<input type="text" id="zzje" name="zzje" maxlength="8" style="width:90%;heigh:100%"
							value="<bean:write name="rs" property="zzje"/>" onkeyup="checkInputData(this)">&nbsp;Ԫ
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2"
					onClick="yz();">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
				<button type="button" class="button2"
					onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
			</div>
		</html:form>
</body>
</html:html>
