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
	<meta name="Copyright" content="正方软件 zfsoft" />
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
						学号
					</div>
				</td>
				<td colspan="3" scope="col">
					<bean:write name="rs" property="xh" />
				</td>
				<td width="14%" scope="col">
					<div align="center">
						姓名
					</div>
				</td>
				<td colspan="3" scope="col">
					<bean:write name="rs" property="xm" />
				</td>
			</tr>
			<tr>
				<td colspan="2" scope="row">
					<div align="center">
						性别
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="xb" />
				</td>
				<td>
					<div align="center">
						年级
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="nj" />
				</td>
			</tr>
			<tr>
				<td colspan="2" scope="row">
					<div align="center">
						身份证号
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="sfzh" />
				</td>
				<td>
					<div align="center">
						年度
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
						专业
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="zymc" />
				</td>
			</tr>
			<tr>
				<td colspan="2" scope="row">
					<div align="center">
						班级
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="bjmc" />
				</td>
				<td>
					<div align="center">
						联系电话
					</div>
				</td>
				<td colspan="3">
					<bean:write name="rs" property="lxdh" />
				</td>
			</tr>
			<tr>
				<td width="4%" rowspan="6" scope="row">
					<div align="center">
						家庭成员信息
					</div>
				</td>
				<td width="10%">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="10%">
					<div align="center">
						称谓
					</div>
				</td>
				<td width="10%">
					<div align="center">
						年龄
					</div>
				</td>
				<td width="16%">
					<div align="center">
						身份证号
					</div>
				</td>
				<td colspan="2">
					<div align="center">
						工作单位
					</div>
				</td>
				<td width="10%">
					<div align="center">
						月收入
					</div>
				</td>
				<td width="10%">
					<div align="center">
						健康状况
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
							收
							<br>
							入
							<br>
							来
							<br>
							源
							<br>
							情
							<br>
							况
						</div>
					</td>
					<td colspan="8">
						<div align="center">
							无收入来源项目!
						</div>
					</td>
				</tr>
			</logic:equal>
			<logic:equal name="isNULL" value="no">
				<tr>
					<td rowspan="<bean:write name='con' />">
						<div align="center">
							收
							<br>
							入
							<br>
							来
							<br>
							源
							<br>
							情
							<br>
							况
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							项目
						</div>
					</td>
					<td colspan="4">
						<div align="center">
							收入(元)
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
						申请原因
					</div>
				</td>
				<td colspan="7">
					<bean:write name="rs" property="sqyy" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						备注
					</div>
				</td>
				<td colspan="7">
					<bean:write name="rs" property="bz" />
				</td>
			</tr>
		</table>
	</html:form>
	<div align=center>
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','困难生申请表')" />
	</div>
</body>
</html:html>
