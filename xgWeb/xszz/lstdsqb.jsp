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
	<html:form action="lyjszxjsqb.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<p align="center" style="font-size:24px">
			<bean:write name="sqlb" />
			�����
		</p>

		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<td width="65" height="35" nowrap>
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="93">
					<p align="center">
						<bean:write name="rs" property="bjmc" />
					</p>
				</td>
				<td colspan="3">
					<p align="center">
						ѧ ��
					</p>
				</td>
				<td width="120">
					<p align="center">
						<bean:write name="rs" property="xh" />
					</p>
				</td>
				<td width="97">
					<p align="center">
						�� ��
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name="rs" property="xm" />
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="79">
					<p align="center">
						<bean:write name="rs" property="xb" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="65" height="35">
					<p align="center">
						ר ҵ
					</p>
				</td>
				<td colspan="6">
					<p align="center">
						<bean:write name="rs" property="xmc" />
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						��ϵ�绰
					</p>
				</td>
				<td colspan="3">
					<p align="center">
						<bean:write name="rs" property="lxdh" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="65" height="35">
					<p align="center">
						���֤��
					</p>
				</td>
				<td colspan="6">
					<p align="center">
						<bean:write name="rs" property="sfzh" />
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						��ͥ��ϵ�绰
					</p>
				</td>
				<td colspan="3">
					<p align="center">
						<bean:write name="rs" property="jtlxdh" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="65" height="35">
					<p align="center">
						��ͥסַ
					</p>
				</td>
				<td colspan="6">
					<p align="center">
						<bean:write name="rs" property="jtzz" />
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						��ϵ�绰
					</p>
				</td>
				<td colspan="3">
					<p align="center">
						<bean:write name="rs" property="lxdh" />
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="12" valign="middle">
					<p>
						�����������ɣ�
					</p>
					<bean:write name="rs" property="sqly" />
					
					<p>
						<strong>���˱�֤��������������ʵ�����类��׼��ɫͨ�����ҽ���������ѧ���ʽ֧����ѧ��ѧ�ѡ� </strong>
					</p>
					<p align="right" >
						�����ˣ�ǩ����:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�����ˣ�ǩ����:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="12" height="35">
					<p align="center">
						��ͥ��Ա���
					</p>
				</td>
			</tr>
			<tr>
				<td width="65" valign="middle" height="35">
					<p align="center">
						�� Ա
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						�� ��
					</p>
				</td>
				<td colspan="7">
					<p align="center">
						�� �� �� λ
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						�� �� ��
					</p>
				</td>
			</tr>
			<tr>
				<td width="65" valign="middle" height="35">
					<div align="center">
						<bean:write name="rs" property="jtcy1_gx" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy1_xm" />
					</div>
				</td>
				<td colspan="7" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy1_gzdz" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy1_ysr" />&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td width="65" valign="middle" height="35">
					<div align="center">
						<bean:write name="rs" property="jtcy2_gx" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy2_xm" />
					</div>
				</td>
				<td colspan="7" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy2_gzdz" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy2_ysr" />&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td width="65" valign="middle" height="35">
					<div align="center">
						<bean:write name="rs" property="jtcy3_gx" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy3_xm" />
					</div>
				</td>
				<td colspan="7" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy3_gzdz" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy3_ysr" />&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td width="65" valign="middle" height="35">
					<div align="center">
						<bean:write name="rs" property="jtcy4_gx" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy4_xm" />
					</div>
				</td>
				<td colspan="7" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy4_gzdz" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy4_ysr" />&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td width="65" valign="middle" height="35">
					<div align="center">
						<bean:write name="rs" property="jtcy5_gx" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy5_xm" />
					</div>
				</td>
				<td colspan="7" valign="middle">
					<div align="center">
						<bean:write name="rs" property="jtcy5_gzdz" />
					</div>
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy5_ysr" />&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td height="89" colspan="12" valign="top">
					<p>
						<bean:message key="lable.xsgzyxpzxy" />��������
					</p>
					<bean:write name="rs" property="xyshyj" />
					<p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td height="89" colspan="12" valign="top">
					<p>
						ѧ������������
					</p>
					<bean:write name="rs" property="xxshyj" />
					<p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td height="89" colspan="12" valign="top">
					<p>
						ѧУ�쵼���������
					</p>
					<p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�����£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="12" valign="top" align="right" height="150">
					<p><font color="red">
						ע����ĸ�����������ߵ�λ��ֵ�֤����
					</font></p>
				</td>
			</tr>
			<tr>
				<td valign="top" colspan="12">
					<p align="center">
						<strong>ͨ </strong><strong></strong><strong>֪ </strong><strong></strong><strong>��
						</strong><strong></strong>
					</p>
					<p>
						���񴦣�
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="bjmc" />
						&nbsp;��&nbsp;
						<bean:write name="rs" property="xm" />
						&nbsp; ͬѧ����&nbsp;
						<bean:write name="rs" property="sqly" />
						&nbsp; ԭ��������ɫͨ����������׼�����㴦�������������ѧע��������
					</p>
					<p align="right">
						�ش�֪ͨ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						ѧ �� ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td valign="top" colspan="12">
					<p align="center">
						<strong>ͨ </strong><strong></strong><strong>֪ </strong><strong></strong><strong>��
						</strong><strong></strong>
					</p>
					<p>
						���񴦣�
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="bjmc" />
						&nbsp;��&nbsp;
						<bean:write name="rs" property="xm" />
						&nbsp; ͬѧ����&nbsp;
						<bean:write name="rs" property="sqly" />
						&nbsp; ԭ��������ɫͨ����������׼�����㴦�������������ѧע��������
					</p>
					<p align="right">
						�ش�֪ͨ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						ѧ �� ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td valign="top" colspan="12">
					<p align="center">
						<strong>ͨ </strong><strong></strong><strong>֪ </strong><strong></strong><strong>��
						</strong><strong></strong>
					</p>
					<p>
						&nbsp;
						<bean:write name="rs" property="xy" />
						&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="rs" property="bjmc" />
						&nbsp;��&nbsp;
						<bean:write name="rs" property="xm" />
						&nbsp; ͬѧ����&nbsp;
						<bean:write name="rs" property="sqly" />
						&nbsp; ԭ��������ɫͨ����������׼�����㴦�������������ѧע��������
					</p>
					<p align="right">
						�ش�֪ͨ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						ѧ �� ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','��ɫͨ�������')" />
	</div>
</body>
</html:html>
