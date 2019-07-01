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
	<html:form action="lyjszxjsqb.do" method="post">
		<p align="center">
			�й�����
			<bean:write name="rs" property="zhmc" />
		</p>
		<div align="center">
			<p>
				������ѧ���� ��ҵ��������ˣ�����ȷ����
			</p>

			<table width="100%" class="tbstyle" id="theTable">
				<tr>
					<td colspan="7" scope="col">
						<div align="left">
							<strong> ����˻������� </strong>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row" width="13%">
						<div align="right">
							�����������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xm" />
					</td>
					<td width="13%">
						<div align="right">
							�Ա�
						</div>
					</td>
					<td width="17%">
						<bean:write name="rs" property="xb" />
					</td>
					<td width="13%">
						<div align="right">
							�������ڣ�
						</div>
					</td>
					<td width="17%">
						<bean:write name="rs" property="csrq" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							��ҵѧУ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="xxmc" />
					</td>
					<td>
						<div align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xymc" />
					</td>
					<td>
						<div align="right">
							ϵ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xmc" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							���֤���룺
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="sfzh" />
					</td>
					<td>
						<div align="right">
							ѧ����
						</div>
					</td>
					<td>
						<bean:write name="rs" property="xl" />
					</td>
					<td>
						<div align="right">
							��ͥ��ϵ�绰��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtlxdh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							��ͥסַ��
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="jtxxzz" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							����״����
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="hyzk" />
					</td>
					<td>
						<div align="right">
							��ż������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="poxm" />
					</td>
					<td>
						<div align="right">
							��ϵ�绰��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							������λ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="gzdw" />
					</td>
					<td>
						<div align="right">
							��λ�绰��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="dwdh" />
					</td>
					<td>
						<div align="right">
							�������룺
						</div>
					</td>
					<td>
						<bean:write name="rs" property="dwyzbm" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							��λ��ַ��
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="dwdz" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							�ƶ��绰��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="yddh" />
					</td>
					<td>
						<div align="right">
							E-MAIL��ַ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="email" />
					</td>
				</tr>
				<tr>
					<td colspan="7" scope="row">
						<div align="left">
							<strong> ��ϵ�˻������� </strong>
						</div>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							��ϵ��������
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="lxrxm" />
					</td>
					<td>
						<div align="right">
							�Ա�
						</div>
					</td>
					<td>
						<bean:write name="rs" property="lxrxb" />
					</td>
					<td>
						<div align="right">
							�������ڣ�
						</div>
					</td>
					<td>
						<bean:write name="rs" property="lxrcsrq" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							�����˹�ϵ��
						</div>
					</td>
					<td colspan="2">
						<bean:write name="rs" property="lxrgx" />
					</td>
					<td>
						<div align="right">
							��ϵ�绰��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="lxrdh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							��λ /��ַ��
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="lxrdwdz" />
					</td>
				</tr>
				<tr>
					<td colspan="4" scope="row">
						<div align="left">
							<strong> ��ͥ������� </strong>
						</div>
					</td>
					<td colspan="3" rowspan="7">
						<br>
						<br>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;�����ڴ˳�ŵ����������ʵ�������б䶯�����˳�ŵ�ڱ䶯��һ���ڽ��䶯�����ʼ����й�����
							��/֧�С����ṩ������ϻ�δ�ܼ�ʱ���ͱ�����ϣ�������Ȩ�϶�����ΥԼ�����ɰ��ա��й����й�����ѧ�������ͬ���е����Լ��׷�����˵�ΥԼ���Ρ�
						</p>
						<p align="right">
							��ŵ��(ǩ��)��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
						<p align="right">
							��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						</p>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							��ͥ��ϸסַ��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="jtxxzz" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							�绰��
						</div>
					</td>
					<td width="19%">
						<bean:write name="rs" property="jtlxdh" />
					</td>
					<td width="11%">
						<div align="right">
							�ʱࣺ
						</div>
					</td>
					<td>
						<bean:write name="rs" property="jtyzbm" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							����������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="fqxm" />
					</td>
					<td>
						<div align="right">
							ְҵ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="fqzy" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							�������֤��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="fqsfzh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							ĸ��������
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mqxm" />
					</td>
					<td>
						<div align="right">
							ְҵ��
						</div>
					</td>
					<td>
						<bean:write name="rs" property="mqzy" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							ĸ�����֤��
						</div>
					</td>
					<td colspan="3">
						<bean:write name="rs" property="mqsfzh" />
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="right">
							��ע��
						</div>
					</td>
					<td colspan="6">
						<bean:write name="rs" property="bz" />
					</td>
				</tr>
			</table>
		</div>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','�й������Ϻ��з��й�����ѧ�����ҵ��������ˣ�����ȷ����')" />
	</div>
</body>
</html:html>
