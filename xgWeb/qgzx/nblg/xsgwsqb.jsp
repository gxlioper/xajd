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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<base target="_self">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<script language="javascript" src="js/function.js"></script>
	</head>
	<body>

		<html:form action="/qgzxNblg" method="post">
			<p align="center">
				<strong><bean:write name="xxmc"/>ѧ���ڹ���ѧ��λ����� </strong>
			</p>
			<p align="center">
				<strong>&nbsp; </strong>
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				���ڷ�Ժ��<bean:write name="rs" property="xymc"/> ����ʱ�䣺<bean:write name="rs" property="year"/>-<bean:write name="rs" property="month"/>-<bean:write name="rs" property="day"/>
			</p>
			<table cellspacing="0" cellpadding="0" class="tbstyle" align="center">
				<tr>
					<td width="72">
						<p align="center">
							�� ��
						</p>
					</td>
					<td width="84">
						<p align="center">
							<bean:write name="rs" property="xm"/>
						</p>
					</td>
					<td width="72">
						<p align="center">
							ѧ ��
						</p>
					</td>
					<td width="120">
						<p align="center">
							<bean:write name="rs" property="xh"/>
						</p>
					</td>
					<td width="72">
						<p align="center">
							�� ��
						</p>
					</td>
					<td width="132">
						<p>
							<bean:write name="rs" property="xb"/>
						</p>
					</td>
				</tr>
				<tr>
					<td width="72">
						<p align="center">
							�� ��
						</p>
					</td>
					<td width="84">
						<p align="center">
							<bean:write name="rs" property="ssbh"/>
						</p>
					</td>
					<td width="72">
						<p align="center">
							�� ϵ
						</p>
						<p align="center">
							�� ʽ
						</p>
					</td>
					<td width="120">
						<p align="center">
							<bean:write name="rs" property="lxdh"/>
						</p>
					</td>
					<td width="72">
						<p align="center">
							�Ƿ�μ�������λ
						</p>
					</td>
					<td width="132">
						<p align="center">
							<bean:write name="rs" property="sfcjqggw"/>
						</p>
					</td>
				</tr>
				<tr>
					<td width="72">
						<p align="center">
							ר ҵ
						</p>
						<p align="center">
							�� ��
						</p>
					</td>
					<td width="276" colspan="3">
						<p align="center">
							<bean:write name="rs" property="zymc"/><bean:write name="rs" property="bjmc"/>
						</p>
					</td>
					<td width="72">
						<p align="center">
							�� ��
						</p>
						<p align="center">
							������
						</p>
					</td>
					<td width="132">
						<p align="center">
							<bean:write name="rs" property="sfpkf"/>
						</p>
					</td>
				</tr>
				<tr>
					<td width="156" colspan="2">
						<p align="center">
							�����ڹ���ѧ��λ����
						</p>
					</td>
					<td width="396" colspan="4">
						<p align="center">
							<bean:write name="rs" property="gwmc"/>
						</p>
					</td>
				</tr>
				<tr>
					<td width="156" colspan="2">
						<p align="center">
							�ɲμ��ڹ���ѧʱ��
						</p>
					</td>
					<td width="396" colspan="4">
						<p align="center">
							<bean:write name="rs" property="kcjqgzxsj"/>
						</p>
					</td>
				</tr>
				<tr>
					<td width="156" colspan="2">
						<p align="center">
							ũ �� �� ��
						</p>
					</td>
					<td width="396" colspan="4">
						<p align="center">
							<bean:write name="rs" property="kh"/>
						</p>
					</td>
				</tr>
				<tr>
					<td width="72">
						<p align="center">
							��
						</p>
						<p align="center">
							��
						</p>
						<p align="center">
							��
						</p>
						<p align="center">
							λ
						</p>
						<p align="center">
							��
						</p>
						<p align="center">
							��
						</p>
					</td>
					<td width="480" colspan="5" valign="top">
						<p>
							�Ƿ�ͬ��Ƹ�ã�
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							ǩ�������£��� �� �� ��
						</p>
					</td>
				</tr>
				<tr>
					<td width="72">
						<p align="center">
							��
						</p>
						<p align="center">
							Ժ
						</p>
						<p align="center">
							��
						</p>
						<p align="center">
							��
						</p>
					</td>
					<td width="480" colspan="5" valign="top">
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							ǩ�������£��� �� �� ��
						</p>
					</td>
				</tr>
				<tr>
					<td width="72">
						<p align="center">
							ѧ
						</p>
						<p align="center">
							Ժ
						</p>
						<p align="center">
							ѧ
						</p>
						<p align="center">
							��
						</p>
						<p align="center">
							��
						</p>
						<p align="center">
							��
						</p>
						<p align="center">
							��
						</p>
					</td>
					<td width="480" colspan="5" valign="top">
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							ǩ�������£��� �� �� ��
						</p>
					</td>
				</tr>
				<tr>
					<td width="72">
						<p align="center">
							��
						</p>
						<p align="center">
							ע
						</p>
					</td>
					<td width="480" colspan="5" valign="top">
						<p>
							<bean:write name="rs" property="bz"/>
						</p>
					</td>
				</tr>
			</table>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				ע���š��˱�һʽ���ݣ�һ��ѧ����������һ�����˵�λ������
			</p>
			<p>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				�ơ�ѧ��ƾ�˱����˵�λ������
			</p>
		</html:form>
	</body>
</html>
