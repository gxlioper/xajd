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
<html:html>
<base target="_self">
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="������� zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/n05_xszz.do?method=lsbz2sq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong>��ʱ���������</strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td height="40px">
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;ϵ��<bean:write name='rs' property="xymc" />
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr height="40px">
							<td width="15%">
								<div align="center">
									��&nbsp;&nbsp;��
								</div>
							</td>
							<td width="18%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="15%">
								<div align="center">
									��&nbsp;&nbsp;��
								</div>
							</td>
							<td width="19%">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td width="15%">
								<div align="center">
									ѧ&nbsp;&nbsp;��
								</div>
							</td>
							<td width="18%">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
						</tr>
						<tr height="40px">
							<td>
								<div align="center">
									��ͥסַ
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="jtzz" />
								</div>
							</td>
						</tr>
						<tr height="40px">
							<td>
								<div align="center">
									����ʱ��
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="sqsj" />
								</div>
							</td>
						</tr>
						<tr height="40px">
							<td>
								<div align="center">
									������
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="sqje" />
								</div>
							</td>
						</tr>
						<tr height="320px">
							<td>
								<div align="center">
									��&nbsp;&nbsp;��
									<br />
									<br />
									��&nbsp;&nbsp;��
								</div>
							</td>
							<td colspan="5">
								<br />
								<logic:empty name='rs' property="sqly">
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="sqly">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="sqly" />
								</logic:notEmpty>
								<br /><br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<tr height="80px">
							<td>
								<div align="center">
									������
									<br />
									��&nbsp;��
								</div>
							</td>
							<td colspan="5">
								<br />
								<logic:empty name='rs' property="fdyshyj">
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="fdyshyj">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="fdyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr height="80px">
							<td>
								<div align="center">
									ϵ&nbsp;��
									<br />
									��&nbsp;��
								</div>
							</td>
							<td colspan="5">
								<br />
								<logic:empty name='rs' property="xyshyj">
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xyshyj">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="xyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr height="80px">
							<td>
								<div align="center">
									ѧ����
									<br />
									��&nbsp;��
								</div>
							</td>
							<td colspan="5">
								<br />
								<logic:empty name='rs' property="xxshyj">
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xxshyj">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr height="80px">
							<td>
								<div align="center">
									��ע
								</div>
							</td>
							<td colspan="5">
								<br /><br />&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="����" onclick="back();" />
	</div>
</body>
</html:html>
