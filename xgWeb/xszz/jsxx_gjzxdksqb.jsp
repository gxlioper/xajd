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
			<bean:write name="sqlb" /><br>
			������ѧ�������������
		</p>
		<table width="100%" border="1" class="tbstyle" id="theTable">
			<tr>
				<td colspan="7">
					<div align="center">
						ϵ &nbsp;<u>&nbsp;
						<bean:write name='rs' property="zymc" />
						&nbsp;&nbsp;</u>&nbsp;&nbsp; �༶ &nbsp;<u>&nbsp;
						<bean:write name='rs' property="bjmc" />
						&nbsp;&nbsp;</u>&nbsp;&nbsp; ���� &nbsp;<u>&nbsp;
						<bean:write name='rs' property="xm" />
						&nbsp;&nbsp;</u>&nbsp;&nbsp; �Ա� &nbsp;<u>&nbsp;
						<bean:write name='rs' property="xb" />
						&nbsp;&nbsp;</u>&nbsp;&nbsp; �������� &nbsp;<u>&nbsp;
						<bean:write name='rs' property="csrq" /></u>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col" width="10%">
					<div align="center">
						��ͥסַ
					</div>
				</td>
				<td colspan="3" scope="col">
					<div align="center">
						<bean:write name='rs' property="jtzz" />
					</div>
				</td>
				<td scope="col" width="20%">
					<div align="center">
						ѧ����ϵ�绰
					</div>
				</td>
				<td scope="col" width="20%">
					<div align="center">
						<bean:write name='rs' property="xslxdh" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						���֤��
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="sfzh" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />ͳ��ѧ����
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="xh" />
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="6" scope="row">
					<div align="center">
						��ͥ��Ա
					</div>
				</td>
				<td width="15%">
					<div align="center">
						��ν
					</div>
				</td>
				<td width="15%">
					<div align="center">
						����
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<p>
							������λ��ְ��
						</p>
					</div>
				</td>
				<td>
					<div align="center">
						������
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy1_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy1_nl" />&nbsp;
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy1_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy1_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy2_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy2_nl" />&nbsp;
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy2_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy2_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy3_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy3_nl" />&nbsp;
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy3_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy3_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy4_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy4_nl" />&nbsp;
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy4_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy4_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy5_xm" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy5_nl" />&nbsp;
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy5_gzdwjzw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy5_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						ѧϰ���
					</div>
				</td>
				<td colspan="5">
					<div align="left">
						��ѧ�ɼ� <u>&nbsp;
						<bean:write name='rs' property="rxcj" />
						&nbsp;</u>�֣���ѧ�ڰ��������� <u>&nbsp;
						<bean:write name='rs' property="sxqpm" />
						&nbsp;</u>�����γ̳ɼ�ƽ���� <u>&nbsp;
						<bean:write name='rs' property="pjcj" />
						&nbsp;</u>�֡�
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						�ճ�����
					</div>
				</td>
				<td colspan="5" height="30">
					<bean:write name='rs' property="rcbx" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						�������
					</div>
				</td>
				<td colspan="5" height="30">
					<bean:write name='rs' property="jcqk" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						��ͥ�����������
					</div>
				</td>
				<td colspan="5" height="50">
					<bean:write name='rs' property="jtjjknzk" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						�ɷ����
					</div>
				</td>
				<td colspan="5">
					<div align="left">
						ȫѧ��ѧ�� <u>&nbsp;
						<bean:write name='rs' property="xnxf" />
						&nbsp;</u>Ԫ�� ��ͥ�ṩ����������Ѳ�������<u> &nbsp;
						<bean:write name='rs' property="jtfybzs" />
						&nbsp;</u>Ԫ�� ����ѧ�ѣ�<u> &nbsp;
						<bean:write name='rs' property="hjxf" />
						&nbsp;</u>Ԫ
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="2" scope="row">
					<div align="center">
						��������
					</div>
				</td>
				<td colspan="5">
					<div align="left">
						����������ࣺ����ʡ������ũ����Դ����ѧ����(�ߵ�ѧУ������ѧ����)
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<div align="left">
						��ѧ��������� <u>&nbsp;
						<bean:write name='rs' property="bxqdks" />
						&nbsp;</u>Ԫ�� �ƻ� <u>&nbsp;
						<bean:write name='rs' property="jhhkkssj" />
						&nbsp;</u>�ꡪ�� <u>&nbsp;
						<bean:write name='rs' property="jhhkjssj" />
						&nbsp;</u>��黹
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						���������
					</div>
				</td>
				<td colspan="5">
					<div align="center">
						(ͬ�����Դ��ũ�����������Ǹߵȹ�����ѧ����)
					</div>
					<div align="right">
						��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						ϵ�����
					</div>
				</td>
				<td colspan="5" height="20">
					<bean:write name='rs' property="xyshyj" />
					<br>
					<br>
					<div align="right">
						��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						��ע
					</div>
				</td>
				<td colspan="5">
					<bean:write name='rs' property="bz" />
					<br>
					<br>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<div align="center">
						�˱������ϵ��(������).ϵ��ͬ��󣬸�ѧ��������Դ�ش���֤������д��У����������
					</div>
					<div align="right">
						�������:&nbsp;&nbsp;
						<bean:write name='rs' property="sqsj" />
						&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','������Ϣְҵ����<bean:message key="lable.xsgzyxpzxy" />������ѧ�������������')" />
	</div>
</body>
</html:html>
