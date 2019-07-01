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
	<html:form action="gjjzxjsqb.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<p align="center" style="font-size:24px">
			���Һ�ʡ������ѧ��ѧ�������
		</p>
		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<td scope="col" width="4%">
					<div align="center">
						��
						<br>
						��
						<br>
						��
						<br>
						��
					</div>
				</td>
				<td colspan="8" scope="col">
					<div align="center">
						<bean:write name="rs" property="jxjmc" />
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="4" scope="row" width="4%">
					<div align="center">
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
					</div>
				</td>
				<td width="12%">
					<div align="center">
						����
					</div>
				</td>
				<td width="12%">
					<div align="center">
						<bean:write name="rs" property="xm" />
					</div>
				</td>
				<td width="12%">
					<div align="center">
						�Ա�
					</div>
				</td>
				<td width="12%">
					<div align="center">
						<bean:write name="rs" property="xb" />
					</div>
				</td>
				<td width="12%">
					<div align="center">
						��������
					</div>
				</td>
				<td width="12%">
					<div align="center">
						<bean:write name="rs" property="csny" />
					</div>
				</td>
				<td width="12%">
					<div align="center">
						����
					</div>
				</td>
				<td width="12%">
					<div align="center">
						<bean:write name="rs" property="mzmc" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					<p align="center">
						&nbsp;
						<bean:write name="rs" property="xxmc" />
						&nbsp;��ѧ&nbsp;
						<bean:write name="rs" property="xy" />
						&nbsp;<bean:message key="lable.xsgzyxpzxy" />&nbsp;
						<bean:write name="rs" property="xmc" />
						&nbsp;ϵ&nbsp;
						<bean:write name="rs" property="bjmc" />
						&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��ѧʱ��
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="rxny" />
					</div>
				</td>
				<td>
					<div align="center">
						�߿��ܷ�
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="gkfs" />
					</div>
				</td>
				<td>
					<div align="center">
						��ҵ����
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="bygz" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						������ֽ���
					</div>
				</td>
				<td colspan="7">
					<bean:write name="rs" property="jlxx" />
				</td>
			</tr>
			<tr>
				<td rowspan="3" scope="row">
					<div align="center">
						��ͥ�������
					</div>
				</td>
				<td>
					<div align="center">
						��ͥסַ
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						<bean:write name="rs" property="jtzz" />
					</div>
				</td>
				<td>
					<div align="center">
						��������
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="yzbm" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��ͥ����
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="radjthk" />
					</div>
				</td>
				<td>
					<div align="center">
						��ͥ������
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="hkrs" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ͥ��������
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtyzsr" />
					</div>
				</td>
				<td>
					<div align="center">
						������Դ
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtsrly" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						�˾�������Դ
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtrjsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="6" scope="row">
					<div align="center">
						��ͥ��Ա���
					</div>
				</td>
				<td>
					<div align="center">
						����
					</div>
				</td>
				<td>
					<div align="center">
						����
					</div>
				</td>
				<td>
					<div align="center">
						�뱾�˹�ϵ
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						������ѧϰ��λ
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy1_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_gx" />
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						<bean:write name="rs" property="jtcy1_gzdz" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy2_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_gx" />
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						<bean:write name="rs" property="jtcy2_gzdz" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy3_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_gx" />
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						<bean:write name="rs" property="jtcy3_gzdz" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy4_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_gx" />
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						<bean:write name="rs" property="jtcy4_gzdz" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy5_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_gx" />
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						<bean:write name="rs" property="jtcy5_gzdz" />
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="7" scope="row">
					<div align="center">
						��ѧ��ɼ���߿��ɼ�
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						�γ�����
					</div>
				</td>
				<td>
					<div align="center">
						������ȵ�
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						�γ�����
					</div>
				</td>
				<td>
					<div align="center">
						������ȵ�
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc1_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="kc1_cj" />&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc2_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="kc2_cj" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc3_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="kc3_cj" />&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc4_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="kc4_cj" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc5_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="kc5_cj" />&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc6_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="kc6_cj" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc7_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="kc7_cj" />&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc8_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="kc8_cj" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc9_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="kc9_cj" />&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc10_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="kc10_cj" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc11_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="kc11_cj" />&nbsp;
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="kc12_mc" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="kc12_cj" />
					</div>
				</td>
			</tr>
			<tr>
				<td height="60" colspan="9" valign="top">
					<p>
						�������ɣ�
					</p>
					<bean:write name="rs" property="sqly" />
					<p>
					<br>
					<br>
					<p align="right">
					(�����˱�֤��������д���ȫ����ʵ)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						������ǩ����
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td height="89" colspan="9" valign="top">
					<p>
						<bean:message key="lable.xsgzyxpzxy" />��������
					</p>
					<bean:write name="rs" property="xyshyj" />
					<p>
						<br>
						<br>
					<p align="right">
						<bean:message key="lable.xsgzyxpzxy" />�쵼ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="9" valign="top">
					<p>
						ѧУ��˼���ʾ�����
					</p>
					<bean:write name="rs" property="xxshyj" />
					<p>
					<p align="right">
						У�쵼ǩ�֣�
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��ѧУ���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','���Һ�ʡ������ѧ��ѧ�������')" />
	</div>
</body>
</html:html>
