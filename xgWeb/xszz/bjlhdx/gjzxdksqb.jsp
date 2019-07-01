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
	<html:form action="bjlhdx_xszz.do?method=gjzxdksqb" method="post">
		<table width="100%" id="theTable" border="0" height="90%">
			<tr>
				<td>
					<div align="center">
						<h3>
							<strong>��&nbsp;��&nbsp;��&nbsp;ѧ&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;��</strong>
						</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div align="right">
						�������:&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name='rs' property="sqsj" />
						&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%"  class="tbstyle">
						<tr>
							<td width="9%">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									ѧ��
								</div>
							</td>
							<td width="11%">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									��/ר/ר�ӱ�
								</div>
							</td>
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="pycc" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									ѧ��
								</div>
							</td>
							<td width="8%">
								<div align="center">
									<bean:write name='rs' property="xz" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
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
									�Ա�
								</div>
							</td>
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									��ѧ����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rxny" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ҵ����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="byny" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��Ч��ס
									<br />
									��ַ
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="jtzz" />
							</td>
							<td>
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="yddh" />
								</div>
								<br />
								<div align="center">
									<bean:write name='rs' property="gddh" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="yzbm" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ�������
									<br />
									(����ϸ��д)
								</div>
							</td>
							<td colspan="10">
								<bean:write name='rs' property="jtjjqk" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									���������Ŀ
									<br />
									�����(Ԫ)
								</div>
							</td>
							<td>
								<div align="center">
									ѧ�Ѵ���
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xfdkze" />
								</div>
							</td>
							<td>
								<div align="center">
									Ӧ��ѧ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="yjxf" />
								</div>
							</td>
							<td>
								<div align="center">
									����Ѵ���
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="shfdkze" />
								</div>
							</td>
							<td>
								<div align="center">
									�ϼ�
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="dkze" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�ҳ����
								</div>
							</td>
							<td colspan="10">
								<br />
								<br />
								<div align="right">
									�ҳ�ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ϵ�绰:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
<%--						<tr>--%>
<%--							<td colspan="11">--%>
<%--								<div align="center">--%>
<%--									��ѧ��ѧϰ�ɼ�--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									�γ�����--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="2">--%>
<%--								<div align="center">--%>
<%--									�ɼ�--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									�γ�����--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									�ɼ�--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj1_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="2">--%>
<%--								<div align="center">--%>
<%--									&nbsp;<bean:write name='rs' property="sxncj1_cj" />&nbsp;--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj2_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj2_cj" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj3_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="2">--%>
<%--								<div align="center">--%>
<%--									&nbsp;<bean:write name='rs' property="sxncj3_cj" />&nbsp;--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj4_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj4_cj" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj5_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="2">--%>
<%--								<div align="center">--%>
<%--									&nbsp;<bean:write name='rs' property="sxncj5_cj" />&nbsp;--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj6_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj6_cj" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj7_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="2">--%>
<%--								<div align="center">--%>
<%--									&nbsp;<bean:write name='rs' property="sxncj7_cj" />&nbsp;--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj8_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj8_cj" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj9_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="2">--%>
<%--								<div align="center">--%>
<%--									&nbsp;<bean:write name='rs' property="sxncj9_cj" />&nbsp;--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj10_mc" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="sxncj10_cj" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									��ѧ�����������Ŀ--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="2">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="rxlbjgkm" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									��ѧ��������ͨ����Ŀ--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							<td colspan="3">--%>
<%--								<div align="center">--%>
<%--									<bean:write name='rs' property="rxlbktgkm" />--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
						<tr>
							<td colspan="3">
								<div align="center">
									ѧ������<bean:message key="lable.xsgzyxpzxy" />��ѧ
									<br />
									�칫�Һ�ʵ�����£�
								</div>
							</td>
							<td colspan="8">
								<br />
								<br />
								<div align="right">
									������ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									�����μ�
									<br />
									��ί�����
								</div>
							</td>
							<td colspan="9">
								<br />
								<bean:write name='rs' property="fdyshyj" />
								<br />
								<div align="right">
									������ǩ��:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ϵ�绰:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:empty name="rs" property="fdshsj">
									&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="fdshsj">
										<bean:write name='rs' property="fdshsj" />
									</logic:notEmpty>
									&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />���
								</div>
							</td>
							<td colspan="9">
								<br />
								<logic:empty name="rs" property="xyshyj">
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xyshyj">
									<bean:write name='rs' property="xyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									<bean:message key="lable.xsgzyxpzxy" />�����쵼ǩ�֣����£�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:empty name="rs" property="xyshsj">
									&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="xyshsj">
										<bean:write name='rs' property="xyshsj" />
									</logic:notEmpty>
									&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									ѧУ���
								</div>
							</td>
							<td colspan="9">
								<br />
								<logic:empty name="rs" property="xxshyj">
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xxshyj">
									<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									�쵼ǩ�֣����£�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:empty name="rs" property="xxshsj">
									&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
									</logic:empty>
									<logic:notEmpty name="rs" property="xxshsj">
										<bean:write name='rs' property="xxshsj" />
									</logic:notEmpty>
									&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<div align="left">
						&nbsp;&nbsp;���˵��: 1.ѧ�����뱣֤������Ϣ��ʵ���󣬲���Ϳ�ģ�һʽ���ݡ�
						<br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.����ͥ���������Ҫע����ͥ�˿������������Դ�����˾����롣
					</div>
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
