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
</script>
<body>
	<html:form action="/zzlgdx_rcsw" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong> <bean:write name='rs' property="xxmc" />ѧ����˽����������������</strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<br />
					<br />
					&nbsp;
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="20%" height="20px">
								<div align="center">
									����������
								</div>
							</td>
							<td width="30%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="16%" rowspan="2">
								<div align="center">
									��ϵ��ʽ
								</div>
							</td>
							<td width="14%%">
								<div align="center">
									����
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="lxfs_br" />
								</div>
							</td>
						</tr>
						<tr>
							<td height="20px">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td>
								<div align="center">
									�ҳ�
								</div>
							</td>
							<td>
								<bean:write name='rs' property="lxfs_jz" />
							</td>
						</tr>
						<tr>
							<td rowspan="2">
								<div align="center">
									����ȥ��
								</div>
							</td>
							<td rowspan="2">
								<div align="center">
									<bean:write name='rs' property="sqqx" />
								</div>
							</td>
							<td height="20px">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
						</tr>
						<tr>
							<td height="20px">
								<div align="center">
									�༶
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
						</tr>
						<tr height="40px">
							<td>
								<div align="center">
									��������������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="cgrq" />
								</div>
							</td>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="fhrq" />
								</div>
							</td>
						</tr>
						<tr height="400px">
							<td colspan="5">
								&nbsp;�������ɣ�
								<br /><br />
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
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="sqly">
									<bean:write name='rs' property="sqly" />
								</logic:notEmpty>
								<br />
								<div align="right">
									������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									���������ڣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<tr height="80px">
							<td colspan="2">
								&nbsp;����Ա���
								<br /><br /><br /><br /><br /><br /><br />
								<div align="right">
									ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									���ڣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
							<td colspan="3">
								&nbsp;<bean:message key="lable.xsgzyxpzxy" />������ֹ�ѧ�����������ˡ���ѧ��Ժ����<br /><br />
								<logic:empty name='rs' property="xyshyj">
									<br /><br /><br /><br /><br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xyshyj">
									<bean:write name='rs' property="xyshyj" />
								</logic:notEmpty>
								<div align="right">
									ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									���ڣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr height="80px">
							<td colspan="2">
								&nbsp;�������
								<br /><br /><br /><br /><br /><br /><br />
								<div align="right">
									ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									���ڣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
							<td colspan="3">
								&nbsp;ѧ�������<br /><br />
								<logic:empty name='rs' property="xxshyj">
									<br /><br /><br /><br /><br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xxshyj">
									<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
								<div align="right">
									ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									���ڣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
