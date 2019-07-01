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
						<strong>
								����ʦ��<bean:message key="lable.xsgzyxpzxy" />������־��ѧ�������
						</strong>
							</h3>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="tbstyle">
						<tr>
							<td rowspan="5" scope="col" width="4%">
								<div align="center">
									�������
								</div>
							</td>
							<td scope="col" width="14%">
								<div align="center">
									����
								</div>
							</td>
							<td scope="col" width="14%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									�Ա�
								</div>
							</td>
							<td scope="col" width="12%">
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td scope="col" width="14%">
								<div align="center">
									��������
								</div>
							</td>
							<td scope="col" width="14%">
								<div align="center">
									<bean:write name='rs' property="csrq" />
								</div>
							</td>
							<td width="16%" rowspan="4" scope="col">
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
									������ò
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
									<bean:write name='rs' property="rxrq" />
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
							<td colspan="6" align="center">
								<logic:empty name="rs" property="xymc">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="xymc">
								<bean:write name='rs' property="xymc" />
								</logic:notEmpty>
								&nbsp;ϵ&nbsp;&nbsp;
								<logic:empty name="rs" property="zymc">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="zymc">
								<bean:write name='rs' property="zymc" />
								</logic:notEmpty>
								&nbsp;רҵ&nbsp;&nbsp;
								<logic:empty name="rs" property="bjmc">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="bjmc">
								<bean:write name='rs' property="bjmc" />
								</logic:notEmpty>
								&nbsp;�༶&nbsp;&nbsp;
								<logic:empty name="rs" property="xh">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								<logic:notEmpty name="rs" property="xh">
								<bean:write name='rs' property="xh" />
								</logic:notEmpty>
								&nbsp;ѧ��
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
									��
								</div>
							</td>
							<td colspan="6">
								<bean:write name='rs' property="chhzjl" />
							</td>
						</tr>
						<tr>
							<td rowspan="3" scope="row">
								<div align="center">
									��
									<br />
									ͥ
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
							<td>
								<div align="center">
									��ͥ����
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jthk" />
								</div>
							</td>
							<td>
								<div align="center">
									��ͥ�˿�����
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtzrks" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥ��������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtyzsr" />
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									�˾�������
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rjysr" />
								</div>
							</td>
							<td>
								<div align="center">
									������Դ
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="srly" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ͥסַ
								</div>
							</td>
							<td colspan="4">
								<div align="center">
									<bean:write name='rs' property="jtzz" />
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
							<td rowspan="2" scope="row">
								<div align="center">
									ѧϰ�ɼ�
								</div>
							</td>
							<td>
								<div align="center">
									��ѧ���ۺϲ���������
								</div>
							</td>
							<td colspan="6">
								<logic:empty name='rs' property="sxnzhcpzpm" >
								<div align="right">
									������ /��רҵ�꼶��������
								</div>
								</logic:empty>
								<logic:notEmpty name='rs' property="sxnzhcpzpm" >
								<bean:write name='rs' property="sxnzhcpzpm" />
								&nbsp;&nbsp;������ /��רҵ�꼶��������
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									��ѧ��ѧϰ�ɼ�������
								</div>
							</td>
							<td colspan="6">
								<logic:empty name='rs' property="sxnxxcjzpm" >
								<div align="right">
									������ /��רҵ�꼶��������
								</div>
								</logic:empty>
								<logic:notEmpty name='rs' property="sxnxxcjzpm" >
								<bean:write name='rs' property="sxnxxcjzpm" />
								&nbsp;&nbsp;������ /��רҵ�꼶��������
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td colspan="8" scope="row">
								��������
								<br />
								<bean:write name='rs' property="sqly" />
								<br />
								<div align="right">
									������ǩ����
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="8" scope="row">
								ϵ������
								<br />
								<bean:write name='rs' property="xysh" />
								<br />
								<bean:write name='rs' property="xyshyj" />
								<br />
								<br />
								<div align="right">
									(����)
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
							</td>
						</tr>
<%--						<tr>--%>
<%--							<td colspan="8" scope="row">--%>
<%--								ϵ����֧���--%>
<%--								<br />--%>
<%--								<bean:write name='rs' property="xyzzfzryj" />--%>
<%--								<br />--%>
<%--								<br />--%>
<%--								<div align="right">--%>
<%--									(����)--%>
<%--									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��--%>
<%--									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
						<tr>
							<td colspan="8" scope="row">
								<bean:message key="lable.xsgzyxpzxy" />������
								<br />
								<bean:write name='rs' property="xxsh" />
								<br />
								<bean:write name='rs' property="xxshyj" />
								<br />
								<br />
								<div align="right">
									(����)
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;��
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
	</div>
</body>
</html:html>
