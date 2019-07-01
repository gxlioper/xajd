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
	<html:form action="/zzlgdx_rcsw" method="post">
		<table width="100%" border="0" id="theTable">
			<tr>
				<td scope="col">
					<div align="center">
						<h2>
							<strong> <bean:write name='rs' property="xxmc" />ѧ��Ӧ�����鱨����</strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<br /><br />
					&nbsp;
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr height="60px">
							<td width="20%">
								<div align="center">
									����
								</div>
							</td>
							<td width="30%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="20%">
								<div align="center">
									���֤��
								</div>
							</td>
							<td width="30%%">
								<div align="center">
									<bean:write name='rs' property="sfzh" />
								</div>
							</td>
						</tr>
						<tr height="60px">
							<td>
								<div align="center">
									�Ա�
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xb" />
								</div>
							</td>
							<td>
								<div align="center">
									�༶
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
						</tr>
						<tr height="60px">
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
						</tr>
						<tr height="60px">
							<td>
								<div align="center">
									�ֻ�
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="ji" />
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
						<tr height="60px">
							<td>
								<div align="center">
									�ֽ񻧼�
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xjhj" />
								</div>
							</td>
							<td>
								<div align="center">
									��ѧǰ����<br />���ڵ�
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="rxqhkszd" />
								</div>
							</td>
						</tr>
						<tr height="60px">
							<td>
								<div align="center">
									��ͥ��ϸ��ַ
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="jtxxdz" />
								</div>
							</td>
							<td>
								<div align="center">
									��ĸ��ϵ��ʽ
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="fmlxfs" />
								</div>
							</td>
						</tr>
						<tr height="30px">
							<td colspan="2">
								<div align="center">
									�Ƿ��Ѹ�֪�ҳ����õ��ҳ���ͬ��
								</div>
							</td>
							<td colspan="2">
								<div align="center">
									<logic:equal name='rs' property="sfygzjzbty" value="��">
										��
									</logic:equal>
									<logic:notEqual name='rs' property="sfygzjzbty" value="��">
										��
									</logic:notEqual>
									&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name='rs' property="sfygzjzbty" value="��">
										��
									</logic:equal>
									<logic:notEqual name='rs' property="sfygzjzbty" value="��">
										��
									</logic:notEqual>
									&nbsp;&nbsp;��
								</div>
							</td>
						</tr>
						<tr height="180px">
							<td colspan="4">
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<div align="center">
									�������˽�Ӧ���������ع涨������ϵ��Ը�μӡ�
								</div>
								<br />
								<br />
								<br />
								<br />
								<br />
								<br />
								<div align="right">
									ǩ����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
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
	</div>
</body>
</html:html>
