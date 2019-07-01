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
		</p>

		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<td colspan="19">
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<strong>ѧУ��</strong>&nbsp;&nbsp;
						<bean:write name="rs" property="xxmc" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<strong>Ժ��ϵ����</strong>&nbsp;&nbsp;
						<bean:write name="rs" property="xymc" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<strong>רҵ�� </strong>&nbsp;&nbsp;
						<bean:write name="rs" property="xmc" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<strong>�꼶�� </strong>&nbsp;&nbsp;
						<bean:write name="rs" property="nj" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</p>
				</td>
			</tr>
			<tr>
				<td rowspan="4" scope="col" width="5%">
					<div align="center">
						<strong>ѧ�����˻������</strong>
					</div>
				</td>
				<td scope="col" width="10%">
					<div align="center">
						�� ��
					</div>
				</td>
				<td colspan="2" scope="col">
					<bean:write name="rs" property="xm" />
				</td>
				<td scope="col" width="10%">
					<div align="center">
						�� ��
					</div>
				</td>
				<td scope="col" width="10%">
					<bean:write name="rs" property="xb" />
				</td>
				<td scope="col" width="10%">
					<div align="center">
						��������
					</div>
				</td>
				<td scope="col" width="10%">
					<bean:write name="rs" property="csny" />
				</td>
				<td scope="col" width="10%">
					<div align="center">
						�� ��
					</div>
				</td>
				<td scope="col" width="10%">
					<bean:write name="rs" property="mzmc" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						���֤����
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="sfzh" />
				</td>
				<td>
					<div align="center">
						������ò
					</div>
				</td>
				<td>
					<bean:write name="rs" property="zzmm" />
				</td>
				<td>
					<p align="center">
						��ѧǰ����
					</p>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="rxqhk" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						��ͥ�˿���
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="jtrks" />
				</td>
				<td>
					<p align="center">
						��ҵѧУ
					</p>
				</td>
				<td>
					<bean:write name="rs" property="byxx" />
				</td>
				<td>
					<div align="center">
						�����س�
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="grtc" />
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						�� ��
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="sfgc" />
				</td>
				<td>
					<div align="center">
						�� ��
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="sfdq" />
				</td>
				<td>
					<div align="center">
						��ʿ��Ů
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="sflszn" />
				</td>
			</tr>
			<tr>
				<td rowspan="2" scope="row">
					<div align="center">
						<strong>��ͥͨѶ��Ϣ</strong>
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						��ϸͨѶ��ַ
					</div>
				</td>
				<td colspan="7">
					<bean:write name="rs" property="jtxxtxdz" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��������
					</div>
				</td>
				<td colspan="2">
					<bean:write name="rs" property="jtyzbm" />
				</td>
				<td>
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td colspan="4">
					<bean:write name="rs" property="jtlxdh" />
				</td>
			</tr>
			<tr>
				<td rowspan="6" scope="row">
					<div align="center">
						<strong>��ͥ��Ա���</strong>
					</div>
				</td>
				<td>
					<div align="center">
						����
					</div>
				</td>
				<td width="10%">
					<div align="center">
						����
					</div>
				</td>
				<td width="10%">
					<p align="center">
						��ѧ����ϵ
					</p>
				</td>
				<td colspan="3">
					<div align="center">
						������ѧϰ����λ
					</div>
				</td>
				<td>
					<div align="center">
						ְҵ
					</div>
				</td>
				<td>
					<div align="center">
						������(Ԫ)
					</div>
				</td>
				<td>
					<div align="center">
						����״��
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
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="jtcy1_gzdw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_zy" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_jkzk" />
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
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="jtcy2_gzdw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_zy" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_jkzk" />
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
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="jtcy3_gzdw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_zy" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_jkzk" />
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
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="jtcy4_gzdw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_zy" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_jkzk" />
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
				<td colspan="3">
					<div align="center">
						<bean:write name="rs" property="jtcy5_gzdw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_zy" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_nsr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_jkzk" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row" height="200">
					<p align="center">
						<strong>Ӱ���ͥ����״���й���Ϣ</strong>
					</p>
				</td>
				<td colspan="9">
					��ͥ�˾�������<u>&nbsp;
					<bean:write name="rs" property="jtrjnsr" /></u>
					��Ԫ����ѧ����ѧ���ѻ��������<u>&nbsp;
					<bean:write name="rs" property="xsyhhjqk" /></u>
					��
					<br />
					��ͥ������Ȼ�ֺ������<u>&nbsp;
					<bean:write name="rs" property="jtzszrzhqk" /></u>
					��
					<br />
					��ͥ����ͻ�������¼���<u>&nbsp;
					<bean:write name="rs" property="jtzstfywsj" /></u>
					��
					<br />
					��ͥ��Ա��м����������Ͷ������������<u>&nbsp;
					<bean:write name="rs" property="jtcyndlrqk" /></u>
					��
					<br />
					��ͥ��Աʧҵ�����<u>&nbsp;
					<bean:write name="rs" property="jtcysyqk" /></u>
					����ͥǷծ�����<u>&nbsp;
					<bean:write name="rs" property="jtqzqk" /></u>
					��
					<br />
					���������<u>&nbsp;
					<bean:write name="rs" property="qtqk" /></u>
					��
				</td>
			</tr>
			<tr>
				<td scope="row" height="100">
					<div align="center">
						<strong>ǩ��</strong>
					</div>
				</td>
				<td>
					<div align="center">
						ѧ������
					</div>
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					<div align="center">
						ѧ���ҳ���໤��
					</div>
				</td>
				<td colspan="2">
					&nbsp;
				</td>
				<td>
					<div align="center">
						ѧ����ͥ���ڵ������ֵ���������
					</div>
				</td>
				<td colspan="3">
					<div align="left">
						<p>
							������ǩ�֣�
						</p>
						<br />
						<p>
							��λ���ƣ�
						</p>
						<p>
							&nbsp;&nbsp;&nbsp;&nbsp;���Ӹǹ��£�
						</p>
					</div>
					<div align="right">
						��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="2" scope="row">
					<div align="center">
						<strong>����������Ϣ</strong>
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						��ϸͨѶ��ַ
					</div>
				</td>
				<td colspan="7">
					<bean:write name="rs" property="mzbmxxtxdz" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��������
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="mzbmyzbm" />
				</td>
				<td>
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="mzbmlxdh" />
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','�����������')" />
	</div>
</body>
</html:html>
