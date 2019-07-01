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
			<br>
			�ˡ������Ѳ��������
		</p>
		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<td colspan="9">
					<div align="center">
						ϵ <u>&nbsp;
						<bean:write name='rs' property="zymc" />
						&nbsp;</u>&nbsp; �༶ <u>&nbsp;
						<bean:write name='rs' property="bjmc" />
						&nbsp;</u>&nbsp; ���� <u>&nbsp;
						<bean:write name='rs' property="xm" />
						&nbsp;</u>&nbsp; �Ա� <u>&nbsp;
						<bean:write name='rs' property="xb" />
						&nbsp;</u>&nbsp; �������� <u>&nbsp;
						<bean:write name='rs' property="csrq" /></u>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col" width="10%">
					<div align="center">
						��ͥ��ַ
					</div>
				</td>
				<td colspan="5" scope="col">
					<div align="left">
						<bean:write name='rs' property="jtdz" />
					</div>
				</td>
				<td scope="col" width="10%">
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td colspan="2" scope="col">
					<div align="center">
						<bean:write name='rs' property="lxdh" />
					</div>
				</td>
			</tr>
			<tr>
				<td rowspan="6" scope="row">
					<div align="center">
						��ͥ��Ա
					</div>
				</td>
				<td width="10%">
					<div align="center">
						��ν
					</div>
				</td>
				<td width="10%">
					<div align="center">
						����
					</div>
				</td>
				<td width="10%">
					<div align="center">
						����״��
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						������λ��ְ��
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						������
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy1_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy1_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy1_stzk" />
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy1_gzdwjzw" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy1_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy2_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy2_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy2_stzk" />
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy2_gzdwjzw" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy2_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy3_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy3_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy3_stzk" />
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy3_gzdwjzw" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy3_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy4_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy4_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy4_stzk" />
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy4_gzdwjzw" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy4_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy5_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name='rs' property="jtcy5_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="jtcy5_stzk" />
					</div>
				</td>
				<td colspan="3">
					<div align="center">
						<bean:write name='rs' property="jtcy5_gzdwjzw" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="jtcy5_nsr" />
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="9" scope="row">
					<div align="center">
						�Ƿ����Ҿ���:&nbsp;
						<bean:write name='rs' property="sfljs" />
						&nbsp;&nbsp;&nbsp;&nbsp; �Ƿ��ǵ���:&nbsp;
						<bean:write name='rs' property="sfdq" />
						&nbsp;&nbsp;&nbsp;&nbsp; ��ĸ�Ƿ���˫�¸�:&nbsp;
						<bean:write name='rs' property="sfsxg" />
						&nbsp;&nbsp;&nbsp;&nbsp; ��ĸ�Ƿ��вм�:&nbsp;
						<bean:write name='rs' property="sfcj" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						ѧϰ���
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="xxqk" />
					<br />
					<div align="right">
						(���������޲�����γ�&nbsp;
						<bean:write name='rs' property="bkhywbjgkc" />
						&nbsp;)
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						���еȵ�
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="cxdd" />
					</div>
				</td>
				<td>
					<div align="center">
						ƽʱ����
					</div>
				</td>
				<td colspan="6">
					<bean:write name='rs' property="psbx" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						�������
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="jcqk" />
				</td>
			</tr>
			<tr>
				<td colspan="4" scope="row">
					ÿ���ͥ�ṩ����&nbsp;&nbsp;<u>&nbsp;
					<bean:write name='rs' property="mnjttgfy" />
					&nbsp;</u>Ԫ
				</td>
				<td colspan="2">
					������ѵ��ṩ<u>&nbsp;
					<bean:write name='rs' property="mnqphytgfy" />
					&nbsp;</u>Ԫ
				</td>
				<td colspan="3">
					�ϼ�ÿ���ṩ<u>&nbsp;
					<bean:write name='rs' property="mnhjtgfy" />
					&nbsp;</u>Ԫ
				</td>
			</tr>
			<tr>
				<td colspan="4" scope="row">
					ÿ��Ӧ���ɸ��ַ���<u>&nbsp;
					<bean:write name='rs' property="mnyjgzfy" />
					&nbsp;</u>Ԫ
				</td>
				<td colspan="2">
					ÿ��ƽ�������<u>&nbsp;
					<bean:write name='rs' property="mypjshf" />
					&nbsp;</u>Ԫ
				</td>
				<td colspan="3">
					�ϼ�ÿ�����<u>&nbsp;
					<bean:write name='rs' property="mnhjfy" />
					&nbsp;</u>Ԫ
				</td>
			</tr>
			<tr>
				<td colspan="9" scope="row">
					ÿ��μ�Ժ���ڹ���ѧ����<u>&nbsp;
					<bean:write name='rs' property="mncjynqgzxbt" />
					&nbsp;</u>Ԫ, &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; �μ�Ժ���ڹ���ѧ��񱨳�<u>&nbsp;
					<bean:write name='rs' property="cjywqgzxbc" />
					&nbsp;</u>Ԫ
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						�����˺��ͼ����ſ�
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="ywshhjbgk" />
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						�ˡ���ʱ��
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="sbsj" />
					</div>
				</td>
				<td width="10%">
					<div align="center">
						����ҽԺ
					</div>
				</td>
				<td width="20%">
					<div align="center">
						<bean:write name='rs' property="zzyy" />
					</div>
				</td>
				<td width="10%">
					<div align="center">
						ҽҩ��
					</div>
				</td>
				<td width="10%">
					<div align="center">
						<bean:write name='rs' property="yyf" />
					</div>
				</td>
				<td width="10%">
					<div align="center">
						��������
					</div>
				</td>
				<td width="10%">
					<div align="center">
						<bean:write name='rs' property="qtfy" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						�Ƿ�Ƿ��
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name='rs' property="sfqf" />
					</div>
				</td>
				<td colspan="3">
					��ͥ���������<u>&nbsp;
					<bean:write name='rs' property="jtbxlpk" />
					&nbsp;</u>Ԫ
				</td>
				<td colspan="3">
					<bean:message key="lable.xsgzyxpzxy" />���������<u>&nbsp;
					<bean:write name='rs' property="xybxlpk" />
					&nbsp;</u>Ԫ
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						��ͥ����״���������ˡ������Ѳ���������
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="jtjjzkjsqsbbzly" />
				</td>
			</tr>
			<tr>
				<td colspan="5" scope="row">
					<div align="right">
						���޼�ͥ���ڵش塢��(��ί���ְ�)���ߵ��й�֤��
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="ywzm" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						�����벹�����
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name='rs' property="nsqbzje" />
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						���������
					</div>
				</td>
				<td colspan="8">
					<br />
					<div align="right">
						ǩ�֣�
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />���
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="xyshyj" />
					<br />
					<div align="right">
						ǩ�֣�
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						ѧУ���
					</div>
				</td>
				<td colspan="8">
					<bean:write name='rs' property="xxshyj" />
					<br />
					<div align="right">
						ǩ�֣�
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						Ժ�쵼����
					</div>
				</td>
				<td colspan="8">
					<br />
					<div align="right">
						ǩ�֣�
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','������Ϣְҵ����<bean:message key="lable.xsgzyxpzxy" /> �ˡ������Ѳ��������')" />
	</div>
</body>
</html:html>
