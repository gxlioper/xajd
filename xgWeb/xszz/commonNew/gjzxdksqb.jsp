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
							<h2>
						<strong>
								������ѧ���������
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="right">
						�������:&nbsp;&nbsp;&nbsp;&nbsp;
						<logic:empty name="rs" property="sqsj">
					&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
					</logic:empty>
						<logic:notEmpty name="rs" property="sqsj">
							<bean:write name='rs' property="sqsj" />
						</logic:notEmpty>
						&nbsp;&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<table width="100%" class="tbstyle">
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
									<logic:empty name="rs" property="xh">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<logic:equal name="rs" property="xsxz" value="����">
											����
										</logic:equal>
										<logic:equal name="rs" property="xsxz" value="ר��">
											ר��
										</logic:equal>
										<logic:equal name="rs" property="xsxz" value="ר�ӱ�">
											ר�ӱ�
										</logic:equal>
									</logic:notEmpty>
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
									��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="csrq" />
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
								<bean:write name='rs' property="yxjzdz" />
							</td>
							<td>
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="lxdh" />
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
								<logic:empty name="rs" property="jtjjqk">
									<br /><br /><br /><br /><br />
								</logic:empty>
								<logic:notEmpty name="rs" property="jtjjqk">
									<bean:write name='rs' property="jtjjqk" />
								</logic:notEmpty>
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
									<logic:empty name="rs" property="xh">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<bean:write name='rs' property="xfdk" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									Ӧ��ѧ��
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="xh">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<bean:write name='rs' property="yjxf" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									����Ѵ���
								</div>
							</td>
							<td>
								<div align="center">
									<logic:empty name="rs" property="xh">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<bean:write name='rs' property="shfdk" />
									</logic:notEmpty>
								</div>
							</td>
							<td>
								<div align="center">
									�ϼ�
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:empty name="rs" property="xh">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="xh">
										<bean:write name='rs' property="hj" />
									</logic:notEmpty>
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
						<tr>
							<td colspan="11">
								<div align="center">
									��ѧ��ѧϰ�ɼ�
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									�γ�����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									�ɼ�
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									�γ�����
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									�ɼ�
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj1_mc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									&nbsp;<bean:write name='rs' property="sxncj1_cj" />&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj2_mc" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj2_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj3_mc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									&nbsp;<bean:write name='rs' property="sxncj3_cj" />&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj4_mc" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj4_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj5_mc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									&nbsp;<bean:write name='rs' property="sxncj5_cj" />&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj6_mc" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj6_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj7_mc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									&nbsp;<bean:write name='rs' property="sxncj7_cj" />&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj8_mc" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj8_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj9_mc" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									&nbsp;<bean:write name='rs' property="sxncj9_cj" />&nbsp;
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj10_mc" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sxncj10_cj" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<div align="center">
									��ѧ�����������Ŀ
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="rxlbjgkm" />
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									��ѧ��������ͨ����Ŀ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="rxlbktgkm" />
								</div>
							</td>
						</tr>
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
									<bean:message key="lable.xsgzyxpzxy" />���
								</div>
							</td>
							<td colspan="9">
								<br />
								<br />
								<br />
								<div align="right">
									<bean:message key="lable.xsgzyxpzxy" />�����쵼ǩ�֣����£�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
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
								<br />
								<br />
								<div align="right">
									�쵼ǩ�֣����£�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<div align="left">
					&nbsp;&nbsp;���˵��:��1.ѧ�����뱣֤������Ϣ��ʵ���󣬲���Ϳ�ġ�<br />
             		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.����ͥ���������Ҫע����ͥ�˿������������Դ�����˾����롣
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
