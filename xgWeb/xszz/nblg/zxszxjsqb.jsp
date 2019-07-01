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
<script language="javascript">
		function back(){
			document.forms[0].action = "/xgxt/nblg_xszz.do?method=zxszxjsq";
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="nblg_xszz.do?method=zxszxjsqb" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
							<h3>
						<strong>
								�����д����ܻ��У��У����ѧ�걨��
						</strong>
							</h3>
					</div>
				</td>
			</tr>
			<tr><td align="right">��ţ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="12%">
								<div align="center">
									ѧ������
								</div>
							</td>
							<td width="28%">
								<div align="center">
									<bean:write name="rs" property="xm" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									��������
								</div>
							</td>
							<td width="14%">
								<div align="center">
									<bean:write name="rs" property="csny" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="8%">
								<div align="center">
									<bean:write name="rs" property="xb" />
								</div>
							</td>
							<td width="8%">
								<div align="center">
									����
								</div>
							</td>
							<td width="12%">
								<div align="center">
									<bean:write name="rs" property="jg" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									���ԺУ��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="xxmc" />
								</div>
							</td>
							<td>
								<div align="center">
									�꼶��רҵ
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<logic:notEmpty name="rs" property="nj">
										<bean:write name="rs" property="nj" />��
									&nbsp;
									</logic:notEmpty>
									<br />
									<bean:write name="rs" property="zymc" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ��ϸ
									<br />
									סַ
								</div>
							</td>
							<td colspan="7">
								<div align="center">
									<bean:write name="rs" property="jtzz" />
								</div>
							</td>
						</tr>
						<!--��������һ-->
						<logic:notEqual value="13742" name="xxdm">
						<tr>
							<td rowspan="6">
								<div align="center">
									��ͥ��Ա��
									<br />
									��������
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									��ν
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									��λ
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
									<bean:write name="rs" property="jtcy1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
									<bean:write name="rs" property="jtcy1_cw" />
									&nbsp;
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name="rs" property="jtcy1_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy1_sr" />
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
									&nbsp;
									<bean:write name="rs" property="jtcy2_cw" />
									&nbsp;
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name="rs" property="jtcy2_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy2_sr" />
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
									&nbsp;
									<bean:write name="rs" property="jtcy3_cw" />
									&nbsp;
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name="rs" property="jtcy3_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy3_sr" />
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
									&nbsp;
									<bean:write name="rs" property="jtcy4_cw" />
									&nbsp;
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name="rs" property="jtcy4_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy4_sr" />
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
									&nbsp;
									<bean:write name="rs" property="jtcy5_cw" />
									&nbsp;
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name="rs" property="jtcy5_gz" />
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name="rs" property="jtcy5_sr" />
								</div>
							</td>
						</tr>
						</logic:notEqual>
						<!--������һ-->
						<logic:equal value="13742" name="xxdm">
							<tr>
							<td rowspan="5">
								<div align="center">
									��ͥ��Ա��
									<br />
									��������
								</div>
							</td>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									��ν
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									��λ
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
									&nbsp;${rs.jtcy1_xm}
								</td>
								<td>
									&nbsp;${rs.jtcy1_gx}
								</td>								
								<td colspan="4">
									&nbsp;${rs.jtcy1_gzdwmc}
								</td>
								<td>
									&nbsp;${rs.jtcy1_ysr}
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;${rs.jtcy2_xm}
								</td>
								<td>
									&nbsp;${rs.jtcy2_gx}
								</td>
								<td colspan="4">
									&nbsp;${rs.jtcy2_gzdwmc}
								</td>
								<td>
									&nbsp;${rs.jtcy2_ysr}
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;${rs.jtcy3_xm}
								</td>
								<td>
									&nbsp;${rs.jtcy3_gx}
								</td>
								<td colspan="4">
									&nbsp;${rs.jtcy3_gzdwmc}
								</td>
								<td>
									&nbsp;${rs.jtcy3_ysr}
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
								<td colspan="4">
									&nbsp;
								</td>
								<td>
									&nbsp;
								</td>
							</tr>
						</logic:equal>
						<tr>
							<td colspan="2">
								<div align="center">
									ѧ���������֤����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="sfzh" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name="rs" property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��������
								</div>
							</td>
							<td colspan="7">
								<br />
								<logic:empty name="rs" property="sqly">
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
								<div align="right">
									�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�ӣ��壩ί
									<br />
									�������
									<br />
									��λ���
								</div>
							</td>
							<td colspan="2">
								<br />
								<br />
								<br />
								<br />
								<div align="right">
									(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
							<td>
								<div align="center">
									�ֵ������磩
									<br />
									�����ܲ���
									<br />
									���
								</div>
							</td>
							<td colspan="4">
								<br />
								<br />
								<br />
								<br />
								<div align="right">
									(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									ԺУ
									<br />
									���
									<br />
									���
								</div>
							</td>
							<td colspan="7">
								<br />
								<logic:empty name="rs" property="xxshyj">
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xxshyj">
									<bean:write name="rs" property="xxshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									�д����ܻ�
									<br />
									�������
								</div>
							</td>
							<td colspan="7">
								<br />
								<br />
								<br />
								<br />
								<br />
								<div align="right">
									(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="����" onclick="back();" />
	</div>
</body>
</html:html>
