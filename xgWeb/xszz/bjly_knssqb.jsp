<%@ page language="java" contentType="text/html;charset=GBK"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.Iterator" />

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
	<html:form action="lyjszxjsqb.do" method="post">
		<input type="hidden" name="tableName" id="tableName"
			value="<bean:write name="sqlb" />" />
		<p align="center" style="font-size:24px">
			<bean:write name="sqlb" />
		</p>

		<table width="100%" class="tbstyle" id="theTable">
			<tr>
				<td colspan="2" scope="col">
					<div align="center">
						ѧ��
					</div>
				</td>
				<td colspan="3" scope="col">
					<bean:write name="rs" property="xh" />
				</td>
				<td width="14%" scope="col">
					<div align="center">
						����
					</div>
				</td>
				<td colspan="3" scope="col">
					<bean:write name="rs" property="xm" />
				</td>
			</tr>
			<tr>
				<td colspan="2" scope="row">
					<div align="center">
						�Ա�
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="xb" />
				</td>
				<td>
					<div align="center">
						�꼶
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="nj" />
				</td>
			</tr>
			<tr>
				<td colspan="2" scope="row">
					<div align="center">
						���֤��
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="sfzh" />
				</td>
				<td>
					<div align="center">
						���
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="nd" />
				</td>
			</tr>
			<tr>
				<td colspan="2" scope="row">
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="xymc" />
				</td>
				<td>
					<div align="center">
						רҵ
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="zymc" />
				</td>
			</tr>
			<tr>
				<td colspan="2" scope="row">
					<div align="center">
						�༶
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="bjmc" />
				</td>
				<td>
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="lxdh" />
				</td>
			</tr>
			<tr>
				<td width="4%" rowspan="6" scope="row">
					<div align="center">
						��ͥ��Ա��Ϣ
					</div>
				</td>
				<td width="10%">
					<div align="center">
						����
					</div>
				</td>
				<td width="10%">
					<div align="center">
						��ν
					</div>
				</td>
				<td width="10%">
					<div align="center">
						����
					</div>
				</td>
				<td width="16%">
					<div align="center">
						���֤��
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						������λ
					</div>
				</td>
				<td width="10%">
					<div align="center">
						������
					</div>
				</td>
				<td width="10%">
					<div align="center">
						����״��
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
						<bean:write name="rs" property="jtcy1_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy1_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_sfzh" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name="rs" property="jtcy1_gzdw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_ysr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy1_jkzk" />
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
						<bean:write name="rs" property="jtcy2_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy2_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_sfzh" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name="rs" property="jtcy2_gzdw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_ysr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy2_jkzk" />
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
						<bean:write name="rs" property="jtcy3_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy3_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_sfzh" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name="rs" property="jtcy3_gzdw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_ysr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy3_jkzk" />
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
						<bean:write name="rs" property="jtcy4_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy4_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_sfzh" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name="rs" property="jtcy4_gzdw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_ysr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy4_jkzk" />
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
						<bean:write name="rs" property="jtcy5_cw" />
					</div>
				</td>
				<td>
					<div align="center">
						&nbsp;<bean:write name="rs" property="jtcy5_nl" />&nbsp;
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_sfzh" />
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						<bean:write name="rs" property="jtcy5_gzdw" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_ysr" />
					</div>
				</td>
				<td>
					<div align="center">
						<bean:write name="rs" property="jtcy5_jkzk" />
					</div>
				</td>
			</tr>
			<logic:equal name="isNULL" value="is">
				<tr>
					<td rowspan="<bean:write name='con' />">
						<div align="center">
							��
							<br>
							��
							<br>
							��
							<br>
							Դ
							<br>
							��
							<br>
							��
						</div>
					</td>
					<td colspan="8">
						<div align="center">
							��������Դ��Ŀ!
						</div>
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="isNULL" value="no">
				<tr>
					<td rowspan="<bean:write name='con' />">
						<div align="center">
							��
							<br>
							��
							<br>
							��
							<br>
							Դ
							<br>
							��
							<br>
							��
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							��Ŀ
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							����(Ԫ)
						</div>
					</td>
				</tr>
				<%
					ArrayList srlyList = (ArrayList) request
							.getAttribute("srlyList");
					String srlyName = "";
					
					for (Iterator it = srlyList.iterator(); it.hasNext();) {
						String[] temp = (String[]) it.next();
						srlyName = "srly" + temp[0];
				%>
				<tr>
					<td colspan="4">
						<div align="center">
							<%=temp[1]%>
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							<bean:write name="rs" property="<%=srlyName%>" />
						</div>
					</td>
				</tr>
				<%
					}
				%>
			</logic:equal>
			<tr>
				<td colspan="2">
					<div align="center">
						����ԭ��
					</div>
				</td>
				<td colspan="7">
					<bean:write name="rs" property="sqyy" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						��ע
					</div>
				</td>
				<td colspan="7">
					<bean:write name="rs" property="bz" />
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="��ӡ" name="button_print"
			onclick="expTab('theTable','�����������')" />
	</div>
</body>
</html:html>
