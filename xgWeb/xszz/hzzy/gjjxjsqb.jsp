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
		function back(){
			document.forms[0].action = "/xgxt/hzzyjsxy_xszz.do?method=gjjxjsq";
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
								����ְҵ����<bean:message key="lable.xsgzyxpzxy" />���ҽ�ѧ������ѧ�����������
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
					<br />
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="5" width="6%">
								<div align="center">
									ѧ
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									Ϣ
								</div>
							</td>
							<td width="12%">
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
							<td width="13%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td width="15%">
								<div align="center">
									��������
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td width="15%" rowspan="5">
								<div align="center">
									��Ƭ
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="mzmc" />
								</div>
							</td>
							<td>
								<div align="center">
									����
									<br />
									��ò
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="zzmmmc" />
								</div>
							</td>
							<td>
								<div align="center">
									��ѧʱ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rxny" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									���֤����
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
							<td>
								<div align="center">
									��ϵ�绰
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="lxdh" />
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div align="center">
									<logic:empty name="rs" property="xymc">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="xymc">
									<bean:write name='rs' property="xymc" />&nbsp;
									</logic:notEmpty>
									<bean:message key="lable.xsgzyxpzxy" />
									<logic:empty name="rs" property="zymc">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="zymc">
									&nbsp;&nbsp;<bean:write name='rs' property="zymc" />&nbsp;
									</logic:notEmpty>
									רҵ
									<logic:empty name="rs" property="bjmc">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name="rs" property="bjmc">
									&nbsp;&nbsp;<bean:write name='rs' property="bjmc" />&nbsp;
									</logic:notEmpty>
									�༶
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									���
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="nd" />
								</div>
							</td>
							<td colspan="2">
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
							<td colspan="8">
								<div align="left">
									ѧ�������������(��ѧ������ϵ(Ժ)����):
								</div>
								<br />
								<logic:empty name="rs" property="xsjbqk">
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
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								</logic:empty>
								<logic:notEmpty name="rs" property="xsjbqk">
								<bean:write name='rs' property="xsjbqk" />
								</logic:notEmpty>
								<br />
								<div align="right">
									ϵ(Ժ)����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<div align="left">
									ѧ����(ѧ����)���:
								</div>
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
									(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<tr>
							<td colspan="8">
								<div align="left">
									<bean:message key="lable.xsgzyxpzxy" />�������:
								</div>
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
									(����)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
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
