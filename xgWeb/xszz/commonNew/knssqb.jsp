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
	<meta name="Copyright" content="正方软件 zfsoft" />
	<style type="text/css">
	<!--
	.style1 {font-family: "华文行楷"}
	-->
	</style>
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
				<td scope="col">
					<div align="center">
							<h2>
						<strong>
								困难生申请表
						</strong>
							</h2> 
					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">
					<table width="100%" class="tbstyle">
						<tr>
							<td align="center" width="16%">
								学号
							</td>
							<td align="center" width="34%">
								<bean:write name='rs' property="xh" />
							</td>
							<td width="16%" scope="col">
								<div align="center">
									姓名
								</div>
							</td>
							<td width="34%" scope="col" align="center">
								<bean:write name='rs' property="xm" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									性别
								</div>
							</td>
							<td align="center">
								<bean:write name='rs' property="xb" />
							</td>
							<td>
								<div align="center">
									身份证号
								</div>
							</td>
							<td align="center">
								<bean:write name='rs' property="sfzh" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									<bean:message key="lable.xsgzyxpzxy" />
								</div>
							</td>
							<td align="center">
								<bean:write name='rs' property="xymc" />
							</td>
							<td>
								<div align="center">
									专业
								</div>
							</td>
							<td align="center">
								<bean:write name='rs' property="zymc" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									班级
								</div>
							</td>
							<td align="center">
								<bean:write name='rs' property="bjmc" />
							</td>
							<td>
								<div align="center">
									年级
								</div>
							</td>
							<td align="center">
								<bean:write name='rs' property="nj" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									学制
								</div>
							</td>
							<td align="center">
								<bean:write name='rs' property="xz" />
							</td>
							<td>
								<div align="center">
									联系电话
								</div>
							</td>
							<td align="center">
								<bean:write name="rs" property="lxdh" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									申请理由
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="sqly" />
							</td>
						</tr>
						<tr>
							<td scope="row">
								<div align="center">
									备注
								</div>
							</td>
							<td colspan="3">
								<bean:write name="rs" property="bz" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center">
		<input  value="打印" name="button_print"
			onclick="expTab('theTable','')" />
	</div>
</body>
</html:html>
