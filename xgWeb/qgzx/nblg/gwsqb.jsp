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
				<logic:equal name="gwxz" value="gdgw">
					<font size="5"><strong><bean:write name="xxmc"/>�̶��ڹ���ѧ��λ����� </strong></font>
				</logic:equal>
				<logic:equal name="gwxz" value="lsgw">
					<font size="5"><strong><bean:write name="xxmc"/>��ʱ�ڹ���ѧ��λ����� </strong></font>
				</logic:equal>		
			</p>
			<br>
			
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				�������ƣ�<bean:write name="rs" property="bmmc"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				��λ���ƣ�<bean:write name="rs" property="gwdm"/> 
			
			<table cellspacing="0" cellpadding="0" class="tbstyle" align="center">
				<tr height="32">
					<td align="center" width="100">
							���뵥λ
					</td>
					<td align="center" width="200">
							<bean:write name="rs" property="sqdw"/>
					</td>
					<td align="center" width="100">
							����ʱ��
					</td>
					<td align="center" width="200">
							<bean:write name="rs" property="year"/>-<bean:write name="rs" property="month"/>-<bean:write name="rs" property="day"/>
					</td>
				</tr>
				<tr height="32">
					<td align="center">
						<p align="center">
							������
						</p>
					</td>
					<td align="center">
							<bean:write name="rs" property="fzr"/>
					</td>
					<td align="center">
							��ϵ�绰
					</td>
					<td align="center">
							<bean:write name="rs" property="lxdh"/>
					</td>
				</tr>
				<tr height="32">
					<td align="center">
							��������
					</td>
					<td>
							<bean:write name="rs" property="gznr"/>
					</td>
					<td align="center">
							��λ����
					</td>
					<td align="center">
							<bean:write name="rs" property="xyrs"/>
					</td>
				</tr>
				<logic:equal name="gwxz" value="lsgw">
					<tr height="32">
						<td align="center">
								�Ƿ���ѧ�����ڹ�<br>
								��ѧ���Ĵ�Ϊ��Ƹ
						</td>
						<td align="center" colspan="3">
							<bean:write name="rs" property="dwzp"/>
						</td>
					</tr>
					<tr>
						<td align="center">
								��Ա��ʵ���
						</td>
						<td colspan="3">
							<bean:write name="rs" property="rylsqk"/>
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="gwxz" value="gdgw">
					<tr height="32">
						<td align="center">
								ÿ���ڹ���ѧʱ��
						</td>
						<td align="center" colspan="3">
							<bean:write name="rs" property="myqgzxsj"/>
						</td>
					</tr>
					<tr height="32">
						<td align="center">
								����Ҫ��
						</td>
						<td align="center" colspan="3">
							<bean:write name="rs" property="tsyq"/>
						</td>
					</tr>
					<tr height="32">
						<td align="center">
								��Ƹ����ʱ��
						</td>
						<td align="center" colspan="3">
							<bean:write name="rs" property="mssj"/>
						</td>
					</tr>
					<tr height="32">
						<td align="center" >
								�Ƿ���ѧ�����ڹ�<br>
								��ѧ���Ĵ�Ϊ��Ƹ<br>
								(�����Ա��ȷ����<br>
								��ע����
						</td>
						<td colspan="3" valign="top">
							<bean:write name="rs" property="dwzp"/>
							<logic:present name="rs" property="rylsqk">
								 <br>
								(<bean:write name="rs" property="rylsqk"/>)
							</logic:present>
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td align="center">
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
					<td colspan="3" valign="top">
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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							������ǩ�������£�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��
						</p>
					</td>
				</tr>
				<tr>
					<td align="center">
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
					<td colspan="3" valign="top">
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
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							������ǩ�������£�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							��&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;��
						</p>
					</td>
				</tr>
				<tr>
					<td align="center">
						<p align="center">
							��
						</p>
						<p align="center">
							ע
						</p>
					</td>
					<td colspan="3" valign="top">
						<p>
							<bean:write name="rs" property="bz"/>
						</p>
					</td>
				</tr>
			</table>
			<p align="left">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ע������һʽ���ݣ�һ�������뵥λ���棬һ�ݽ�ѧ����������
			</p>
		</html:form>
	</body>
</html>
