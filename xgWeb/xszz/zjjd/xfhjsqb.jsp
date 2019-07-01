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
			document.forms[0].action = "/xgxt/zjjdzyjsxy_xszz.do?method=xfhjsq";
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
								ѧ�ѻ���������
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
							<td width="13%">
								<div align="center">
									����
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td width="13%">
								<div align="center">
									רҵ
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name="rs" property="zymc" />
								</div>
							</td>
							<td width="13%">
								<div align="center">
									�༶
								</div>
							</td>
							<td width="21%">
								<div align="center">
									<bean:write name="rs" property="bjmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�꼶
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="nj" />
								</div>
							</td>
							<td>
								<div align="center">
									ѧ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="xh" />
								</div>
							</td>
							<td>
								<div align="center">
									����ѧ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="hjje" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��
									<br />
									<br />
									��
									<br />
									<br />
									ԭ
									<br />
									<br />
									��
								</div>
							</td>
							<td colspan="5">
								<br />
								<br />
								<logic:empty name="rs" property="hjly">
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
								<logic:notEmpty name="rs" property="hjly">
									<bean:write name="rs" property="hjly" />
								</logic:notEmpty>
								<br />
								<div align="right">
									ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br />
									<logic:empty name="rs" property="sqsj">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="sqsj">
									<bean:write name="rs" property="sqsj" />&nbsp;&nbsp;
									</logic:notEmpty>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									���������
								</div>
							</td>
							<td colspan="5">
								<logic:equal name="rs" property="fdysh" value="δ���">
									��˽����
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="δ���">
									��˽����<bean:write name='rs' property="fdysh" />
								</logic:notEqual>
								<br />
								<logic:empty name="rs" property="fdyshyj">
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="fdyshyj">
									<bean:write name='rs' property="fdyshyj" />
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									ǩ�֣����£�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ϵ���
								</div>
							</td>
							<td colspan="5">
								<logic:equal name="rs" property="xysh" value="δ���">
									��˽����
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="δ���">
									��˽����<bean:write name='rs' property="xysh" />
								</logic:notEqual>
								<br />
								<logic:empty name="rs" property="xyshyj">
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xyshyj">
									<bean:write name='rs' property="xyshyj" />
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									ǩ�֣����£�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									����Ժ�����
								</div>
							</td>
							<td colspan="5">
								<logic:equal name="rs" property="xxsh" value="δ���">
									��˽����
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="δ���">
									��˽����<bean:write name='rs' property="xxsh" />
								</logic:notEqual>
								<br />
								<logic:empty name="rs" property="xxshyj">
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xxshyj">
									<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									����Ժ��ǩ�֣����£�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;����һʽ���ݣ���ϵ�����񴦸�һ�ݣ�ƾ�˱����񴦽ɷѡ�
					</div>
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
