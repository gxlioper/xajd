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
	<style type="text/css">
	<!--
	.style1 {font-family: "�����п�"}
	-->
	</style>
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
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h1 class="style1">
						<strong>
								������Ϣְҵ����<bean:message key="lable.xsgzyxpzxy" />
						</strong>
							</h1> 
					</div>
					<div align="center">
							<h2>
						<strong>
								������ѧ���걨������
						</strong>
							</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="center">
						ϵ(��)<u>&nbsp;
						<bean:write name='rs' property="zymc" />
						&nbsp;&nbsp;</u>�༶<u>&nbsp;
						<bean:write name='rs' property="bjmc" />
						&nbsp;&nbsp;</u>����<u>&nbsp;&nbsp;
						<bean:write name='rs' property="xm" />
						&nbsp;&nbsp;</u>�Ա�<u>&nbsp;
						<bean:write name='rs' property="xb" />
						&nbsp;&nbsp;</u>��������<u>&nbsp;
						<bean:write name='rs' property="sqsj_year" />
						��&nbsp;
						<bean:write name='rs' property="sqsj_mon" />
						��&nbsp;
						<bean:write name='rs' property="sqsj_day" />
						��</u>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<table width="100%" class="tbstyle">
						<tr>
							<td colspan="2" scope="col">
								<div align="center">
									��ͥ��ַ
								</div>
							</td>
							<td colspan="5" scope="col">
								<bean:write name='rs' property="jtdz" />
							</td>
							<td scope="col" width="14%">
								<div align="center">
									��ͥ�绰
								</div>
							</td>
							<td scope="col" width="14%">
								<div align="center">
									<bean:write name='rs' property="jtdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									������ò
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									����
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									����ְ��
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name='rs' property="btzw" />
								</div>
							</td>
							<td>
								<div align="center">
									ѧ���绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xsdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									���֤����
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									ѧ��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="6" scope="row" width="4%">
								<div align="center">
									��
									<br />
									ͥ
									<br />
									��
									<br />
									Ա
								</div>
							</td>
							<td scope="row" width="12%">
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									��ν
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									����״��
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									����(ѧϰ)��λ��ְ��
								</div>
							</td>
							<td>
								<div align="center">
									������(Ԫ)
								</div>
							</td>
						</tr>
						<tr>
							<td scope="row" width="10%">
								<div align="center">
									<bean:write name='rs' property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy1_jkzk" />
								</div>
							</td>
							<td colspan="3">
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
							<td scope="row" width="10%">
								<div align="center">
									<bean:write name='rs' property="jtcy2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy2_jkzk" />
								</div>
							</td>
							<td colspan="3">
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
							<td scope="row" width="10%">
								<div align="center">
									<bean:write name='rs' property="jtcy3_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy3_jkzk" />
								</div>
							</td>
							<td colspan="3">
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
							<td scope="row" width="10%">
								<div align="center">
									<bean:write name='rs' property="jtcy4_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy4_jkzk" />
								</div>
							</td>
							<td colspan="3">
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
							<td scope="row" width="10%">
								<div align="center">
									<bean:write name='rs' property="jtcy5_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_cw" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_nl" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtcy5_jkzk" />
								</div>
							</td>
							<td colspan="3">
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
							<td colspan="4" scope="row">
								ÿ��Ӧ�ɸ��ַ���<u>&nbsp;
								<bean:write name='rs' property="nyyjngzfy" />
								&nbsp;</u>Ԫ
							</td>
							<td colspan="3">
								��ͥÿ���ṩ<u>&nbsp;
								<bean:write name='rs' property="jtmntg" />
								&nbsp;</u>Ԫ
							</td>
							<td colspan="2">
								�ϼ�ÿ����ȱ����<u>&nbsp;
								<bean:write name='rs' property="hjmnsqfy" />
								&nbsp;</u>Ԫ
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									��ͥ������
									<br />
									�Ѿ������
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="jtjjknqk" />
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									Ƿ��ѧ����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="qjxfs" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									�Ѵ������༰���
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="yhdkzljje" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									������ѧ��
									<br />
									�����ɽ��
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="sqly" />
								<div align="right">
								������:<u>&nbsp;
								<bean:write name='rs' property="xssqdjje" />&nbsp;</u>Ԫ&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									��ͥ����
									<br />
									�������
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									�¶���
									<bean:write name='rs' property="sfgr" />
									����ʿ��Ů��
									<bean:write name='rs' property="sflszn" />
									�������뻧��
									<bean:write name='rs' property="sfwsrh" />
									���ز�����
									<bean:write name='rs' property="sfzbh" />
									���ͱ�����
									<bean:write name='rs' property="sfdbh" />
									<br />
									��ĸ˫�¸ڣ�
									<bean:write name='rs' property="sffmsxg" />
									����ũ����
									<bean:write name='rs' property="sfcnh" />
									��������(��ͥ���벻����֧����ѧ��������)��
									<bean:write name='rs' property="sfdsr" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" rowspan="5" scope="row">
								<div align="center">
									���������
								</div>
							</td>
							<td colspan="7">
								ƽʱ���֣�
							</td>
						</tr>
						<tr>
							<td colspan="7">
								���������
							</td>
						</tr>
						<tr>
							<td colspan="7">
								���޼�ͥ���ڵ�����(�ְ�)�������ų��ߵļ�ͥ��������֤�����С�&nbsp;�ޡ�
							</td>
						</tr>
						<tr>
							<td colspan="7">
								���϶�&nbsp;������𡢳̶ȣ�
							</td>
						</tr>
						<tr>
							<td colspan="7">
								<div align="right">
									ͬ������&nbsp;&nbsp;&nbsp;&nbsp;Ԫ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									ϵ������
									<br />
									���
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="xyshyj" />
								<div align="right">
									ͬ������&nbsp;
									<bean:write name='rs' property="xytysqje" />
									&nbsp;Ԫ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������&nbsp;&nbsp;ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />������
									<br />
									��������
									<br />
									���
								</div>
							</td>
							<td colspan="7">
								<br />
								<br />
								<div align="right">
									ͬ������&nbsp;&nbsp;&nbsp;&nbsp;Ԫ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;������&nbsp;&nbsp;ǩ�֣�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />��
									<br />
									���
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="xxshyj" />
								<div align="right">
									ͬ������&nbsp;
									<bean:write name='rs' property="xxtyshje" />
									&nbsp;Ԫ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />���£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2" scope="row">
								<div align="center">
									��ע
								</div>
							</td>
							<td colspan="7">
								<bean:write name='rs' property="bz" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td scope="row">
					&nbsp;&nbsp;&nbsp;&nbsp;Ӧ������֤��(��ӡ��)
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>
