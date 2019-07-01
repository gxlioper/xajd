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
			document.forms[0].action = "/xgxt/zjjdzyjsxy_xszz.do?method=gjzxdksq";
			document.forms[0].submit();
		}
	</script>
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
				<td>
					<logic:empty name="rs" property="sqsj">
						<div align="right">
							�������:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
						</div>
					</logic:empty>
					<logic:notEmpty name="rs" property="sqsj">
						<div align="right">
							�������:&nbsp;&nbsp;
							<bean:write name='rs' property="sqsj" />
							&nbsp;&nbsp;
						</div>
					</logic:notEmpty>
				</td>
			</tr>
			<tr>
				<td scope="col">
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
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="9%">
								<div align="center">
									ѧ��
								</div>
							</td>
							<td width="17%">
								<div align="center">
									<bean:write name='rs' property="xz" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									רҵ
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="zymc" />
								</div>
							</td>
							<td>
								<div align="center">
									�༶
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
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
							<td width="9%">
								<div align="center">
									<bean:write name='rs' property="rxny" />
								</div>
							</td>
							<td>
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
									��Ч��ͥ
									<br />
									��ַ
								</div>
							</td>
							<td colspan="3">
								<bean:write name='rs' property="yxjtzz" />
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
							<td>
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
									�ҳ�����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jz1_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jz1_lxdh" />
								</div>
							</td>
							<td>
								<div align="center">
									�ҳ�����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jz2_xm" />
								</div>
							</td>
							<td>
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jz2_lxdh" />
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
							<td colspan="9">
								<br />
								<logic:empty name="rs" property="jtjjqk">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="jtjjqk">
									<bean:write name='rs' property="jtjjqk" />
								</logic:notEmpty>
								<br />
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									���������
									<br />
									������ѧ��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									������
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="xfdk" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									Ӧ��ѧ��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="yjxf" />
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
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="rxlbktgkm" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									���������
								</div>
							</td>
							<td colspan="8">
								<logic:equal name="rs" property="fdysh" value="δ���">
									��˽����
								</logic:equal>
								<logic:notEqual name="rs" property="fdysh" value="δ���">
									��˽����<bean:write name='rs' property="fdysh" />
								</logic:notEqual>
								<br />
								<br />
								<logic:empty name="rs" property="fdyshyj">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="fdyshyj">
									<bean:write name='rs' property="fdyshyj" />
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									ǩ�֣����£�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									ϵ���
								</div>
							</td>
							<td colspan="8">
								<logic:equal name="rs" property="xysh" value="δ���">
									��˽����
								</logic:equal>
								<logic:notEqual name="rs" property="xysh" value="δ���">
									��˽����<bean:write name='rs' property="xysh" />
								</logic:notEqual>
								<br />
								<br />
								<logic:empty name="rs" property="xyshyj">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xyshyj">
									<bean:write name='rs' property="xyshyj" />
								</logic:notEmpty>
								<br />
								<br />
								<div align="right">
									ǩ�֣����£�:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />���
								</div>
							</td>
							<td colspan="8">
								<logic:equal name="rs" property="xxsh" value="δ���">
									��˽����
								</logic:equal>
								<logic:notEqual name="rs" property="xxsh" value="δ���">
									��˽����<bean:write name='rs' property="xxsh" />
								</logic:notEqual>
								<br />
								<br />
								<logic:empty name="rs" property="xxshyj">
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xxshyj">
									<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
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
	<div align="center">
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','')" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input  value="����" onclick="back();" />
	</div>
</body>
</html:html>
