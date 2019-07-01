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
<script language="javascript">

	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<table width="100%" border="0" id="theTable" height="90%">
			<tr>
				<td scope="col">
					<div align="center">
						<strong>�й����к���ʡ����</strong>
					</div>
					<div align="center">
						<strong>������ѧ�����ҵ��(�����)����ȷ����</strong>
					</div>
				</td>
			</tr>
			<tr>
				<td scope="col">
					&nbsp;<br />&nbsp;
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle" height="60%">
						<tr>
							<td colspan="3">
								<strong>����˻�������</strong>
							</td>
						</tr>
						<tr>
							<td width="40%">
								���������&nbsp;
								<bean:write name="rs" property="xm" />
							</td>
							<td width="30%">
								�Ա�&nbsp;&nbsp;
								<logic:empty name="rs" property="xh">
								��&nbsp;��&nbsp;&nbsp;��&nbsp;Ů
								</logic:empty>
								<logic:notEmpty name="rs" property="xh">
									<logic:equal name="rs" property="xb" value="��">
									��
									</logic:equal>
									<logic:notEqual name="rs" property="xb" value="��">
									��
									</logic:notEqual>
									&nbsp;��&nbsp;&nbsp;
									<logic:equal name="rs" property="xb" value="Ů">
									��
									</logic:equal>
									<logic:notEqual name="rs" property="xb" value="Ů">
									��
									</logic:notEqual>
									&nbsp;Ů
								</logic:notEmpty>
							</td>
							<td width="30%">
								��������&nbsp;&nbsp;
								<logic:equal name="rs" property="csrqYear" value="">
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal>
								<logic:notEqual name="rs" property="csrqYear" value="">
								<bean:write name="rs" property="csrqYear" />
								</logic:notEqual>
								��
								<logic:equal name="rs" property="csrqMon" value="">
								&nbsp;&nbsp;
								</logic:equal>
								<logic:notEqual name="rs" property="csrqMon" value="">
								<bean:write name="rs" property="csrqMon" />
								</logic:notEqual>
								��
								<logic:equal name="rs" property="csrqDay" value="">
								&nbsp;&nbsp;
								</logic:equal>
								<logic:notEqual name="rs" property="csrqDay" value="">
								<bean:write name="rs" property="csrqDay" />
								</logic:notEqual>
								��
							</td>
						</tr>
						<tr>
							<td>
								��ҵѧУ&nbsp;
								�й����ʴ�ѧ(�人)
							</td>
							<td>
								���֤����&nbsp;
								<bean:write name="rs" property="sfzh" />
							</td>
							<td>
								ѧ��&nbsp;
								<bean:write name="rs" property="xl" />
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />&nbsp;
								<bean:write name="rs" property="xymc" />
							</td>
							<td>
								�������&nbsp;
								<bean:write name="rs" property="bjdm" />
							</td>
							<td>
								ѧ��&nbsp;
								<bean:write name="rs" property="xh" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								��ͥסַ&nbsp;
								<bean:write name="rs" property="jtxxzz" />
							</td>
							<td>
								��ͥ��ϵ�绰&nbsp;
								<bean:write name="rs" property="jtdh" />
							</td>
						</tr>
						<tr>
							<td>
								����״��&nbsp;
								<logic:notEmpty name="rs" property="xh">
									<bean:write name="rs" property="hyzk" />
								</logic:notEmpty>
							</td>
							<td>
								��ż����&nbsp;
								<bean:write name="rs" property="pomc" />
							</td>
							<td>
								��ϵ�绰&nbsp;
								<bean:write name="rs" property="polxdh" />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								��λ��ַ&nbsp;
								<bean:write name="rs" property="dwdz" />
							</td>
							<td>
								����&nbsp;
								<bean:write name="rs" property="mzmc" />
							</td>
						</tr>
						<tr>
							<td>
								������λ&nbsp;
								<bean:write name="rs" property="gzdw" />
							</td>
							<td>
								��λ�绰&nbsp;
								<bean:write name="rs" property="dwdh" />
							</td>
							<td>
								��λ�ʱ�&nbsp;
								<bean:write name="rs" property="dwyb" />
							</td>
						</tr>
						<tr>
							<td>
								����ͬ���&nbsp;
								<bean:write name="rs" property="htbh" />
							</td>
							<td>
								Email��QQ&nbsp;
								<bean:write name="rs" property="emailqq" />
							</td>
							<td>
								�ƶ��绰&nbsp;
								<bean:write name="rs" property="yddh" />
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<strong>��ϵ�˻�������</strong>
							</td>
						</tr>
						<tr>
							<td>
								��ϵ������&nbsp;
								<bean:write name="rs" property="lxrxm" />
							</td>
							<td>
								�Ա�&nbsp;&nbsp;
								<logic:empty name="rs" property="lxrxm">
								��&nbsp;��&nbsp;&nbsp;��&nbsp;Ů
								</logic:empty>
								<logic:notEmpty name="rs" property="lxrxm">
									<logic:equal name="rs" property="lxrxb" value="��">
									��
									</logic:equal>
									<logic:notEqual name="rs" property="lxrxb" value="��">
									��
									</logic:notEqual>
									&nbsp;��&nbsp;&nbsp;
									<logic:equal name="rs" property="lxrxb" value="Ů">
									��
									</logic:equal>
									<logic:notEqual name="rs" property="lxrxb" value="Ů">
									��
									</logic:notEqual>
									&nbsp;Ů
								</logic:notEmpty>
							</td>
							<td>
								��������&nbsp;&nbsp;
								<logic:equal name="rs" property="lxrcsrqYear" value="">
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal>
								<logic:notEqual name="rs" property="lxrcsrqYear" value="">
								<bean:write name="rs" property="lxrcsrqYear" />
								</logic:notEqual>
								��
								<logic:equal name="rs" property="lxrcsrqMon" value="">
								&nbsp;&nbsp;
								</logic:equal>
								<logic:notEqual name="rs" property="lxrcsrqMon" value="">
								<bean:write name="rs" property="lxrcsrqMon" />
								</logic:notEqual>
								��
								<logic:equal name="rs" property="lxrcsrqDay" value="">
								&nbsp;&nbsp;
								</logic:equal>
								<logic:notEqual name="rs" property="lxrcsrqDay" value="">
								<bean:write name="rs" property="lxrcsrqDay" />
								</logic:notEqual>
								��
							</td>
						</tr>
						<tr>
							<td>
								�����˹�ϵ&nbsp;
								<bean:write name="rs" property="lxrgx" />
							</td>
							<td colspan="2">
								��ϵ�绰&nbsp;
								<bean:write name="rs" property="lxrlxdh" />
							</td>
						</tr>
						<tr>
							<td colspan="3">
								��λ/��ַ&nbsp;
								<bean:write name="rs" property="lxrdwdz" />
							</td>
						</tr>
					</table>
					<table width="100%" class="tbstyle">
						<tr>
							<td width="50%">
								<strong>��ͥ�������</strong>
								<br />
								��ͥ��ϸסַ��&nbsp;
								<bean:write name="rs" property="jtxxzz" />
								<br />
								�ʱࣺ&nbsp;
								<bean:write name="rs" property="jtyb" />
								<br />
								�绰��&nbsp;
								<bean:write name="rs" property="jtdh" />
								<br />
								����������&nbsp;
								<logic:empty name="rs" property="fqxm" >
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="fqxm" >
								<bean:write name="rs" property="fqxm" />
								</logic:notEmpty>
								&nbsp;ְҵ��&nbsp;
								<bean:write name="rs" property="fqzy" />
								<br />
								�������֤���룺&nbsp;
								<bean:write name="rs" property="fqsfzh" />
								<br />
								ĸ��������&nbsp;
								<logic:empty name="rs" property="mqxm" >
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="mqxm" >
								<bean:write name="rs" property="mqxm" />
								</logic:notEmpty>
								&nbsp;ְҵ��&nbsp;
								<bean:write name="rs" property="mqzy" />
								<br />
								ĸ�����֤���룺&nbsp;
								<bean:write name="rs" property="mqsfzh" />
								<br />
							</td>
							<td width="50%">
								&nbsp;&nbsp;&nbsp;&nbsp;�����ڴ˳�ŵ����������ʵ�������б䶯�����˳�ŵ�ڱ䶯��һ���ڽ��䶯�����ʼ����й�����&nbsp;&nbsp;
								<strong>����������</strong>&nbsp;��/֧�С����ṩ������ϻ�δ�ܼ�ʱ���ͱ�����ϣ�������Ȩ�϶�����ΥԼ�����ɰ��ա��й����й�����ѧ�������ͬ���е����Լ��׷�����˵�ΥԼ���Ρ�
								<br />
								<br />
								<br />
								<div align="center">
									��ŵ��(ǩ��)��
								</div>
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								��ע:<br />
								<logic:empty name="rs" property="bz" >
								<br /><br />
								</logic:empty>
								<logic:notEmpty name="rs" property="bz" >
								&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="rs" property="bz" />
								</logic:notEmpty>
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
	</div>
</body>
</html:html>
