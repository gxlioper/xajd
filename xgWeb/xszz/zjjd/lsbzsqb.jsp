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
	<title><bean:message key="lable.title" />
	</title>
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
			document.forms[0].action = "/xgxt/zjjdzyjsxy_xszz.do?method=lsbzsq";
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
						<strong>
								��ʱ���Ѳ��������
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
					<br />
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="6%" rowspan="4" scope="col">
								<div align="center">
									�������
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td scope="col" width="20%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									��������
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td width="16%" rowspan="4" scope="col">
								<div align="center">
									��Ƭ
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ѧ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td>
								<div align="center">
									��ѧʱ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rxny" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="center">
									<logic:empty name="rs" property="xymc">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="xymc">
										&nbsp;<bean:write name='rs' property="xymc" />&nbsp;
									</logic:notEmpty>
									<bean:message key="lable.xsgzyxpzxy" />
									<logic:empty name="rs" property="zymc">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="zymc">
										&nbsp;<bean:write name='rs' property="zymc" />&nbsp;
									</logic:notEmpty>
									רҵ
									<logic:empty name="rs" property="bjmc">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="bjmc">
										&nbsp;<bean:write name='rs' property="bjmc" />&nbsp;
									</logic:notEmpty>
									�༶
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�Ƿ�������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="sfkns" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="lsbzje" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="7">
								<br />
								<logic:empty name="rs" property="sqly">
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly">
									<bean:write name='rs' property="sqly" />
								</logic:notEmpty>
								<br />
								<div align="right">
									������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									ϵ
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="7">
								<logic:equal name="rs" property="xysh" value="δ���">
									��˽����
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="δ���">
									��˽����<bean:write name='rs' property="xysh" />
								</logic:notEqual>
								<br />
								<br />
								<logic:empty name="rs" property="xyshyj">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xyshyj">
									<bean:write name='rs' property="xyshyj" />
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									ѧ
									<br />
									Ժ
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="7">
								<logic:equal name="rs" property="xxsh" value="δ���">
									��˽����
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="δ���">
									��˽����<bean:write name='rs' property="xxsh" />
								</logic:notEqual>
								<br />
								<br />
								<logic:empty name="rs" property="xxshyj">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xxshyj">
									<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
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
