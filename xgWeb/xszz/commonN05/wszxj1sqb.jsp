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
			var zxjdm = document.getElementById('zxjdm').value;
			var xh = $('xh').value;
			var xn = $('xn').value;
			document.forms[0].action = "/xgxt/n05_xszz.do?method=wszxj1sq&xmdm="+zxjdm+"&xh="+xh+"&xn"+xn;
			document.forms[0].submit();
		}
	</script>
<body>
	<html:form action="zxdksq.do" method="post">
		<input type="hidden" id="zxjdm" name="zxjdm"
			value="<bean:write name="rs" property="zxjdm" />">
		<input type="hidden" id="xh" value="${rs.xh }"/>
		<input type="hidden" id="xn" value="${rs.xn }"/>
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong><bean:write name='rs' property="zxjmc" />�����</strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td height="40px">
					<br />
					<br />
					&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
					<bean:write name='rs' property="xymc" />
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr height="40px">
							<td width="15%">
								<div align="center">
									�༶
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td width="15%">
								<div align="center">
									ѧ��
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
						</tr>
						<tr height="40px">
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="15%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td>
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
						<tr height="40px">
							<td>
								<div align="center">
									����
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="jg" />
								</div>
							</td>
							<td>
								<div align="center">
									Ʒѧ���
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<bean:write name='rs' property="pxqk" />
								</div>
							</td>
						</tr>
						<tr height="40px">
							<td>
								<div align="center">
									��ͥ��ϸ��ַ
								</div>
							</td>
							<td colspan="3">
								<div align="center">
									<bean:write name='rs' property="jtxxdz" />
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
						<tr height="320px">
							<td>
								<div align="center">
									��
									<br />
									ͥ
									<br />
									��
									<br />
									ѧ
									<br />
									ϰ
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									��
									<br />
									ժ
									<br />
									Ҫ
								</div>
							</td>
							<td colspan="5">
								<br />
								<logic:empty name='rs' property="jtjxxqkknzy">
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
								<logic:notEmpty name='rs' property="jtjxxqkknzy">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs'
										property="jtjxxqkknzy" />
								</logic:notEmpty>
								<br />
								<br />
							</td>
						</tr>
						<tr height="80px">
							<td>
								<div align="center">
									��Ժ
									<br />
									��ϵ����
									<br />
									��
									<br />
									��
								</div>
							</td>
							<td colspan="5">
								<br />
								<logic:empty name='rs' property="xyshyj">
									<br /><br /><br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xyshyj">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs'
										property="xyshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr height="80px">
							<td>
								<div align="center">
									ѧ<br />Ժ<br />��<br />��
								</div>
							</td>
							<td colspan="5">
								<br />
								<logic:empty name='rs' property="xxshyj">
									<br /><br /><br />
								</logic:empty>
								<logic:notEmpty name='rs' property="xxshyj">
									&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name='rs' property="xxshyj" />
								</logic:notEmpty>
								<br />
								<div align="right">
									ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
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
