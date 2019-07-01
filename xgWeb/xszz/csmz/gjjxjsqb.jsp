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
	<html:form action="csmz_xszz.do?method=jtjjqkdcb" method="post">
		<table width="100%" id="theTable" border="0" height="90%">
			<tr>
				<td>
					<div align="center">
						<h3>
							<strong>��ͨ�ߵ�ѧУ���ҽ�ѧ�������</strong>
						</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						(
						<logic:empty name="rs" property="xn">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="xn">
						<bean:write name='rs' property="xn" />
						</logic:notEmpty>
						&nbsp;ѧ��)
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						ѧУ��<bean:write name="xxmc" scope="session"/>&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
						<logic:empty name="rs" property="xymc">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="xymc">
							<bean:write name='rs' property="xymc" />
						</logic:notEmpty>
						&nbsp;&nbsp;רҵ��
						<logic:empty name="rs" property="zymc">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="zymc">
							<bean:write name='rs' property="zymc" />
						</logic:notEmpty>
						&nbsp;&nbsp;�༶��
						<logic:empty name="rs" property="bjmc">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:empty>
						<logic:notEmpty name="rs" property="bjmc">
							<bean:write name='rs' property="bjmc" />
						</logic:notEmpty>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle" height="1500px">
						<tr>
							<td rowspan="4" width="4%">
								<div align="center">
									��
									<br />
									��
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td width="24%">
								<div align="center">
									ѧ������
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="csny" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ѧ��
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									����
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									��ѧ����
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="rxny" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									���֤��
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh1" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh2" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh3" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh4" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh5" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh6" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh7" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh8" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh9" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh10" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh11" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh12" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh13" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh14" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh15" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh16" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh17" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name="rs" property="sfzh18" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									������ò
								</div>
							</td>
							<td colspan="5">
								<div align="center">
									<bean:write name="rs" property="zzmmmc" />
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td colspan="9">
								<div align="center">
									<bean:write name="rs" property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ѧ
									<br />
									��
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="19">
								<br />
								��ѧ����޿γ���
								<logic:empty name="rs" property="gxnbxkcs">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="gxnbxkcs">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="gxnbxkcs" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								��,���У�����
								<logic:empty name="rs" property="yxkc">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="yxkc">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="yxkc" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								�ţ�����
								<logic:empty name="rs" property="lhkc">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="lhkc">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="lhkc" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								��
								<br />
								<br />
								�ɼ�����(רҵ���꼶)��
								<logic:empty name="rs" property="cjpm">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="cjpm">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="cjpm" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								(����/����)
								<br />
								<br />
								�ۺϿ����ɼ�
								<logic:empty name="rs" property="zhkpcj">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="zhkpcj">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="zhkpcj" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								�֣�����
								<logic:empty name="rs" property="zhkppm">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="zhkppm">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="zhkppm" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								(����/����)(���޴����д��)
								<br />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��
									<br />
									��
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="19">
								<br />
								��Ҫ���
								<br />
								<logic:empty name="rs" property="zyjx">
								<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="zyjx">
								<bean:write name="rs" property="zyjx" />
								</logic:notEmpty>
								<br />
								���У�Ժ������
								<logic:empty name="rs" property="yjjx">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="yjjx">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="yjjx" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								�У������
								<logic:empty name="rs" property="xjjx">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="xjjx">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="xjjx" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								�ʡ�����Ͻ���
								<logic:empty name="rs" property="sjjx">
								<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
								</logic:empty>
								<logic:notEmpty name="rs" property="sjjx">
								<u>&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="sjjx" />&nbsp;&nbsp;&nbsp;</u>
								</logic:notEmpty>
								��
								<br />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									ȫ
									<br />
									��
									<br />
									��
									<br />
									ӳ
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="19">
								<br />
								<logic:empty name="rs" property="sqly">
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="sqly">
									<bean:write name="rs" property="sqly" />
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									������ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��
									<br />
									��
									<br />
									��
									<br />
									ר
									<br />
									ҵ
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="19">
								<br />
								<br />
								<br />
								<div align="right">
									�Ƽ��ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����ְ��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									Ժ
									<br />
									��
									<br />
									ϵ
									<br />
									��
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="19">
								<br />
								<br />
								<br />
								<div align="right">
									(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ѧ
									<br />
									У��
									<br />
									��
								</div>
							</td>
							<td colspan="19">
								<br />
								&nbsp;&nbsp;&nbsp;&nbsp;�����󣬲���___________��Χ�ڹ�ʾ______�죬�����飬�ֱ���ͬ���ͬѧ���_______ѧ��ȹ��ҽ�ѧ��
								<br />
								<div align="right">
									(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>
