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
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<p align="center" style="font-size:24px">
			<bean:write name="sqlb" />
			�����
		</p>

		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<td width="54" height="35">
					<p align="center">
						�� ��
					</p>
				</td>
				<td width="147">
					<p align="center">
						<bean:write name="rs" property="xm" />
					</p>
				</td>
				<td width="53">
					<p align="center">
						�Ա�
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name="rs" property="xb" />
					</p>
				</td>
				<td colspan="2" width="12%">
					<p align="center">
						��������
					</p>
				</td>
				<td width="82">
					<p align="center">
						<bean:write name="rs" property="csny" />
					</p>
				</td>
				<td width="51">
					<p align="center">
						����
					</p>
				</td>
				<td colspan="2" width="8%">
					<p align="center">
						<bean:write name="rs" property="mzmc" />
					</p>
				</td>
				<td>
					<p align="center">
						������ò
					</p>
				</td>
				<td width="74">
					<p align="center">
						<bean:write name="rs" property="zzmmmc" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="54" height="35">
					<p align="center">
						ѧ Ժ
					</p>
				</td>
				<td width="147">
					<p align="center">
						<bean:write name="rs" property="xy" />
					</p>
				</td>
				<td width="53">
					<p align="center">
						�༶
					</p>
				</td>
				<td colspan="2" width="10%">
					<p align="center">
						<bean:write name="rs" property="bjmc" />
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						ѧ��
					</p>
				</td>
				<td width="82">
					<p align="center">
						<bean:write name="rs" property="xh" />
					</p>
				</td>
				<td width="51">
					<p align="center">
						����ְ��
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name="rs" property="xrzw" />
					</p>
				</td>
				<td width="66">
					<p align="center">
						����
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						<bean:write name="rs" property="qs" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="54" height="35">
					<p align="center">
						��ͥ��ϸ��ַ
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						<bean:write name="rs" property="jtzz" />
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						�ʱ�
					</p>
				</td>
				<td width="82">
					<p align="center">
						<bean:write name="rs" property="yzbm" />
					</p>
				</td>
				<td width="51">
					<p align="center">
						��ϵ�绰
					</p>
				</td>
				<td colspan="5">
					<p align="center">
						<bean:write name="rs" property="lxdh" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="54" height="70">
					<p align="center">
						���������Ѳ���������ѧ�Ѽ��������
					</p>
				</td>
				<td colspan="13" valign="top">
					<p>
						<bean:write name="rs" property="yxsbzdkqk" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="54" height="70">
					<p align="center">
						��У�μ��ڹ���ѧ���
					</p>
				</td>
				<td colspan="13" valign="top">
					<p>
						<bean:write name="rs" property="zxcjqgzxqk" />
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="14" valign="middle" height="35">
					<p align="center">
						��ͥ������Դ
					</p>
				</td>
			</tr>
			<tr>
				<td width="54" rowspan="6">
					<p align="center">
						�� ͥ �� Ա
					</p>
				</td>
				<td width="147" height="35">
					<p align="center">
						�� ν
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						�� ��
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						��������
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						������λ
					</p>
				</td>
				<td colspan="4">
					<p align="center">
						������
					</p>
				</td>
			</tr>
			<tr>
				<td width="147" valign="middle" height="35">
					<p align="center">
						<bean:write name="rs" property="jtcy1_gx" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy1_xm" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						&nbsp;<bean:write name="rs" property="jtcy1_nl" />&nbsp;
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy1_gzdz" />
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy1_ysr" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="147" valign="middle" height="35">
					<p align="center">
						<bean:write name="rs" property="jtcy2_gx" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy2_xm" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						&nbsp;<bean:write name="rs" property="jtcy2_nl" />&nbsp;
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy2_gzdz" />
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy2_ysr" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="147" valign="middle" height="35">
					<p align="center">
						<bean:write name="rs" property="jtcy3_gx" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy3_xm" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						&nbsp;<bean:write name="rs" property="jtcy3_nl" />&nbsp;
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy3_gzdz" />
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy3_ysr" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="147" valign="middle" height="35">
					<p align="center">
						<bean:write name="rs" property="jtcy4_gx" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy4_xm" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						&nbsp;<bean:write name="rs" property="jtcy4_nl" />&nbsp;
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy4_gzdz" />
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy4_ysr" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="147" valign="middle" height="35">
					<p align="center">
						<bean:write name="rs" property="jtcy5_gx" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy5_xm" />
					</p>
				</td>
				<td colspan="2" valign="middle">
					<p align="center">
						&nbsp;<bean:write name="rs" property="jtcy5_nl" />&nbsp;
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy5_gzdz" />
					</p>
				</td>
				<td colspan="4" valign="middle">
					<p align="center">
						<bean:write name="rs" property="jtcy5_ysr" />
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="14" height="35">
					<p align="center">
						����ѧ�Ѽ������ɣ��ڵڶ�ҳ�� �������ڣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="6" valign="top" height="70">
					<p>
						���׵�λ���������������
					</p>
					<br><br>
					<p align="right">
						ǩ�������£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
				<td colspan="8" valign="top">
					<p>
						ĸ�׵�λ���������������
					</p>
					<br><br>
					<p align="right">
						ǩ�������£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td height="89" colspan="14">
					<p>
						<bean:message key="lable.xsgzyxpzxy" />��������
					</p>
					<bean:write name="rs" property="xyshyj" />
					<p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						������
						<bean:write name="rs" property="njmjedx" />
						&nbsp;&nbsp;&nbsp;&nbsp;ǩ�������£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;��
					</p>
			</tr>
			<tr>
				<td height="89" colspan="14">
					<p>
						ѧ������������
					</p>
					<bean:write name="rs" property="xxshyj" />
					<p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						�����
						<bean:write name="rs" property="jmjedx" />
						&nbsp;&nbsp;&nbsp;&nbsp;ǩ�������£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;��
					</p>
			</tr>
			<tr>
				<td colspan="14" height="80" valign="top">
					<p align="right">
					����һʽ���ݣ�һ����<bean:message key="lable.xsgzyxpzxy" />��һ�ݽ�ѧ������
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="14" height="200" valign="top">
					<p>
						�������ɣ�
					</p>
					<bean:write name="rs" property="sqly" />
				</td>
			</tr>
			</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','ѧ��ѧ�Ѽ��������')" />
	</div>
</body>
</html:html>
