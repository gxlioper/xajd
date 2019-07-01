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
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td>
					<div align="center">
							<h3>
						<strong>
								����ʦ��<bean:message key="lable.xsgzyxpzxy" />��ͥ��������ѧ���϶������
						</strong>
							</h3> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="4" scope="col" width="4%">
								<div align="center">
									ѧ�����˻������
								</div>
							</td>
							<td colspan="2" scope="col">
								<div align="center">
									����
								</div>
							</td>
							<td scope="col" width="16%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="14%" scope="col">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="10%" scope="col">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="10%" scope="col">
								<div align="center">
									��������
								</div>
							</td>
							<td width="10%" scope="col">
								<div align="center">
									<bean:write name='rs' property="xsrq" />
								</div>
							</td>
							<td width="10%" scope="col">
								<div align="center">
									����
								</div>
							</td>
							<td width="11%" scope="col">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									���֤��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									������ò
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									��ͥ�˾�������
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtrjnsr" />
									&nbsp;Ԫ
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									ϵ
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td>
								<div align="center">
									רҵ
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
							<td>
								<div align="center">
									���
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xslb" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�꼶
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="nj" />
								</div>
							</td>
							<td>
								<div align="center">
									�༶
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td>
								<div align="center">
									��У��ϵ�绰
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									ѧ�����������϶�����
								</div>
							</td>
							<td colspan="9">
								<br />
								<bean:write name='rs' property="sqly" />
								<br />
								<div align="right">
									ѧ��ǩ�֣�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> ��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									&nbsp;&nbsp;
								</div>
								<div align="tight">
									ע��������ϸ���˵����
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row" rowspan="4">
								<div align="center">
									��������
								</div>
							</td>
							<td width="4%" rowspan="4">
								<div align="center">
									�Ƽ�����
								</div>
							</td>
							<td colspan="2">
								A.��ͥ����һ������&nbsp;
								<logic:equal name="rs" property="fdysh" value="һ������">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="һ������">
								��
								</logic:notEqual>
							</td>
							<td rowspan="4">
								<div align="center">
									��
									<br />
									��
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="5" rowspan="4">
								<br />
								<logic:empty name="rs" property="fdyshyj">
								<br /><br /><br />
								</logic:empty>
								<logic:notEmpty name="rs" property="fdyshyj">
								<bean:write name='rs' property="fdyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									����С���鳤ǩ��ǩ�֣�
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> ��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								B.��ͥ��������&nbsp;
								<logic:equal name="rs" property="fdysh" value="����">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="����">
								��
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								C.��ͥ������������&nbsp;
								<logic:equal name="rs" property="fdysh" value="��������">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="��������">
								��
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								D.��ͥ���ò�����&nbsp;
								<logic:equal name="rs" property="fdysh" value="������">
								��
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="������">
								��
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									�϶�����
								</div>
							</td>
							<td>
								<div align="center">
									ϵ���
								</div>
							</td>
							<td colspan="3">
								������С���Ƽ�����ϵ������˺�<br />
								<logic:equal name="rs" property="xysh" value="">
								��&nbsp;ͬ������С�������<br />
								��&nbsp;��ͬ������С�������<br />
								����Ϊ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="">
								<logic:equal name="fdy_xy" value="same">
								��&nbsp;ͬ������С�������<br />
								��&nbsp;��ͬ������С�������<br />
								����Ϊ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
								</logic:equal>
								<logic:equal name="fdy_xy" value="notSame">
								��&nbsp;ͬ������С�������<br />
								��&nbsp;��ͬ������С�������<br />
								����Ϊ<u>&nbsp;<bean:write name='rs' property="xysh" />&nbsp;</u>��
								</logic:equal>
								</logic:notEqual>
								<br />
								<div align="left">
									�������鳤ǩ�֣�
								</div>
								<div align="right">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> ��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<div align="center">
									(�Ӹ�ϵ����֧����)
								</div>
							</td>
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
									<br />
									ѧ��
									<br />
									����
									<br />
									����
									<br />
									����
									<br />
									����
									<br />
									���
								</div>
							</td>
							<td colspan="4">
								��ѧ������ϵ���룬�����������ʵ��<br />
								<logic:equal name="rs" property="xxsh" value="">
								��&nbsp;ͬ�⹤���������С�������<br />
								��&nbsp;��ͬ�⹤���������С�������<br />
								����Ϊ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="">
								<logic:equal name="xy_xx" value="same">
								��&nbsp;ͬ�⹤���������С�������<br />
								��&nbsp;��ͬ�⹤���������С�������<br />
								����Ϊ<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
								</logic:equal>
								<logic:equal name="xy_xx" value="notSame">
								��&nbsp;ͬ�⹤���������С�������<br />
								��&nbsp;��ͬ�⹤���������С�������<br />
								����Ϊ<u>&nbsp;<bean:write name='rs' property="xxsh" />&nbsp;</u>��
								</logic:equal>
								</logic:notEqual>
								<br />
								<div align="left">
									������ǩ�֣�
								</div>
								<div align="right">
									<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u> ��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>��
									&nbsp;&nbsp;
								</div>
								<div align="center">
									(�Ӹǲ��Ź���)
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
