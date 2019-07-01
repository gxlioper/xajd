<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">


	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/data_search" method="post">
			<fieldset>
				<legend>
					���˼�����ϸ��Ϣ
				</legend>
				<table width="100%" class="tbstyle" id="grjl">
					<thead>
						<tr>
							<td colspan="8" align="center">
								���֤�ţ�
								<html:text name="rs" property="id" readonly="true"
									style="width:130px" />
								<html:checkbox name="rs" property="idsee" value="no" />
								(����) &nbsp;&nbsp; ����<bean:message key="lable.xsgzyxpzxy" />��
								<html:text name="rs" property="xymc" readonly="true"
									style="width:100px" />
								&nbsp;&nbsp; ѧ�ţ�
								<html:text name="rs" property="xsxh" readonly="true"
									style="width:80px" />
								&nbsp;&nbsp; ����ʱ�䣺
								<html:text name="rs" property="fbsj" style="width:130px"
									readonly="true" />
							</td>
						</tr>
					</thead>
					<tr>
						<td rowspan="3" align="center" width="3%">
							<b>��<br>��<br>��<br>��</b>
						</td>
						<td align="right">
							������
						</td>
						<td width="100">
							<bean:write name="rs" property="name" />
						</td>
						<td align="right">
							�Ա�
						</td>
						<td width="15%">
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right">
							�������£�
						</td>
						<td width="15%">
							<bean:write name="rs" property="csny" />
						</td>
						<td rowspan="4" align="center" width="9%">
							��Ƭ
						</td>
					</tr>
					<tr>
						<td align="right">
							���壺
						</td>
						<td>
							<bean:write name="rs" property="mz" />
						</td>
						<td align="right">
							ѧ����
						</td>
						<td>
							<bean:write name="rs" property="xl" />
						</td>
						<td align="right">
							������ò��
						</td>
						<td>
							<bean:write name="rs" property="zzmm" />
						</td>
					</tr>
					<tr>
						<td align="right">
							רҵ���ƣ�
						</td>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
						<td align="right">
							����רҵ��
						</td>
						<td>
							<bean:write name="rs" property="fxzymc" />
						</td>
						<td align="right">
							��Դ������
						</td>
						<td>
							<bean:write name="rs" property="sydq" />
						</td>
					</tr>
					<tr>
						<td rowspan="2" align="center">
							<b>��<br>ϵ<br>��<br>��</b>
						</td>
						<td align="right">
							��ϵ��ַ��
						</td>
						<td colspan="2">
							<bean:write name="rs" property="lxdz" />
						</td>
						<td align="right">
							��ϵ�绰��
						</td>
						<td colspan="2">
							<bean:write name="rs" property="lxdh" />
						</td>
					</tr>
					<tr>
						<td align="right">
							�������룺
						</td>
						<td colspan="2">
							<bean:write name="rs" property="yzbm" />
						</td>
						<td align="right">
							�������䣺
						</td>
						<td colspan="3">
							<bean:write name="rs" property="email" />
						</td>
					</tr>
					<tr>
						<td rowspan="5" align="center">
							<b>ѧ<br>��<br>��<br>��<br>��<br>��</b>
						</td>
						<td align="center">
							�����
						</td>
						<td colspan="6">
							<html:textarea name="rs" property="hjqk" rows="4"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							ѧϰ���
						</td>
						<td colspan="6">
							<html:textarea name="rs" property="xxqk" rows="4"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							У����
							<br>
							�Ͻ���
						</td>
						<td colspan="6">
							<html:textarea name="rs" property="xjysjl" rows="4"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							���ʵ
							<br>
							�����
						</td>
						<td colspan="6">
							<html:textarea name="rs" property="shsjqk" rows="4"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							��������
						</td>
						<td colspan="2">
							<html:textarea name="rs" property="gzjl" rows="4"
								style="width=100%" readonly="true" />
						</td>
						<td align="center">
							�����س�
						</td>
						<td colspan="3">
							<html:textarea name="rs" property="grtc" rows="4"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<b>��<br>��<br>��<br>��</b>
						</td>
						<td colspan="7">
							<html:textarea name="rs" property="zwtj" rows="6"
								style="width=100%" readonly="true" />
						</td>
					</tr>
					<tr>
						<td align="center">
							<b>ѧ<br>У<br>��<br>��</b>
						</td>
						<td colspan="7">
							<html:textarea name="rs" property="xxtj" rows="8"
								style="width=100%" readonly="true" />
						</td>
					</tr>
				</table>
			</fieldset>
		</html:form>
		<logic:notEmpty name="notice">
			<logic:equal name="notice" value="no">
				<script>
                       alert("��ѧ������δͨ��ѧУ��ˣ�Ϊ��֤��������ʵ����ʱ�޷��鿴��");
                </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
