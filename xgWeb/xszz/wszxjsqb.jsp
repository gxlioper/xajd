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
<head>

	<title><bean:message key="lable.title" />
	</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Expires", "0");
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>

</head>

<body>
	<html:form action="/wszxjsqb" method="post">
		<p align="center">
			<strong><bean:write name="rs" property="xxmc"/> </strong><strong>�� </strong><strong>��
			</strong><strong>�� </strong><strong>��ѧ�� </strong>
		</p>
		<p align="center">
			<strong>�����Ƽ��� </strong>
		</p>
		<p>
			&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;רҵ���꼶��
		</p>
		<table  class="tbstyle" width="100%" id="theTable">
			<tr>
				<td width="44" valign="middle">
					<div align="center">
						ѧ��
					</div>
				</td>
				<td width="65" valign="middle">
					<div align="center"><bean:write name="rs" property="xh" /></div>
				</td>
				<td width="44" valign="middle">
					<div align="center">
						�Ա�
					</div>
				</td>
				<td height="31" colspan="2" valign="top">
					<bean:write name="rs" property="xb" />
				</td>
				<td colspan="2" valign="middle">
					<div align="center">
						����
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="mzmc" />
				</td>
				<td width="148">
					<p align="center">
						��������
					</p>
				</td>
				<td width="111">
					<bean:write name="rs" property="csny" />
				</td>
			</tr>
			<tr>
				<td width="44" valign="middle">
					<div align="center">
						����
					</div>
				</td>
				<td width="65" valign="middle">
					<div align="center"><bean:write name="rs" property="csny" /></div>
				</td>
				<td width="44" valign="middle">
					<div align="center">
						ѧ��
					</div>
				</td>
				<td height="31" colspan="2">
					<bean:write name="rs" property="xz" />
				</td>
				<td colspan="4">
					<p align="left">
						���Һţ����绰��
					</p>
				</td>
				<td width="148">
					<bean:write name="rs" property="qsh" />&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="qsdh" />
				</td>
				<td width="111" rowspan="3">
					<p align="center">
						��Ƭ
					</p>
				</td>
			</tr>
			<tr>
				<td width="44" valign="middle">
					<div align="center">
						<br>
						����
						<br>
						<br>
						��ò
					</div>
				</td>
				<td width="65" valign="middle">
					<bean:write name="rs" property="zzmm" />
				</td>
				<td width="44" valign="middle">
					<div align="center">
						<br>
						����
					</div>
				</td>
				<td colspan="4">
					<bean:write name="rs" property="jg" />
				</td>
				<td colspan="2">
					<div align="center">
						�ʱ�
					</div>
				</td>
				<td>
					<bean:write name="rs" property="yzbm" />
				</td>
			</tr>
			<tr>
				<td width="44" valign="top">
					<p>
						��ͥ
					</p>
					<p>
						��ַ
					</p>
				</td>
				<td colspan="9" valign="top">
					<bean:write name="rs" property="jtdz" />
				</td>
			</tr>
			<tr>
				<td width="44" rowspan="7" valign="middle">
					<div align="center">
						��ͥ
						<br>
						<br>
						��Ҫ
						<br>
						<br>
						��Ա
					</div>
				</td>
				<td width="65" valign="top">
					<p align="center">
						�� ��
					</p>
				</td>
				<td colspan="2" valign="top">
					<p>
						��ν
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						����
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						�� �� �� λ
					</p>
				</td>
				<td width="111">
					<p align="center">
						�� �� ��
					</p>
				</td>
			</tr>
			<tr>
				<td width="65">
					<bean:write name="rs" property="jtcy1_xm" />
				</td>
				<td colspan="2">
					<bean:write name="rs" property="jtcy1_gx" />
				</td>
				<td colspan="2">
					&nbsp;<bean:write name="rs" property="jtcy1_nl" />&nbsp;
				</td>
				<td height="31" colspan="4">
					<bean:write name="rs" property="jtcy1_gzdw" />
				</td>
				<td width="111">
					<bean:write name="rs" property="jtcy1_ysr" />
				</td>
			</tr>
			<tr>
				<td width="65">
					<bean:write name="rs" property="jtcy2_xm" />
				</td>
				<td colspan="2">
					<bean:write name="rs" property="jtcy2_gx" />
				</td>
				<td colspan="2">
					&nbsp;<bean:write name="rs" property="jtcy2_nl" />&nbsp;
				</td>
				<td height="31" colspan="4">
					<bean:write name="rs" property="jtcy2_gzdw" />
				</td>
				<td width="111">
					<bean:write name="rs" property="jtcy2_ysr" />
				</td>
			</tr>
			<tr>
				<td width="65">
					<bean:write name="rs" property="jtcy3_xm" />
				</td>
				<td colspan="2">
					<bean:write name="rs" property="jtcy3_gx" />
				</td>
				<td colspan="2">
					&nbsp;<bean:write name="rs" property="jtcy3_nl" />&nbsp;
				</td>
				<td height="31" colspan="4">
					<bean:write name="rs" property="jtcy3_gzdw" />
				</td>
				<td width="111">
					<bean:write name="rs" property="jtcy3_ysr" />
				</td>
			</tr>
			<tr>
				<td width="65">
					<bean:write name="rs" property="jtcy4_xm" />
				</td>
				<td colspan="2">
					<bean:write name="rs" property="jtcy4_gx" />
				</td>
				<td colspan="2">
					&nbsp;<bean:write name="rs" property="jtcy4_nl" />&nbsp;
				</td>
				<td height="31" colspan="4">
					<bean:write name="rs" property="jtcy4_gzdw" />
				</td>
				<td width="111">
					<bean:write name="rs" property="jtcy4_ysr" />
				</td>
			</tr>
			<tr>
				<td width="65">
					<bean:write name="rs" property="jtcy5_xm" />
				</td>
				<td colspan="2">
					<bean:write name="rs" property="jtcy5_gx" />
				</td>
				<td colspan="2">
					&nbsp;<bean:write name="rs" property="jtcy5_nl" />&nbsp;
				</td>
				<td height="31" colspan="4">
					<bean:write name="rs" property="jtcy5_gzdw" />
				</td>
				<td width="111">
					<bean:write name="rs" property="jtcy5_ysr" />
				</td>
			</tr>
			<tr>
				<td width="44">
					<div align="center">
						����
						<br>
						<br>
						���
						<br>
						<br>
						����
						<br>
						<br>
						���
					</div>
				</td>
				<td colspan="10">
					<bean:write name="rs" property="drshgzqk" />
				</td>
			</tr>
			<tr>
				<td width="44">
					<div align="center">
						����
						<br>
						<br>
						���
					</div>
				</td>
				<td colspan="10">
					<bean:write name="rs" property="jcqk" />
				</td>
			</tr>
			<tr>
				<td colspan="11" valign="top">
					<p>
						������������ ��������ͥ���������ѧ����Դ������Ʒ�С� ѧϰ��� ����
					</p>
					<bean:write name="rs" property="sqzzly" />
					<p>
						&nbsp;
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;��
					</p>
					<p>
						&nbsp;
					</p>
				</td>
			</tr>
		</table>
		<br>
		<table cellspacing="0" cellpadding="0" border="1" width="100%">

			<tr>
				<td width="43">
					<div align="center">
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						ŵ
					</div>
				</td>
				<td width="525">
					<p>
						&nbsp;
					</p>
					<p>
						����Ը����밮�����ţ��������μӸ�����ṫ������ʵ���ж��ر����
					</p>
					<p align="left">
						&nbsp;
					</p>
					<p align="left">
						ѧ������ǩ����
					</p>
					<p align="right">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td width="43">
					<div align="center">
						<br>
						ѧ
						<br>
						Ժ
						<br>
						��
						<br>
						��
					</div>
				</td>
				<td width="525">
					<p>
						<strong>��Ʒ�б��֡���ͥ��������������Ƽ������ </strong>
					</p>
					<p>
						<bean:write name="rs"  property="xyshyj" />
					</p>
					<p>
						�����ˣ������£�
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td width="43">
					<div align="center">
						<br>
						ѧ
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
						<br>
						��
					</div>
				</td>
				<td width="525" valign="bottom">
					<p>
					<p>
					<bean:write name="rs"  property="xxshyj" />
					<p>
						�����ˣ������£�
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
		</table>
		<p>
			����һʽ���ݣ����ʵ�λ &lt; ���� &gt; ��ѧ��������һ�ݣ���
		</p>
	</html:form>
	<div align="center">
		<input  value="��ӡ" onclick="expTab('theTable','�㽭��ѧ(��)��ѧ�������Ƽ���')">
	</div>
</body>
</html:html>
