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
		function yz(){
			document.forms[0].action = "/xgxt/ycws_xszz.do?method=zzsjEdit&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��������ά�� - ��Ŀ����ά��
		</div>
	</div>
	<html:form action="/ycws_xszz.do?method=zzsjEdit" method="post">

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<div align="center">
		<table class="tbstyle" width="90%">
			<tr>
				<td width="20%">
					<div align="right">
						�����϶���ɾ��
					</div>
				</td>
				<td width="30%">
					<div align="center">
						<logic:present name="rs" property="knrdzsg">
							<logic:match value="1" name="rs" property="knrdzsg">
								<input type="radio" name="knrdzsg" value="1" checked>&nbsp;��
							    <input type="radio" name="knrdzsg" value="0">&nbsp;��
							</logic:match>
							<logic:match value="0" name="rs" property="knrdzsg">
								<input type="radio" name="knrdzsg" value="1">&nbsp;��
							    <input type="radio" name="knrdzsg" value="0" checked>&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="knrdzsg">
							<input type="radio" name="knrdzsg" value="1" checked>&nbsp;��
							<input type="radio" name="knrdzsg" value="0">&nbsp;��
						</logic:notPresent>
					</div>
				</td>
				<td width="20%">
					<div align="right">
						������ɾ��
					</div>
				</td>
				<td width="30%">
					<div align="center">
						<logic:present name="rs" property="zzzsg">
							<logic:match value="1" name="rs" property="zzzsg">
								<input type="radio" name="zzzsg" value="1" checked>&nbsp;��
							    <input type="radio" name="zzzsg" value="0">&nbsp;��
							</logic:match>
							<logic:match value="0" name="rs" property="zzzsg">
								<input type="radio" name="zzzsg" value="1">&nbsp;��
							    <input type="radio" name="zzzsg" value="0" checked>&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="zzzsg">
							<input type="radio" name="zzzsg" value="1" checked>&nbsp;��
							<input type="radio" name="zzzsg" value="0">&nbsp;��
						</logic:notPresent>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						�����϶�����
					</div>
				</td>
				<td>
					<div align="center">
						<logic:present name="rs" property="knrddr">
							<logic:match value="1" name="rs" property="knrddr">
								<input type="radio" name="knrddr" value="1" checked>&nbsp;��
							    <input type="radio" name="knrddr" value="0">&nbsp;��
							</logic:match>
							<logic:match value="0" name="rs" property="knrddr">
								<input type="radio" name="knrddr" value="1">&nbsp;��
							    <input type="radio" name="knrddr" value="0" checked>&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="knrddr">
							<input type="radio" name="knrddr" value="1" checked>&nbsp;��
							<input type="radio" name="knrddr" value="0">&nbsp;��
						</logic:notPresent>
					</div>
				</td>
				<td>
					<div align="right">
						��������
					</div>
				</td>
				<td>
					<div align="center">
						<logic:present name="rs" property="zzdr">
							<logic:match value="1" name="rs" property="zzdr">
								<input type="radio" name="zzdr" value="1" checked>&nbsp;��
							    <input type="radio" name="zzdr" value="0">&nbsp;��
							</logic:match>
							<logic:match value="0" name="rs" property="zzdr">
								<input type="radio" name="zzdr" value="1">&nbsp;��
							    <input type="radio" name="zzdr" value="0" checked>&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="zzdr">
							<input type="radio" name="zzdr" value="1" checked>&nbsp;��
							<input type="radio" name="zzdr" value="0">&nbsp;��
						</logic:notPresent>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						�����϶�����
					</div>
				</td>
				<td>
					<div align="center">
						<logic:present name="rs" property="knrddc">
							<logic:match value="1" name="rs" property="knrddc">
								<input type="radio" name="knrddc" value="1" checked>&nbsp;��
							    <input type="radio" name="knrddc" value="0">&nbsp;��
							</logic:match>
							<logic:match value="0" name="rs" property="knrddc">
								<input type="radio" name="knrddc" value="1">&nbsp;��
							    <input type="radio" name="knrddc" value="0" checked>&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="knrddc">
							<input type="radio" name="knrddc" value="1" checked>&nbsp;��
							<input type="radio" name="knrddc" value="0">&nbsp;��
						</logic:notPresent>
					</div>
				</td>
				<td>
					<div align="right">
						��������
					</div>
				</td>
				<td>
					<div align="center">
						<logic:present name="rs" property="zzdc">
							<logic:match value="1" name="rs" property="zzdc">
								<input type="radio" name="zzdc" value="1" checked>&nbsp;��
							    <input type="radio" name="zzdc" value="0">&nbsp;��
							</logic:match>
							<logic:match value="0" name="rs" property="zzdc">
								<input type="radio" name="zzdc" value="1">&nbsp;��
							    <input type="radio" name="zzdc" value="0" checked>&nbsp;��
							</logic:match>
						</logic:present>
						<logic:notPresent name="rs" property="zzdc">
							<input type="radio" name="zzdc" value="1" checked>&nbsp;��
							<input type="radio" name="zzdc" value="0">&nbsp;��
						</logic:notPresent>
					</div>
				</td>
			</tr>
		</table>
		<br /><br />
			<button type="button" class="button2" onClick="yz();">
				��&nbsp;&nbsp;&nbsp;&nbsp;��
			</button>
			<button type="button" class="button2" onClick="document.forms[0].submit()">
				ȡ&nbsp;&nbsp;&nbsp;&nbsp;��
			</button>
		</div>
	</html:form>
</body>
</html:html>
