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
							<strong> <bean:write name='rs' property="xxmc" />ѧ����������</strong>
						</h2>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<br />
					&nbsp;
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="10%" height="20px">
								<div align="center">
									����
								</div>
							</td>
							<td width="20%">
								<div align="center">
									<bean:write name='rs' property="xm" />
								</div>
							</td>
							<td width="10%">
								<div align="center">
									�༶
								</div>
							</td>
							<td width="20%%">
								<div align="center">
									<bean:write name='rs' property="bjmc" />
								</div>
							</td>
							<td width="15%">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td width="25%%">
								<div align="center">
									<bean:write name='rs' property="xymc" />
								</div>
							</td>
						</tr>
						<tr>
							<td height="20px">
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
									ѧ��
								</div>
							</td>
							<td>
								<div align="center">
									<bean:write name='rs' property="xh" />
								</div>
							</td>
							<td>
								<div align="center">
									&nbsp;
								</div>
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr>
							<td rowspan="4">
								<div align="center">
									��&nbsp;&nbsp;��<br /><br />��&nbsp;&nbsp;��
								</div>
							</td>
							<td rowspan="4" colspan="2">
								<div align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name='rs' property="qjlx" value="����">
										��
									</logic:equal>
									<logic:notEqual name='rs' property="qjlx" value="����">
										��
									</logic:notEqual>
									&nbsp;&nbsp;����<br /><br />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name='rs' property="qjlx" value="�¼�">
										��
									</logic:equal>
									<logic:notEqual name='rs' property="qjlx" value="�¼�">
										��
									</logic:notEqual>
									&nbsp;&nbsp;�¼�
								</div>
							</td>
							<td rowspan="4">
								<div align="center">
									���ʱ��
								</div>
							</td>
							<td colspan="2" height="20px">
								<div align="center">
									<bean:write name='rs' property="qjksrq" />&nbsp;&nbsp;��&nbsp;&nbsp;<bean:write name='rs' property="qjjzrq" />
								</div>
							</td>
						</tr>
						<tr>
							<td rowspan="3">
								<div align="center">
									�γ�����
								</div>
							</td>
							<td height="20px">
								<div align="right">
									<bean:write name='rs' property="qjkc1" />&nbsp;&nbsp;&nbsp;&nbsp;��
									<logic:empty name='rs' property="qjkcs1">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name='rs' property="qjkcs1">
										<bean:write name='rs' property="qjkcs1" />
									</logic:notEmpty>
									��
								</div>
							</td>
						</tr>
						<tr>
							<td height="20px">
								<div align="right">
									<bean:write name='rs' property="qjkc2" />&nbsp;&nbsp;&nbsp;&nbsp;��
									<logic:empty name='rs' property="qjkcs2">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name='rs' property="qjkcs2">
										<bean:write name='rs' property="qjkcs2" />
									</logic:notEmpty>
									��
								</div>
							</td>
						</tr>
						<tr>
							<td height="20px">
								<div align="right">
									<bean:write name='rs' property="qjkc3" />&nbsp;&nbsp;&nbsp;&nbsp;��
									<logic:empty name='rs' property="qjkcs3">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty>
									<logic:notEmpty name='rs' property="qjkcs3">
										<bean:write name='rs' property="qjkcs3" />
									</logic:notEmpty>
									��
								</div>
							</td>
						</tr>
						<tr height="80px">
							<td>
								<div align="center">
									��&nbsp;&nbsp;��<br /><br />ԭ&nbsp;&nbsp;��
								</div>
							</td>
							<td colspan="5">
								<br />
								<logic:empty name='rs' property="qjyy">
									<br />
									<br />
									<br />
									<br />
									<br />
									<br />
								</logic:empty>
								<logic:notEmpty name='rs' property="qjyy">
									<bean:write name='rs' property="qjyy" />
								</logic:notEmpty>
								<br /><br />
								<div align="right">
									�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<tr height="80px">
							<td>
								<div align="center">
									ѧ����<br />���칫<br />�����
								</div>
							</td>
							<td colspan="5">
								<br />
								<div align="center">
									���飬���ԭ����ʵ��ͬ��������������
								</div>
								<br />
								<br />
								<br />
								<div align="right">
									�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
								<div align="right">
									��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								</div>
								<br />
							</td>
						</tr>
						<tr height="80px">
							<td>
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />��<br />�����
								</div>
							</td>
							<td colspan="5">
								<br />
								<div align="center">
									ͬ��������������
								</div>
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
			<tr>
				<td>
					ע�����ʱ��3�������ɰ����λ򸨵�Ա��׼��3��������һ�ܵ��뾭<bean:message key="lable.xsgzyxpzxy" />����ѧ���������쵼��׼ͬ�⣬һ�����ϵ���<bean:message key="lable.xsgzyxpzxy" />�����ѧ��Ժ��ͬ�⡣
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
