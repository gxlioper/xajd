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
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td>
					<div align="center">
						<h3>
							<strong> ����ʦ��<bean:message key="lable.xsgzyxpzxy" />��Դ��������ѧ������Ϣ�ɼ��� </strong>
						</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr bgcolor="#9F9F9F">
							<td colspan="8">
								&nbsp;
								<strong>����˻��������</strong>
							</td>
						</tr>
						<tr>
							<td width="15%">
								<div align="center">
									����
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="10%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									��������
								</div>
							</td>
							<td width="13%">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td width="12%">
								<div align="center">
									ѧ��
								</div>
							</td>
							<td width="13%">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									���֤��
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ��ϸ��ַ
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtxxdz" />
								</div>
							</td>
							<td>
								<div align="center">
									��ͥ�绰
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jtdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�������ڵ�
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="hjszd" />
								</div>
							</td>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
							<td>
								<div align="center">
									�ƶ��绰
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="yddh" />
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr bgcolor="#9F9F9F">
							<td colspan="6">
								&nbsp;
								<strong>��ͬ�������Ϣ��</strong>
							</td>
						</tr>
						<tr>
							<td width="15%" rowspan="4">
								<div align="center">
									����
								</div>
							</td>
							<td width="20%" rowspan="4">
								<div align="center">
									<bean:write name='rs' property="gtjkr_xm" />
								</div>
							</td>
							<td width="10%" rowspan="4">
								<div align="center">
									���
									<br />
									����
									<br />
									��ϵ
								</div>
							</td>
							<td width="15%">
								<logic:empty name='rs' property="xh">
									&nbsp;��
								</logic:empty>
								<logic:notEmpty name='rs' property="xh">
									<logic:equal name='rs' property="gtjkr_gx" value="��">
										&nbsp;��
									</logic:equal>
									<logic:notEqual name='rs' property="gtjkr_gx" value="��">
										&nbsp;��
									</logic:notEqual>
								</logic:notEmpty>
								&nbsp;��
							</td>
							<td width="15%" rowspan="4">
								<div align="center">
									���֤����
								</div>
							</td>
							<td width="25%" rowspan="4">
								<div align="center">
									<bean:write name='rs' property="gtjkr_sfzh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<logic:empty name='rs' property="xh">
									&nbsp;��
								</logic:empty>
								<logic:notEmpty name='rs' property="xh">
									<logic:equal name='rs' property="gtjkr_gx" value="ĸ">
										&nbsp;��
									</logic:equal>
									<logic:notEqual name='rs' property="gtjkr_gx" value="ĸ">
										&nbsp;��
									</logic:notEqual>
								</logic:notEmpty>
								&nbsp;ĸ
							</td>
						</tr>
						<tr>
							<td>	
								<logic:empty name='rs' property="xh">
									&nbsp;��
								</logic:empty>
								<logic:notEmpty name='rs' property="xh">
									<logic:equal name='rs' property="gtjkr_gx" value="������">
										&nbsp;��
									</logic:equal>
									<logic:notEqual name='rs' property="gtjkr_gx" value="������">
										&nbsp;��
									</logic:notEqual>
								</logic:notEmpty>
								&nbsp;������
							</td>
						</tr>
						<tr>
							<td>
								<logic:empty name='rs' property="xh">
									&nbsp;��
								</logic:empty>
								<logic:notEmpty name='rs' property="xh">
									<logic:equal name='rs' property="gtjkr_gx" value="����">
										&nbsp;��
									</logic:equal>
									<logic:notEqual name='rs' property="gtjkr_gx" value="����">
										&nbsp;��
									</logic:notEqual>
								</logic:notEmpty>
								&nbsp;����
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									������λ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="gtjkr_gzdw" />
								</div>
							</td>
							<td>
								<div align="center">
									ְ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="gtjkr_zw" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ��ϸ��ַ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="gtjkr_jtxxdz" />
								</div>
							</td>
							<td>
								<div align="center">
									��ͥ�绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="gtjkr_jtdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�������ڵ�
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="gtjkr_hjszd" />
								</div>
							</td>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="gtjkr_yzbm" />
								</div>
							</td>
							<td>
								<div align="center">
									�ƶ��绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="gtjkr_yddh" />
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr bgcolor="#9F9F9F">
							<td colspan="10">
								&nbsp;
								<strong>��&nbsp;ѧ&nbsp;��&nbsp;Ϣ��</strong>
							</td>
						</tr>
						<tr>
							<td width="7%">
								<div align="center">
									ϵ��
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td width="7%">
								<div align="center">
									רҵ
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
							<td width="7%">
								<div align="center">
									�꼶
								</div>
							</td>
							<td width="7%">
								<div align="center">
									<bean:write name='rs' property="nj" />
								</div>
							</td>
							<td width="7%">
								<div align="center">
									�༶
								</div>
							</td>
							<td width="19%">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td width="7%">
								<div align="center">
									ѧ��
								</div>
							</td>
							<td width="7%">
								<div align="center">
									<bean:write name='rs' property="xz" />
								</div>
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr bgcolor="#9F9F9F">
							<td colspan="6">
								&nbsp;
								<strong>���������Ϣ��</strong>
							</td>
						</tr>
						<tr>
							<td rowspan="9" width="4%">
								<div align="center">
									��
									<br />
									&nbsp;
									<br />
									��
									<br />
									&nbsp;
									<br />
									��
									<br />
									&nbsp;
									<br />
									��
								</div>
							</td>
							<td width="15%">
								<logic:equal name='rs' property="knlx_dsr" value="��">
									&nbsp;��
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_dsr" value="��">
									&nbsp;��
								</logic:notEqual>
								&nbsp;������
							</td>
							<td colspan="4" rowspan="5">
								��ϸԭ��
								<br />
								<logic:empty name='rs' property="xxyy">
								<br />
								<br />
								<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xxyy">
									<bean:write name='rs' property="xxyy" />
								</logic:notEmpty>
								<br />
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_cnh" value="��">
									&nbsp;��
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_cnh" value="��">
									&nbsp;��
								</logic:notEqual>
								&nbsp;��ũ��
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_sxg" value="��">
									&nbsp;��
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_sxg" value="��">
									&nbsp;��
								</logic:notEqual>
								&nbsp;˫�¸�
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_dbh" value="��">
									&nbsp;��
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_dbh" value="��">
									&nbsp;��
								</logic:notEqual>
								&nbsp;�ͱ���
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_zbh" value="��">
									&nbsp;��
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_zbh" value="��">
									&nbsp;��
								</logic:notEqual>
								&nbsp;�ز���
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_wsr" value="��">
									&nbsp;��
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_wsr" value="��">
									&nbsp;��
								</logic:notEqual>
								&nbsp;������
							</td>
							<td width="13%" rowspan="2">
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="3" rowspan="2">
								<div align="center">
									<bean:write name='rs' property="dknx" />&nbsp;&nbsp;��
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_lszn" value="��">
									&nbsp;��
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_lszn" value="��">
									&nbsp;��
								</logic:notEqual>
								&nbsp;��ʿ��Ů
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_gr" value="��">
									&nbsp;��
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_gr" value="��">
									&nbsp;��
								</logic:notEqual>
								&nbsp;�¶�
							</td>
							<td rowspan="2">
								<div align="center">
									�����ܽ��
								</div>
							</td>
							<td rowspan="2" width="20%">
								<div align="center">
									<bean:write name='rs' property="dkzje" />&nbsp;&nbsp;Ԫ
								</div>
							</td>
							<td rowspan="2" width="20%">
								<div align="center">
									���ڷ���
									<br />
									����/���
								</div>
							</td>
							<td rowspan="2" width="28%">
								<div align="center">
									<bean:write name='rs' property="fqffcsje" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name='rs' property="knlx_qt" value="��">
									&nbsp;��
								</logic:equal>
								<logic:notEqual name='rs' property="knlx_qt" value="��">
									&nbsp;��
								</logic:notEqual>
								&nbsp;����
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jbyh" />
								</div>
							</td>
							<td>
								<div align="center">
									��Դ�ش�����ѧ
									<br />
									�����ͬ���
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="sydxyzxdkhtbh" />
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						����Ա( ǩ��):&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						���ڣ�<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>��
					</div>
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
