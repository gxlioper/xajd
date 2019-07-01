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
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<p align="center" style="font-size:24px">
			<bean:write name="sqlb" />
			�����
		</p>
		<table width="100%" border="1" class="tbstyle" id="theTable">
			<tr>
				<td width="104" height="35">
					<p align="center">
						���������
					</p>
				</td>
				<td colspan="2" valign="middle">
				<p align="center">
					<bean:write name='rs' property="xm" />
				</p>
				</td>
				<td colspan="2">
					<p align="center">
						�� ��
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name='rs' property="xb" />
					</p>
				</td>
				<td width="103">
					<p align="center">
						��������
					</p>
				</td>
				<td width="225">
					<p align="center">
						<bean:write name='rs' property="csny" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						�Ͷ�ѧУ
					</p>
				</td>
				<td colspan="5">
					<p align="center">
						<bean:write name='rs' property="xxmc" />
					</p>
				</td>
				<td width="169">
					<p align="center">
						Ժ ϵ
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name='rs' property="xymc" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						���֤����
					</p>
				</td>
				<td colspan="5">
					<p align="center">
						<bean:write name='rs' property="sfzh" />
					</p>
				</td>
				<td width="169">
					<p align="center">
						ר ҵ
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name='rs' property="xmc" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						ѧ ��
					</p>
				</td>
				<td colspan="5">
					<p align="center">
						<bean:write name='rs' property="xh" />
					</p>
				</td>
				<td width="169">
					<p align="center">
						����绰
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						<bean:write name='rs' property="ssdh" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						ѧ ��
					</p>
				</td>
				<td width="157">
					<p align="center">
						<bean:write name='rs' property="xz" />
						&nbsp;��
					</p>
				</td>
				<td colspan="2">
					<p align="center">
						ѧ ��
					</p>
				</td>
				<td colspan="5">
					<p align="center">
						&nbsp;�� ר��&nbsp;&nbsp; �� ����&nbsp;&nbsp; �� ˶ʿ&nbsp;&nbsp; �� ��ʿ
						&nbsp;&nbsp;�� ���� ______________ &nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						������
					</p>
				</td>
				<td colspan="8">
					<p align="center">
						�ܽ�&nbsp;
						<bean:write name='rs' property="dkze" />
						&nbsp;Ԫ�� ѧ��&nbsp;
						<bean:write name='rs' property="xfdks" />
						&nbsp;Ԫ�� ס�޷�&nbsp;
						<bean:write name='rs' property="zsfdks" />
						&nbsp;Ԫ�� �����&nbsp;
						<bean:write name='rs' property="shfdks" />
						&nbsp;Ԫ
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						��������
					</p>
				</td>
				<td colspan="8">
					<p align="center">
						��&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="kssjYear" />&nbsp;��&nbsp;
						<bean:write name='rs' property="kssjMonth" />&nbsp;��&nbsp;
						<bean:write name='rs' property="kssjDay" />&nbsp;��&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="jssjYear" />&nbsp;��&nbsp;
						<bean:write name='rs' property="jssjMonth" />&nbsp;��&nbsp;
						<bean:write name='rs' property="jssjDay" />&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						��ͥסַ
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="jtzz" />
					</p>
				</td>
				<td colspan="2" rowspan="5" align="center" valign="middle">
					<p>
						���˱�֤������д������ʵ���󣬲������Ͽɡ�
					</p>
					<p align="right">
						��������ˣ�ǩ�֣���
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						�� ��
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="yzbm" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						�� ��
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="dh" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						��������
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="fqxm" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						ְ ҵ
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="fqzy" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						���֤����
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="fqsfzh" />
					</p>
				</td>
				<td colspan="2" rowspan="5" valign="middle" align="center">
					<p align="center">
						���������ϵ��У�Ͷ�ѧ������������������ʵ���ش�֤����
					</p>
					
					<p align="right">
						��������� / ѧУ��ǩ�£���
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
					<p align="right">
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						ĸ������
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="mqxm" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						ְ ҵ
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="mqzy" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						���֤����
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="mqsfzh" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="35">
					<p align="center">
						��ͥ����
					</p>
				</td>
				<td colspan="6" valign="middle">
					<p align="center">
						<bean:write name='rs' property="jtsr" />
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="70">
					<p align="center">
						�ͻ�����
					</p>
					<p align="center">
						���
					</p>
				</td>
				<td colspan="8" valign="middle">
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��������������������ѧ���������ݹ�����ش������ߣ���ί�н�������ڸ�У������������ȡ�����������ϣ������ֳ���֤��������˱���ǩ�֣���������ˣ���ͬ���������룬�����벿�����ܼ���Ȩ��������ˡ�
					</p>
					<p align="right">
						�ͻ�����:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="70">
					<p align="center">
						��������
					</p>
					<p align="center">
						���
					</p>
				</td>
				<td colspan="8" valign="bottom">
					<p align="center">
						�� ͬ��ͻ��������&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �� ��ͬ��ͻ��������
					</p>
					<p align="right">
						��������:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
			<tr>
				<td width="104" height="70">
					<p align="center">
						��Ȩ���������
					</p>
				</td>
				<td colspan="8" valign="bottom">
					<p align="center">
						�� ͬ�ⷢ�Ŵ���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �� ��ͬ�ⷢ�Ŵ���
					</p>
					<p align="right">
						��Ȩ������:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��
					</p>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','�й������Ϻ��з��й�����ѧ��������������')" />
	</div>
</body>
</html:html>
