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
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h4>
						<strong>
								<bean:write name='rs' property="nd" />
								&nbsp;��ȿ����������ܻᡰ������ѧ������ѧ���ǼǱ�
						</strong>
							</h4>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td colspan="2" scope="col">
								<div align="center">
									����
								</div>
							</td>
							<td colspan="2" scope="col">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td scope="col" width="13%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td scope="col" width="10%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td colspan="2" scope="col">
								<div align="center">
									���֤��
								</div>
							</td>
							<td colspan="3" scope="col">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									��ѧʱ��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="rxny" />
								</div>
							</td>
							<td>
								<div align="center">
									ѧϰרҵ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									������ò
								</div>
							</td>
							<td width="19%">
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									��ͥסַ
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="jtzz" />
							</td>
							<td colspan="2">
								<div align="center">
									��ͥ�˿���
								</div>
							</td>
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="jtrks" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									��������
									<br />
									(��ͥ���)
								</div>
							</td>
							<td colspan="9">
								<br />
								<logic:empty name="rs" property="sqly">
								<br /><br /><br /><br /><br /><br /><br />
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly">
								<bean:write name='rs' property="sqly" />
								</logic:notEmpty>
								<br />
							</td>
						</tr>
						<tr>
							<td scope="row" width="5%">
								<div align="center">
									ϵ
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
							<td colspan="2">
								<br />
								<br />
								<logic:empty name="rs" property="xyshyj">
								<br /><br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xyshyj">
								<bean:write name='rs' property="xyshyj" />
								</logic:notEmpty>
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
								<div align="center">
									ǩ��&nbsp;&nbsp;��&nbsp;��&nbsp;��
								</div>
							</td>
							<td width="5%">
								<div align="center">
									ϵ
									<br />
									��
									<br />
									��
									<br />
									֯
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="2">
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
								<div align="center">
									ǩ��&nbsp;&nbsp;��&nbsp;��&nbsp;��
								</div>
							</td>
							<td width="5%">
								<div align="center">
									Ժ
									<br />
									У
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
							<td colspan="2">
								<br />
								<br />
								<logic:empty name="rs" property="xxshyj">
								<br /><br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xxshyj">
								<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
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
								<div align="center">
									ǩ��&nbsp;&nbsp;��&nbsp;��&nbsp;��
								</div>
							</td>
							<td width="5%">
								<div align="center">
									Ժ
									<br />
									У
									<br />
									��
									<br />
									��
									<br />
									֯
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td width="19%">
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
								<div align="center">
									ǩ��&nbsp;&nbsp;��&nbsp;��&nbsp;��
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									�������߽̹�ί�ᵳί���
								</div>
							</td>
							<td colspan="4">
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
								<div align="right">
									����&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									�����������ܻ����
								</div>
							</td>
							<td colspan="3">
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
								<div align="right">
									����&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;
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
	</div>
</body>
</html:html>
